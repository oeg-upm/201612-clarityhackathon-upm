package oeg.clarity.egov.tramites;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author admin
 */
public class TramiteManager {
    
    public static List<Tramite> tramites = new ArrayList();

    public static void test()
    {
	if (tramites.isEmpty())
        {
            init();
        }
    }
    
    public static List<Tramite> getTramites()
    {
	if (tramites.isEmpty())
        {
            init();
        }
        
        return tramites;
        
    }
    
    public static void init(){
        System.out.println("Inicializando los datos " + tramites.size());
        tramites.clear();
	ClassLoader classLoader = TramiteManager.class.getClassLoader();
	try {
	    String result = IOUtils.toString(classLoader.getResourceAsStream("data/tramite.jsonld"), "UTF-8");
            JSONParser parser = new JSONParser();
            JSONObject jobj = (JSONObject) parser.parse(result);
            JSONArray arr = (JSONArray) jobj.get("result");
            int tam = arr.size();
            System.out.println("El array tiene un tamaño de " + tam);
            for(int i=0;i<tam;i++)
            {
                Tramite t = new Tramite();
                JSONObject jtramite = (JSONObject) arr.get(i);
                String title = (String) jtramite.get("title");
            //    System.out.println(title);
                t.titulo = URLEncoder.encode(title, "UTF-8");
                t.id="" + ((Long) jtramite.get("id"));
                t.descabre = (String) jtramite.get("descabre");
                tramites.add(t);
            }
            System.out.println(tam);
            
	} catch (Exception e) {
		e.printStackTrace();
	}        
    }

    public static Tramite getTramite(String id)
    {
                for(Tramite t : tramites)
        {
            if (t.id.equals(id))
                return t;
        }
                return null;
    }
    
    public static List<Tramite> filter(String searchFrase) {
        if (searchFrase==null || searchFrase.isEmpty())
            return tramites;
        List<Tramite> f = new ArrayList();
        for(Tramite t : tramites)
        {
            if (t.titulo.contains(searchFrase))
                f.add(t);
        }
        return f;
    }
}
