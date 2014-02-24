package net.sourceforge.quoa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Constants {

	private Constants(){
	}
	
	public static final class PARAMETERS {
		public static final String URL = "url";
		public static final String SEPARATOR = "separator";
		public static final String LOCATION = "location";
		public static final String TIMEOUT = "timeout";
		public static final String LOCALE = "locale";
	}
	
	public static final class PROTOCOLS {
		public static final String FILE = "file:";
	}
	
	public static final class PATTERNS {
		public static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{\\w+\\}");
		
		public static final Pattern PREFIX_FUNCTION = Pattern.compile("prefix\\(\\)");
		public static final Pattern VALUE_FUNCTION = Pattern.compile("value\\(\\)");
		public static final Pattern PREFIX_VALUE_FUNCTION = Pattern.compile(PREFIX_FUNCTION + "\\." + VALUE_FUNCTION);
		public static final Pattern PREFIX_FUNCTION_KEY = Pattern.compile(PREFIX_FUNCTION + "\\.[\\w\\.]+");
	}
	
	public static final class STRINGS {
		public static final String PREFIX_FUNCTION = "prefix()";
		public static final String UNDERSCORE = "_";
		
	}
	
	public static final class CHARS {
		public static final char POINT = '.';
	}
	
	public static void main(String...args){
		final Matcher _matcher = PATTERNS.PLACEHOLDER.matcher("${0000afda fafas}");
		System.out.println(_matcher.matches());
	}
}
