package erzeugung;

import java.io.IOException;

import org.junit.Test;



public class TestDaten {
	@Test
	public void testMit100 () {
		try {
			Datenerzeugung.weblogErstellen(1000000, "test.txt", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
