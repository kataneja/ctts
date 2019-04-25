package gov.wisconsin.framework.util;

import gov.wisconsin.framework.base.FwBaseClass;
import gov.wisconsin.framework.constant.FwConstants;
import gov.wisconsin.framework.exception.FwException;

import java.io.Serializable;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.springframework.stereotype.Component;

@Component
public class FwMessageFormatter extends FwBaseClass implements Serializable {
	private static final long serialVersionUID = -8805626992832917816L;

	private final String PARSER = "~";
	
	private static FwMessageFormatter instance;
	
	public String getFormattedMessage(String message, String[] substitute) {
		for (int i = 0; i < substitute.length; i++) {
			if (substitute[i] != null)
				message = message.replaceFirst(PARSER, substitute[i]);
		}
		return message;
	}

	public String getFormattedMessageWithSymbols(String message, String[] substitute) {
		for (int i = 0; i < substitute.length; i++) {
			substitute[i] = createStringWithEscapeChars(substitute[i]);
			message = message.replaceFirst(PARSER, substitute[i]);
		}
		
		return message;
	}

	private String createStringWithEscapeChars(String message) {
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(message);
		
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			if (character == '$')
				result.append("\\$"); 
			else if (character == '\\')
				result.append("\\\\");
			else
				result.append(character);
				
			character = iterator.next();
		}
		
		return result.toString();
	}

	public String getCachedText(int textID, String language) {
		String text = FwConstants.EMPTY_STRING;
		try {
			text = pageManager.getDisplayText(textID, language);
		} catch (FwException fe) {
			return text;
		}
		return text;
	}
	
    public static void setInstance(FwMessageFormatter messageFormatter) {
    	instance = messageFormatter;
    }
    
    public static FwMessageFormatter getInstance() {
    	return instance;
    }
}
