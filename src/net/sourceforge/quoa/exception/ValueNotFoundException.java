package net.sourceforge.quoa.exception;

public final class ValueNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 200018590770583565L;

	public ValueNotFoundException(){
		
	}
	
	public ValueNotFoundException(final String message){
		super(message);
	}
	
	public ValueNotFoundException(final Throwable cause){
		super(cause);
	}
	
	public ValueNotFoundException(final String message, final Throwable cause){
		super(message, cause);
	}
}
