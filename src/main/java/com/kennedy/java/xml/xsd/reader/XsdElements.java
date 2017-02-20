package com.kennedy.java.xml.xsd.reader;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XsdElements {

	@Test
	public void main() {
		System.out.println("BEGINNING EXPORT!");
		InputStream file1 = XsdElements.class.getClassLoader()
				.getResourceAsStream("referenceXsd/SalaryDeclaration.xsd");
		InputStream file2 = XsdElements.class.getClassLoader()
				.getResourceAsStream("referenceXsd/SalaryDeclarationContainer.xsd");
		InputStream file3 = XsdElements.class.getClassLoader()
				.getResourceAsStream("referenceXsd/SalaryDeclarationServiceTypes.xsd");
		Map<String, Map<String, ComplexTypeXsd>> parentNode_childNode = new HashMap<>();
		Map<String, SimpleTypeXsd> simpleType = new HashMap<>();
		try {
			// parse the document
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc1 = docBuilder.parse(file1);
			Document doc2 = docBuilder.parse(file2);
			Document doc3 = docBuilder.parse(file3);

			NodeList complexType1 = doc1.getElementsByTagName("xs:complexType");
			NodeList complexType2 = doc2.getElementsByTagName("xs:complexType");
			NodeList complexType3 = doc3.getElementsByTagName("xs:complexType");

			parentNode_childNode = readerTypeToMap(parentNode_childNode, complexType1);
			parentNode_childNode = readerTypeToMap(parentNode_childNode, complexType2);
			parentNode_childNode = readerTypeToMap(parentNode_childNode, complexType3);
			
			
			complexType1 = doc1.getElementsByTagName("xs:simpleType");
			complexType2 = doc2.getElementsByTagName("xs:simpleType");
			complexType3 = doc3.getElementsByTagName("xs:simpleType");
			
			simpleType = readerSimpTypeToMap(simpleType, complexType1);
			simpleType = readerSimpTypeToMap(simpleType, complexType2);
			simpleType = readerSimpTypeToMap(simpleType, complexType3);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("**************************************");
		printOutNodeInMap("SalaryDeclarationType", parentNode_childNode);
		System.out.println("**************************************");
		printOutSimpleNodeInMap(simpleType);
		System.out.println("**************************************");
		
	}

	private void printOutSimpleNodeInMap(Map<String, SimpleTypeXsd> simpleType) {
		for (Entry<String, SimpleTypeXsd> entry : simpleType.entrySet()) {
			System.out.println(entry.getKey());
			SimpleTypeXsd type = entry.getValue();
			System.out.println("  " + type.toString());
		}
		
	}

	private Map<String, SimpleTypeXsd> readerSimpTypeToMap(Map<String, SimpleTypeXsd> simpleType, NodeList node) {
		for (int i = 0; i < node.getLength(); i++) {
			Element first = (Element) node.item(i);
			if (first.hasAttributes()) {
				String name = first.getAttribute("name");
				
				Element child = (Element)first.getElementsByTagName("xs:restriction").item(0);
				String base = child.getAttribute("base");
				base = base.substring(base.indexOf(":") + 1, base.length());
				
				NodeList types = child.getChildNodes();
				SimpleTypeXsd simpleTypeXsd = SimpleTypeXsd.createNew().withName(name).withType(base);
				for (int j = 0; j < types.getLength(); j++) {
					if (types.item(j) instanceof Element == false){
					       continue;
					}
					Element el = (Element)types.item(j);
					String tag = el.getTagName();
					String value = el.getAttribute("value");
					tag = tag.substring(tag.indexOf(":") + 1, tag.length());
					simpleTypeXsd.withValue(tag, value);
				}
				simpleType.put(name, simpleTypeXsd);
			}
		}
		
		return simpleType;
	}

	private void printOutNodeInMap(String name, Map<String, Map<String, ComplexTypeXsd>> nodeTypeList) {
		System.out.println("<" + name + ">");
		printOutNode(name, nodeTypeList.get(name), nodeTypeList, 1);
		System.out.println("</" + name + ">");
	}

	private void printOutNode(String name, Map<String, ComplexTypeXsd> childNode, Map<String, Map<String, ComplexTypeXsd>> nodeTypeList, int index) {
		StringBuilder node = new StringBuilder();
		for (int i = 0; i < index; i++) {
			node.append("  ");
		}
		String space = node.toString();
		if (childNode.size() == 0) {
			System.out.println(space + "<" + name + "/>");
			return;
		}
		for (Entry<String, ComplexTypeXsd> entry : childNode.entrySet()) {
			ComplexTypeXsd object = entry.getValue();
			System.out.println(space + "<" + object.getNameType() + " -- " + object.getType() + " -- " + object.isRequired() + ">");
			if (nodeTypeList.containsKey(object.getType())) {
				printOutNode(object.getType(), nodeTypeList.get(object.getType()), nodeTypeList, index + 1);
			}
			System.out.println(space + "</" + object.getNameType() + ">");
		}
		
	}

	private Map<String, Map<String, ComplexTypeXsd>> readerTypeToMap(Map<String, Map<String, ComplexTypeXsd>> parentNode_childNode, NodeList node) {
		for (int i = 0; i < node.getLength(); i++) {
			Element first = (Element) node.item(i);
			if (first.hasAttributes()) {
				String name = first.getAttribute("name");
				Map<String, ComplexTypeXsd> childNode = getChildNodeMap(first, name);
				parentNode_childNode.put(name, childNode);
			}
		}
		
		return parentNode_childNode;
	}

	private Map<String, ComplexTypeXsd> getChildNodeMap(final Element element, String parentNodeName) {
		Map<String, ComplexTypeXsd> childNode = new HashMap<>();
		NodeList ele = element.getOwnerDocument().getElementsByTagName("xs:extension");
		for (int i = 0; i < ele.getLength(); i++) {
			Element first = (Element) ele.item(i);
			if (first.hasAttributes()) {
				String name = parentNodeName + "(s/es)";
				String type = getTypeOfNode(first, "base");
				String required = first.getAttribute("minOccurs");
				String parentNode = getPath(first);
				if (parentNode.equals(parentNodeName)) {
					ComplexTypeXsd complexTypeXsd = ComplexTypeXsd.createNew().withNameType(name).withType(type).isRequired(required.equals("0") ? false : true);
					childNode.put(name, complexTypeXsd);
				}
			}
		}
		
		ele = element.getOwnerDocument().getElementsByTagName("xs:element");
		for (int i = 0; i < ele.getLength(); i++) {
			Element first = (Element) ele.item(i);
			if (first.hasAttributes()) {
				String name = first.getAttribute("name");
				String type = getTypeOfNode(first, "type");
				String required = first.getAttribute("minOccurs");
				String parentNode = getPath(first);
				if (parentNode.equals(parentNodeName)) {
					ComplexTypeXsd complexTypeXsd = ComplexTypeXsd.createNew().withNameType(name).withType(type).isRequired(required.equals("0") ? false : true);
					childNode.put(name, complexTypeXsd);
				}
			}
		}

		NodeList att = element.getOwnerDocument().getElementsByTagName("xs:attribute");
		for (int i = 0; i < att.getLength(); i++) {
			Element first = (Element) att.item(i);
			if (first.hasAttributes()) {
				String name = first.getAttribute("name");
				String type = getTypeOfNode(first, "type");
				String required = first.getAttribute("use");
				String parentNode = getPath(first);
				if (parentNode.equals(parentNodeName)) {
					ComplexTypeXsd complexTypeXsd = ComplexTypeXsd.createNew().withNameType(name).withType(type).isRequired(required.equals("0") ? false : true);
					childNode.put(name, complexTypeXsd);
				}
			}
		}
		return childNode;
	}

	private String getTypeOfNode(Element first, String string) {
		String type = first.getAttribute(string);
		if (type.contains(":")) {
			return type.substring(type.indexOf(":") + 1, type.length());
					
		}
		return first.getAttribute(string);
	}

	private String getPath(Element first) {
		Node parentNode = first.getParentNode();
		String path = parentNode.toString();
		while (!path.contains("complexType")) {
			parentNode = parentNode.getParentNode();
			if (parentNode == null) {
				return "";
			}
			path = parentNode.toString();
		}
		NamedNodeMap a = parentNode.getAttributes();
		try {
			Node b = a.getNamedItem("name");
			if (b == null) {
				return "";
			}
			return b.getNodeValue();
		} catch (Exception e) {
			System.out.println("NPE:" + a.getNamedItem("name"));
			throw e;
		}
	}
}
