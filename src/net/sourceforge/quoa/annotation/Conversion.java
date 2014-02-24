package net.sourceforge.quoa.annotation;

import net.sourceforge.quoa.converter.Converter;

public @interface Conversion {

	@SuppressWarnings("rawtypes") Class<? extends Converter> converter();
	String[] parameters() default {};
	
}
