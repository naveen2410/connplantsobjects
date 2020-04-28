package connplantsv1.connplantsv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetMIIResponse {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String stringURL = "http://9.220.9.130:50200/XMII/Illuminator?service=scheduler&mode=List&content-type=text/xml&IllumLoginName=som&IllumLoginPassword=password@1";
		
		try {
			System.out.print(new GetMIIResponse().executeGETService(stringURL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String executeGETService(String stringURL) throws IOException{
		String outValue = null;
		
		
		
			URL url = new URL(stringURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
			InputStream in = conn.getInputStream();
			
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Document doc = null;
			try {
				doc = (Document) db.parse(in);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			NodeList nodes = ((org.w3c.dom.Document) doc).getElementsByTagName("Row");
			//NodeList nodes = ((org.w3c.dom.Document) doc).getElementsByTagName("TimeZoneResponse");
	        
	        // iterate the employees
			
	        for (int i = 0; i < nodes.getLength(); i++) {
	           Element element = (Element) nodes.item(i);
	           
	           NodeList nl = element.getElementsByTagName("SITE");
	           Element line = (Element) nl.item(0);
	           String site = getCharacterDataFromElement(line);

	           nl = element.getElementsByTagName("SHOP_ORDER");
	           line = (Element) nl.item(0);
	           String shoporder = getCharacterDataFromElement(line);
	           
	           nl = element.getElementsByTagName("ITEM_BO");
	           line = (Element) nl.item(0);
	           String item = getCharacterDataFromElement(line);
	           
	           nl = element.getElementsByTagName("QTY_TO_BUILD");
	           line = (Element) nl.item(0);
	           String qty_to_build = getCharacterDataFromElement(line);
	           
	           nl = element.getElementsByTagName("QTY_DONE");
	           line = (Element) nl.item(0);
	           String qty_done = getCharacterDataFromElement(line);
	           
	           nl = element.getElementsByTagName("QTY_SCRAPPED");
	           line = (Element) nl.item(0);
	           String qty_scrapped = getCharacterDataFromElement(line);
	           
	           nl = element.getElementsByTagName("MODIFIED_DATE_TIME");
	           line = (Element) nl.item(0);
	           String modified_dt = getCharacterDataFromElement(line);
	           
	           MySQLConnection myserverCon = new MySQLConnection();
	           String returnMsg = myserverCon.insertMIISchData(site, shoporder, item, qty_to_build, qty_done, qty_scrapped, modified_dt);
	           
	         
		       System.out.println("Data is inserted "+returnMsg);
	        }
	        
			return outValue;
	}


	 public static String getCharacterDataFromElement(Element e) {
		    Node child = e.getFirstChild();
		    if (child instanceof CharacterData) {
		       CharacterData cd = (CharacterData) child;

		       return cd.getData();
		    }
		    return "?";
		  }
	}
