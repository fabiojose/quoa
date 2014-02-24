package net.sourceforge.quoa.exception;

public final class NonUniqueException extends RuntimeException {
	private static final long serialVersionUID = -4851340697923684708L;

	public NonUniqueException(){
		
	}
	
	public NonUniqueException(final String message){
		super(message);
	}
	
	public NonUniqueException(final Throwable cause){
		super(cause);
	}
	
	public NonUniqueException(final String message, final Throwable cause){
		super(message, cause);
	}
}
