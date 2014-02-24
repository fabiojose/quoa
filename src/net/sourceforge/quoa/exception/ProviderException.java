package net.sourceforge.quoa.exception;

public final class ProviderException extends QuoaException {
	private static final long serialVersionUID = 8561429015105033242L;

	public ProviderException(){
		
	}
	
	public ProviderException(final String message){
		super(message);
	}
	
	public ProviderException(final Throwable cause){
		super(cause);
	}
	
	public ProviderException(final String message, final Throwable cause){
		super(message, cause);
	}
}
