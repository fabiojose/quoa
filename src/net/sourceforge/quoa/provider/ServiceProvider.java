package net.sourceforge.quoa.provider;

public interface ServiceProvider {
	
	IOProvider getIOProvider();
	LocalizationProvider getL10NProvider();
	
}
