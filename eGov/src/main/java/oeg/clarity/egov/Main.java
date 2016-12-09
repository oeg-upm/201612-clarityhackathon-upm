package oeg.clarity.egov;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import oeg.clarity.egov.parsers.Utility;
import oeg.clarity.egov.utils.CSVUtils;

/**
 *
 * @author admin
 */
public class Main {
	Logger logger = LogManager.getLogger("Main");
	 
	
	public static void main(String[] args) throws IOException {
		Map<String, Set<String>> mapTemaSubtemas = new HashMap<String, Set<String>>();
		System.out.println("Clase para probar el código rápidamente");
		//TramiteManager.test();

		try {
			Document doc = Utility.readDocumentFromXMLFile("temas_subtemas.xml");
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList resultsList = doc.getElementsByTagName("result");
	         if(resultsList.getLength() > 0 ) {
	        	 Element resultElement = (Element) resultsList.item(0); //just get the first result element
	             //System.out.println("\nCurrent Element :" + resultNode.getNodeName());
	             NodeList docsList = resultElement.getElementsByTagName("doc");
	             if(docsList.getLength() > 0) {
	            	 for(int docNo=0; docNo<docsList.getLength(); docNo++) {
	            		 Element docNode = (Element) docsList.item(docNo);
	            		 List<String> listTema = new LinkedList<String>();
	            		 List<String> listSubTema = new LinkedList<String>();
	            		 
	    	             //System.out.println("\nCurrent Element :" + docNode.getNodeName());
	            		 NodeList arrsList = docNode.getElementsByTagName("arr");
	            		 if(arrsList.getLength() > 0 ) {
	            			 for(int arrNo=0; arrNo<arrsList.getLength(); arrNo++) {
	            				 Element arrNode = (Element) arrsList.item(arrNo);
	            				 String arrNodeName = arrNode.getAttribute("name");
	            				 
	            				 NodeList strsList = arrNode.getElementsByTagName("str");
	            				 for(int strNo=0; strNo<strsList.getLength(); strNo++) {
	            					 Element strNode = (Element) strsList.item(strNo);
		            				 String strNodeTextContent = strNode.getTextContent();
		            				 //System.out.println("strNodeTextContent = " + strNodeTextContent);
		            				 if(arrNodeName.equals("temas_smultiple")) {
		            					 listTema.add(strNodeTextContent);
		            				 } else if (arrNodeName.equals("subtemas_smultiple")) {
		            					 listSubTema.add(strNodeTextContent);
		            				 }
	            				 }
	            			 }
	            			 
	            			 System.out.println("listTema :" + listTema);
	            			 System.out.println("  listSubTema :" + listSubTema);
	            			 int sizeOfListTema = listTema.size();
	            			 int sizeOfListSubTema = listSubTema.size();
	            			 int minSize = sizeOfListTema;
	            			 if(sizeOfListSubTema < minSize) {
	            				 minSize = sizeOfListSubTema;
	            			 }
	            			 for(int i=0; i<minSize; i++) {
	            				 String tema = listTema.get(i);
	            				 String subTema = listSubTema.get(i);
	            				 Set<String> subTemas;
	            				 if(!mapTemaSubtemas.containsKey(tema)) {
	            					 subTemas = new HashSet<String>();
	            				 } else {
	            					 subTemas = mapTemaSubtemas.get(tema);
	            				 }
	            				 subTemas.add(subTema);
	            				 
	            				 mapTemaSubtemas.put(tema, subTemas);
	            			 }
	            		 } else {
	            			 System.out.println("No arr Element");
	            		 }
	            	 }
	             } else {
	            	 System.out.println("No doc Element");
	             }
	         } else {
	 			System.out.println("No result Element");
	         }
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("mapTemaSubtemas = " + mapTemaSubtemas);
		
		String csvFile = "temas_subtemas.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, Arrays.asList("theme", "subtheme"));
        Iterator<String> mapKeys = mapTemaSubtemas.keySet().iterator();
        while(mapKeys.hasNext()) {
        	String tema = mapKeys.next();
        	Iterator<String> subtemasIterator = mapTemaSubtemas.get(tema).iterator();
        	
            while(subtemasIterator.hasNext()) {
            	String subtema = subtemasIterator.next(); 
            	CSVUtils.writeLine(writer, Arrays.asList(tema, subtema));
            	
        	}
        }
        writer.flush();
        writer.close();
	}

}
