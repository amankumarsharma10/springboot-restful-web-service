package com.aman.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponce {
	private Date timestamp;
	private String message;
	private String details;
		
	/**
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ExceptionResponce(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}	
}
