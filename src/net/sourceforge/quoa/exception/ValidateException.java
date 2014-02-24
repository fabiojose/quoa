package net.sourceforge.quoa.exception;

public final class ValidateException extends QuoaException {
	private static final long serialVersionUID = -5167475526778760774L;

	public ValidateException(){
		
	}
	
	public ValidateException(final String message){
		super(message);
	}
	
	public ValidateException(final Throwable cause){
		super(cause);
	}
	
	public ValidateException(final String message, final Throwable cause){
		super(message, cause);
	}
}
