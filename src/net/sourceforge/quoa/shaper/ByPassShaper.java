package net.sourceforge.quoa.shaper;

import net.sourceforge.quoa.annotation.Cacheable;

@Cacheable
public final class ByPassShaper implements Shaper<Object> {

	@Override
	public Object shape(Object value) {
		return value;
	}

	@Override
	public void setParameters(Object... parameters) {		
	}

}
