package net.sourceforge.quoa.provider.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import net.sourceforge.quoa.provider.IOProvider;

public final class IOProviderImpl implements IOProvider {

	public File createFile(final String pathname) throws IOException {
		
		final File _result = new File(pathname);
		_result.createNewFile();
		
		return _result;
	}
	
	public InputStream open(final URL url) throws IOException {
		//TODO verify if the connection is over proxy
		final URLConnection _connection = url.openConnection();
		
		return _connection.getInputStream();
	}
	
	public static void main(String...args){
		try{
			System.out.println(new URL("file:D:\\FIRP\\evidencia").openConnection());
			
		}catch(IOException _e){
			
		}
	}
}
