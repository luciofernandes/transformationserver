package br.ufpe.cin.transformationserver.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ResourceLoader {

	ClassLoader classLoader = getClass().getClassLoader();
	
	public String getResourceRoot(){
		String  out = classLoader.getResource("messages.properties").getPath();
		out = out.replace("messages.properties", "");
		return out;
	}
	
	public String getPath(String resource){
		return  classLoader.getResource(resource).getPath();
	}
	
	public File  getFile(String resource){
		return new File(classLoader.getResource(resource).getFile());
	}
	
	public String getContent(String resource){
		String out = "";
		try {
			out = IOUtils.toString(classLoader.getResourceAsStream(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
}
