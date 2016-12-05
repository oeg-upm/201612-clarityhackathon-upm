package es.upm.fi.dia.oeg.clarity2016hackathon;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.apache.logging.log4j.Logger;

public class UtilityTest {
	Logger logger = LogManager.getLogger("UtilityTest");

	@Test
	public void testReadModelFromFile() {
		try {
			Utility.readModelFromFile("tramite-corrected.jsonld", null, "JSON-LD");
			System.out.println("Bye");
			assertTrue(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			assertTrue(false);
		}

		
	}

}
