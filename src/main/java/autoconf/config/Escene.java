package autoconf.config;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import autoconf.domain.Phone;
import autoconf.domain.PhoneAccount;

public class Escene implements ConfigurationStrategy {
	
	private static final String TEMPLATE_FILE = "/tftpboot/templates/escene_template.xml";
	public static final String FILE_EXTENSION = ".xml";
	
	public String prepareConfig(Phone phone) {
		
		Map<Integer, Map<String, String>> lineSettings = new HashMap<>();
		
		for (PhoneAccount pa : phone.getPhoneAccounts()) {
			lineSettings.put(pa.getLineNumber(), new HashMap<String, String>());
			lineSettings.get(pa.getLineNumber()).put("username", pa.getAccount().getUsername());
			lineSettings.get(pa.getLineNumber()).put("password", pa.getAccount().getPassword());
		}
		
		File xmlFile = new File(TEMPLATE_FILE);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		String config = "";
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();	
			
			updateAttributeValue(doc, "systime", "SNTPAddress", phone.getNtpServer());
			updateAttributeValue(doc, "network", "NetConfigType", phone.getIsDhcp() ? "2" : "1");
			updateAttributeValue(doc, "network", "IPAddress", phone.getIpAddress());
			updateAttributeValue(doc, "network", "SubnetMask", phone.getSubnetMask());
			updateAttributeValue(doc, "network", "DefaultGateways", phone.getDefaultGw());
			updateAttributeValue(doc, "network", "IPDNS", phone.getDns1());
			updateAttributeValue(doc, "network", "SecondDNS", phone.getDns2());
			updateAttributeValue(doc, "privision", "Firmware", phone.getProvUrl());
			updateAttributeValue(doc, "password", "UserName", phone.getAdminLogin());
			updateAttributeValue(doc, "password", "Password", phone.getAdminPassword());
			
			List<String> usernames = new ArrayList<>();
			for (PhoneAccount pa : phone.getPhoneAccounts()) {
				usernames.add(pa.getAccount().getUsername());
			}
			updateAttributeValue(doc, "sipUsers", "sipUser", "UserName", usernames);
			updateAttributeValue(doc, "sipUsers", "sipUser", "UserNumber", usernames);
			
			List<String> passwords = new ArrayList<>();
			for (PhoneAccount pa : phone.getPhoneAccounts()) {
				passwords.add(pa.getAccount().getPassword());
			}
			updateAttributeValue(doc, "sipUsers", "sipUser", "Password", passwords);
			
			List<String> sipServers = new ArrayList<>();
			for (PhoneAccount pa : phone.getPhoneAccounts()) {
				sipServers.add(pa.getAccount().getSipServer());
			}
			updateAttributeValue(doc, "sipUsers", "sipUser", "DomainName", sipServers);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			
			config = result.getWriter().toString();
			
		} catch(SAXException | IOException | ParserConfigurationException | TransformerException ex) {
			ex.printStackTrace();
		}
		
		return config;
	}
	
	private void updateAttributeValue(Document doc, String tagName, String attrName, String value) {
		
		NodeList nodeList = doc.getElementsByTagName(tagName);
		for (int index = 0; index < nodeList.getLength(); index++) {
			Element element = (Element) nodeList.item(index);
			element.setAttribute(attrName, value);
		}
	}
	
	private void updateAttributeValue(Document doc, String tagGroup, String tagName, String attrName, List<String> values) {
		NodeList nodeList = doc.getElementsByTagName(tagGroup);
		for (int index = 0; index < nodeList.getLength(); index++) {
			Element element = (Element) nodeList.item(index);
			NodeList childList = element.getChildNodes();
			int valueIndex = 0;
			for (int i = 0; i < childList.getLength(); i++) {
				if (childList.item(i) instanceof Element) {
					Element elem = (Element) childList.item(i);
					if (elem.getNodeName() == tagName) {					
						elem.setAttribute(attrName, values.get(valueIndex));
						valueIndex++;
					}
				}
			}
		}
		
	}
	
	public String getFileExtension() {
		return FILE_EXTENSION;
	}
	
}
