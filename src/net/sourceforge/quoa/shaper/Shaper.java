package net.sourceforge.quoa.shaper;

public interface Shaper<T> {

	T shape(T value);
	void setParameters(Object...parameters);
	
}
