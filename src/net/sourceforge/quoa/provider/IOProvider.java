package net.sourceforge.quoa.provider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface IOProvider {

	File createFile(final String pathname) throws IOException;
	InputStream open(final URL url) throws IOException;
	
}
