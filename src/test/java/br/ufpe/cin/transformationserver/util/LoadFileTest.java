package br.ufpe.cin.transformationserver.util;


import static org.junit.Assert.*;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;



public class LoadFileTest {

	@Test
	public void testHello() {
		String result = getFile("transformation/eer2relational/eer2SQL.egl");
		assertNotNull(result);
	}


	private String getFile(String fileName){

		String result = "";

		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(classLoader.getResource(fileName).getPath());

		System.out.println(classLoader.getResource("messages.properties").getPath());
		
		return result;

	}

}
