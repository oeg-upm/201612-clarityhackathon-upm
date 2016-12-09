package oeg.clarity.egov.tramites;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;

import oeg.clarity.egov.parsers.Utility;

/**
 *
 * @author admin
 */
public class TramiteManager {

	public static String result = "";

	public static void test()
	{
		if (result.isEmpty())
		{
			init();
		}
	}


	public static void init(){
		ClassLoader classLoader = TramiteManager.class.getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream("data/tramite-corrected.jsonld"));
			JSONParser parser = new JSONParser();
			JSONObject jobj = (JSONObject) parser.parse(result);
			JSONArray arr = (JSONArray) jobj.get("result");
			int tam = arr.size();
			for(int i=0;i<tam;i++)
			{
				JSONObject jtramite = (JSONObject) arr.get(i);
				String title = (String) jtramite.get("title");
				System.out.println(title);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}        
	}
	
}
