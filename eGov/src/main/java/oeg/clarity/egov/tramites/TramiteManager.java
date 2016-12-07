package oeg.clarity.egov.tramites;

import java.io.IOException;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author admin
 */
public class TramiteManager {
    
    public static void test()
    {
	String result = "";

	ClassLoader classLoader = TramiteManager.class.getClassLoader();
	try {
	    result = IOUtils.toString(classLoader.getResourceAsStream("data/tramite-corrected.jsonld"));
            
            
            
	} catch (IOException e) {
		e.printStackTrace();
	}        
    }
}
