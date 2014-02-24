package net.sourceforge.quoa.exception;

public final class ConverterException extends QuoaException {
	private static final long serialVersionUID = 2693435545335574036L;

	public ConverterException(){
		
	}
	
	public ConverterException(final String message){
		super(message);
	}
	
	public ConverterException(final Throwable cause){
		super(cause);
	}
	
	public ConverterException(final String message, final Throwable cause){
		super(message, cause);
	}
}
