package top.importtao.xmlDomread;
import java.io.File;
/*
 * code by zt
 * */
import java.io.IOException;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDom{
	public static void main(String[] args){
		XmlDom xd = new XmlDom();
		//xd.XmlParer();
		xd.CreateXml();
	}
	public void XmlParer() {
		// TODO Auto-generated method stub
		//����Documentbuilefactory����
		
		try {
			//����documentbuilder����
			//DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = getDocumentBuilder().parse("lpl.xml");
			//��ȡ�ڵ㼯��
			NodeList teamlist = doc.getElementsByTagName("team");
			//�����ڵ�
			System.out.println("lck����"+teamlist.getLength()+"֧ս�ӣ�");
			for(int i=0;i<teamlist.getLength();i++){
				Node team = teamlist.item(i);//�ڵ�
				NamedNodeMap attr = team.getAttributes();//���Լ���
				/*ǰ�᣺֪���ڵ�����ֻ��һ�����Ժ�������
				 * Element team = (Element)teamlist.item(i);
				 * String attrvalue = team.getAttributes("id");
				 * System.out.print(attrvalue);
				 * */
				//�����ڵ�����
				for(int j=0;j<attr.getLength();j++){
					Node shuxin = attr.item(j);
					//duiyuan.getNodeName();
					//System.out.print(shuxin.getNodeName());
					System.out.println(shuxin.getNodeValue());
					//��ȡ�ӽڵ�
					NodeList duiyuan = team.getChildNodes();//�ӽڵ㼯
					int duiyuanshu=0;
					//�����ӽڵ�
					for(int k=0;k<duiyuan.getLength();k++){
						if(duiyuan.item(k).getNodeType()== Node.ELEMENT_NODE){
							String weizhi = duiyuan.item(k).getNodeName().toString();
							//String xuanshou = duiyuan.item(k).getFirstChild().getNodeValue().toString();
							String xuanshou = duiyuan.item(k).getTextContent().toString();//��ȡ�����ӽڵ��ֵ
							System.out.print(weizhi+":");
							System.out.println(xuanshou);
							duiyuanshu++;
						}
					}
					System.out.println(shuxin.getNodeValue()+"����"+duiyuanshu+"����Ա");
				}
				
			}
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DocumentBuilder getDocumentBuilder(){
		//����Documentbuilefactory����
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			 db = dbf.newDocumentBuilder();
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}
	//����XML
	public void CreateXml(){
		DocumentBuilder db = getDocumentBuilder();
		Document doc = db.newDocument();
		doc.setXmlStandalone(true);
		Element lcs = doc.createElement("lcs����");
		Element team1 = doc.createElement("team");
		Element top = doc.createElement("top");
		top.setTextContent("huntzer");
		team1.setAttribute("zhandui", "TSM");
		//����ӽڵ�
		doc.appendChild(lcs);
		lcs.appendChild(team1);
		team1.appendChild(top);
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(doc), new StreamResult(new File("lcs.xml")));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
