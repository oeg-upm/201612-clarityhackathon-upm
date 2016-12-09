package oeg.clarity.egov.parsers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.w3c.dom.Document;

public class Utility {

	public static Model readModelFromString(String modelText, String lang) {
		Model model = ModelFactory.createDefaultModel();
		InputStream is = new ByteArrayInputStream(modelText.getBytes());
		model.read(is, null, lang);
		return model;
	}

	public static Model readModelFromFile(String filePath, String lang, String rdfSyntax) {
		InputStream inputStream = FileManager.get().open(filePath);
		Model model = ModelFactory.createDefaultModel();
		model.read(inputStream, null, rdfSyntax);	 
		return model;
	}

	public static Document readDocumentFromXMLFile(String filePath) throws Exception {
		File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        return doc;
	}
}
