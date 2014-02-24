package net.sourceforge.quoa.provider.impl;

import net.sourceforge.quoa.provider.IOProvider;
import net.sourceforge.quoa.provider.LocalizationProvider;
import net.sourceforge.quoa.provider.ServiceProvider;


public class ServiceProviderImpl implements ServiceProvider {

	private IOProvider io;
	private LocalizationProvider l10n;
	public ServiceProviderImpl(){
		io = new IOProviderImpl();
		l10n = new LocalizationProviderImpl();
	}
	
	public IOProvider getIOProvider(){
		return io;
	}

	@Override
	public LocalizationProvider getL10NProvider() {
		return l10n;
	}
}
