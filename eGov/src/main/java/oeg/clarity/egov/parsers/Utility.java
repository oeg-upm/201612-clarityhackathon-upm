package oeg.clarity.egov.parsers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Utility {

	public static Model readModelFromString(String modelText, String lang) {
		Model model = ModelFactory.createDefaultModel();
		InputStream is = new ByteArrayInputStream(modelText.getBytes());
		model.read(is, null, lang);
		return model;
	}

	public static Model  readModelFromFile(String filePath, String lang, String rdfSyntax) {
		InputStream inputStream = FileManager.get().open(filePath);
		Model model = ModelFactory.createDefaultModel();
		model.read(inputStream, null, rdfSyntax);	 
		return model;
	}

}
