package net.sourceforge.quoa.annotation;

import net.sourceforge.quoa.converter.ByPassConverter;
import net.sourceforge.quoa.shaper.ByPassShaper;

public @interface InjectionMapping {

	String from();
	Attribute  destination();
	Conversion conversion() default @Conversion(converter = ByPassConverter.class);
	Shapping shapping() default @Shapping(shaper = ByPassShaper.class);
	
}
