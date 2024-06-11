import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public static void main(String[] args)
			throws ParserConfigurationException, 
					FileNotFoundException, 
					IOException, 
					SAXException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("./src/main/java/io/results/TEST-junit-vintage.xml");
		Element root = doc.getDocumentElement(); // apuntarà al elemento raíz.
		//System.out.println(hijos.item(0).getNodeName()); // el primer hijo es el retorno de carro.
		NodeList testCases = root.getElementsByTagName("failure");
        for (int i = 0; i < testCases.getLength(); i++) {
        	Element el = (Element) testCases.item(i);
        	System.out.println(el.getAttribute("message"));
        }
       
	}
}
