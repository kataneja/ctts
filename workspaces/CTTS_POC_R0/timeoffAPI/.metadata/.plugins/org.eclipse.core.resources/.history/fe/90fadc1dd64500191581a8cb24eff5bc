package gov.wisconsin.framework.impl;

import gov.wisconsin.framework.constant.FwConstants;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *	The purpose of this class is to provide a way for our framework to read the HTTPRequest multiple times because
 *	an HTTPRequest can only have its getInputStream() method called once. What we do here is open that inputStream
 *	and store the data in a String. We can call methods on this class just as we would a regular HTTPRequest or 
 *	HTTPServletRequest and it will reference that String instead of the request's inputStream.
 */
public class FwRequestWrapper extends HttpServletRequestWrapper {

	private String content = FwConstants.EMPTY_STRING;
	
	public FwRequestWrapper(HttpServletRequest request) {
		super(request);
		BufferedReader bufferedReader;
		try {
			bufferedReader = request.getReader();
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) { sb.append(line); }
			content = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
		return new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isReady() { return false; }
			@Override
			public boolean isFinished() { return false; }
			@Override
			public void setReadListener(ReadListener arg0) {}
		};
	}
	
	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
	
	@Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        return (header != null) ? header : super.getParameter(name);
    }

	@Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(Collections.list(super.getParameterNames()));
        return Collections.enumeration(names);
    }
}