package net.sourceforge.quoa.exception;

public class QuoaException extends RuntimeException {
	private static final long serialVersionUID = 4918587894251840041L;

	public QuoaException(){
		
	}
	
	public QuoaException(final String message){
		super(message);
	}
	
	public QuoaException(final Throwable cause){
		super(cause);
	}
	
	public QuoaException(final String message, final Throwable cause){
		super(message, cause);
	}
}
