package oeg.clarity.egov;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;

import oeg.clarity.egov.parsers.Utility;

public class TestEGov {

	@Test
	public void test() {
		try {
			Document doc = Utility.readDocumentFromXMLFile("dirigidoa_smultiple.xml");
			
	        doc.getDocumentElement().normalize();
	        System.out.println("Root element :" 
	           + doc.getDocumentElement().getNodeName());
	        
			assertTrue(true);
			System.out.println("bye");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		

	}

}
