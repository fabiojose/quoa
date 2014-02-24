package net.sourceforge.quoa.annotation;

import net.sourceforge.quoa.shaper.Shaper;

public @interface Shapping {

	Class<? extends Shaper<? extends Object>> shaper();
	String[] parameters() default {};
	
}
