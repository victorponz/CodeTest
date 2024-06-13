import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseXML {
	private Document doc;
	private Element root;
	private String resultsPath;
	public ParseXML(String resultsPath)	throws ParserConfigurationException,
			IOException,
			SAXException{
		this.resultsPath = resultsPath;
		this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(System.getProperty("user.dir") + "/io/" + resultsPath + "/TEST-junit-vintage.xml");
		this.root = this.doc.getDocumentElement(); // apuntarà al elemento raíz.

	}

	public void parse() throws IOException {
		//System.out.println(hijos.item(0).getNodeName()); // el primer hijo es el retorno de carro.
		Log log = new Log(this.resultsPath);
		NodeList failures = this.root.getElementsByTagName("failure");
		log.write("<failures>");
        for (int i = 0; i < failures.getLength(); i++) {
        	Element el = (Element) failures.item(i);
			log.writeTestFailure(el.getAttribute("message"));
        	System.out.println(el.getAttribute("message"));
        }
		log.write("</failures>");
		log.write("<errors>");

		NodeList errors= this.root.getElementsByTagName("error");
		for (int i = 0; i < errors.getLength(); i++) {
			Element el = (Element) errors.item(i);
			log.writeTestFailure(el.getAttribute("type"));
			System.out.println(el.getAttribute("type"));
		}
		log.write("</errors>");
	}
}
