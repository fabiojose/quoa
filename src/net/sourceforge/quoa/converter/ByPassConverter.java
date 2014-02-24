package net.sourceforge.quoa.converter;

import net.sourceforge.quoa.annotation.Cacheable;

@Cacheable
public final class ByPassConverter implements Converter<Object, Object> {

	@Override
	public Object forward(Object value) {
		return value;
	}

	@Override
	public Object backward(Object value) {
		return value;
	}

	@Override
	public void setParameters(Object... parameters) {
	}
}
