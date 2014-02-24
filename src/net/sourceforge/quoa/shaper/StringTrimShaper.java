package net.sourceforge.quoa.shaper;

public final class StringTrimShaper implements Shaper<String> {

	@Override
	public String shape(final String value) {

		if(null!= value){
			return value.trim();
		}
		
		return value;
	}

	@Override
	public void setParameters(Object... parameters) {		
	}

}
