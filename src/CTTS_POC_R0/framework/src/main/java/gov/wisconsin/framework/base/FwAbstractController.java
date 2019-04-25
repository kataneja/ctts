package gov.wisconsin.framework.base;

import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;
import gov.wisconsin.framework.exception.FwWrappedException;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public abstract class FwAbstractController extends FwBaseClass {
	
	@Value(FwConstants.ENVIRONMENT_NAME_INJECT)
	public String currentEnv;

	@GetMapping(value = FwConstants.EMPTY_STRING, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getExample(@ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		try {
			return ResponseEntity.status(200).body("Success");
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
	}
	
	@PostMapping(value = FwConstants.EMPTY_STRING, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> postExample(@RequestBody Map<String, Object> apiRequest, @ModelAttribute("username") String username, HttpServletRequest httpRequest, HttpSession httpSession) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		try {
			int counter = 1;
			for (Map.Entry<String, Object> entry : apiRequest.entrySet()) {
				responseMap.put(String.valueOf(counter++), "Posted: " + entry.getKey() + " | " + entry.getValue());
			}
		} catch(FwException fe) {
			throw new FwWrappedException(this.getClass(), fe, httpRequest);
		}
		
		return ResponseEntity.status(200).body(responseMap);
	}
	
	@ModelAttribute("username")
    public String parseUsernameFromRequest(@RequestHeader HttpHeaders httpHeaders) {
        String result = FwConstants.EMPTY_STRING;
        
        try {
        	List<String> authHeaders = httpHeaders.get("Authorization");
        	if (authHeaders != null && authHeaders.size() > 0) {
				String encodedCredentials = authHeaders.get(0).split(FwConstants.SPACE)[1];
				String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
				result = decodedCredentials.split(FwConstants.COLON)[0];
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return result;
    }
}
