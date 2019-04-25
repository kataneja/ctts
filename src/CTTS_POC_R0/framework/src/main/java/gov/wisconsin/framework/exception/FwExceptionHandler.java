package gov.wisconsin.framework.exception;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.impl.FwDate;
import gov.wisconsin.framework.transport.FwErrorResponse;
import gov.wisconsin.framework.transport.FwResponse;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FwExceptionHandler extends FwBaseClass {
	
	private FwDate fwDate; //@Autowired
	
	@ExceptionHandler({FwWrappedException.class, FwException.class})
	public ResponseEntity<FwResponse> handleFwException(RuntimeException ex, WebRequest request) {
		FwWrappedException exception;
		if (ex instanceof FwWrappedException) {
			exception = (FwWrappedException) ex;
			if (exception.getExceptionTime() == null) {
				exception.setExceptionTime(getCurrentTimestamp());				
			}
		}
		else {
			exception = new FwWrappedException();
		}
		
		if (ex instanceof FwException) {
			FwException tempException = (FwException) ex;
			if (tempException.getExceptionType() == -1) {
				tempException.setExceptionType(FwConstants.EXP_TYP_FRAMEWORK);
			}
			exception.setFwException(tempException); 
		}

		FwException tempException = exception.getFwException();
		if (tempException.isLoggable() && !tempException.isLoggedToDB()) {
			FwExceptionManager.log(exception);
		}

		FwErrorResponse response = new FwErrorResponse(exception);
		return ResponseEntity.status(exception.getStatusCode()).body(response);
    }
	
	@ExceptionHandler({
		NullPointerException.class, NegativeArraySizeException.class, NumberFormatException.class, 
		ArithmeticException.class, ArrayIndexOutOfBoundsException.class, ArrayStoreException.class, 
		CloneNotSupportedException.class, IllegalAccessException.class, InstantiationException.class, 
		ClassCastException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, 
		IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, 
		StringIndexOutOfBoundsException.class, UnsupportedOperationException.class, ClassNotFoundException.class, 
		NoSuchFieldException.class, NoSuchMethodException.class, InterruptedException.class, SecurityException.class
	})
	public ResponseEntity<FwResponse> handleOtherExceptions(Exception ex, WebRequest request) {
		FwException fwException = new FwException();
		StackTraceElement exceptionSource = ex.getStackTrace()[0];

		fwException.setOriginalException(ex);
		fwException.setClassID(exceptionSource.getClassName());
		fwException.setMethodID(exceptionSource.getMethodName());
		fwException.setCustomMessage("An uncaught exception occurred in the ACCESS APIs and was handled by the FwExceptionHandler.");
		fwException.setExceptionText(ex.getClass().getName() + " | " + ex.getMessage());
		fwException.setStackTraceValue(FwExceptionManager.getStackTraceValue(ex));
		FwExceptionManager.log(fwException);
		
		FwErrorResponse response = new FwErrorResponse(fwException);
		return ResponseEntity.status(500).body(response);
	}
	
	protected final Timestamp getCurrentTimestamp() {
		return fwDate.getTimestamp();
	}
	
	public FwDate getFwDate() {
		return fwDate;
	}

	@Autowired
	public void setFwDate(FwDate fwDate) {
		this.fwDate = fwDate;
	}
}