package com.perfectplay.org;

public class InvalidQueryException extends RuntimeException {
	
	private static final long serialVersionUID = -799687285107586458L;
	
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int NOT_FOUND = 404;
	public static final int REQUEST_LIMIT = 429;
	public static final int SERVER_ERROR = 500;
	public static final int Other = 0;
	
	public final int Error;
	
	public InvalidQueryException(int error, String string) {
		super(string);
		this.Error = error;
	}
	
	static InvalidQueryException generateException(String error){
		if(error.contains("400"))
			return new InvalidQueryException(400,"Status 400: Bad Request");
		if(error.contains("401"))
			return new InvalidQueryException(401,"Status 401: Unauthorized");
		if(error.contains("404"))
			return new InvalidQueryException(404,"Status 404: Not Found");
		if(error.contains("429"))
			return new InvalidQueryException(429,"Status 429: Too Many Queries");
		if(error.contains("500"))
			return new InvalidQueryException(500,"Status 500: Internal Server Error");
		throw new InvalidQueryException(0, error);
	}

}
