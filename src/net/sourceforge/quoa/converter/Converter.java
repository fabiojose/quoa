package net.sourceforge.quoa.converter;

public interface Converter<F,B> {
	
	/**
	 * From a value to another
	 * @param value
	 * @return
	 */
	B forward(F value);
	
	/**
	 * The 'another' to the original
	 * @param value
	 * @return
	 */
	F backward(B value);
	
	/**
	 * Set of converter parameters
	 * @param parameters
	 */
	void setParameters(Object...parameters);
}
