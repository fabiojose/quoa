package net.sourceforge.quoa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sourceforge.quoa.adapter.IQuoaAdapter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface SourceOverride {

	String name();
	Class<? extends IQuoaAdapter<? extends Object, ? extends Object>> adapter();
	Argument[] arguments();
	InjectionMapping[] injections();
	
}
