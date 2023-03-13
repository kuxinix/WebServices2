package tut02;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

public class SimpleClient {

	public static void main(String[] args) throws Exception {
		SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
		SOAPConnection soapc = soapcf.createConnection();

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage soapm = mf.createMessage();
			
		SOAPPart soapp = soapm.getSOAPPart();
		SOAPEnvelope soape = soapp.getEnvelope();
		SOAPBody soapb = soape.getBody();

		soape.getHeader().detachNode();
		QName name = new QName("http://tut02/", "regReq", "temp");
		SOAPElement soapel = soapb.addBodyElement(name);

		soapel.addChildElement(
		    new QName("http://tut02/", "arg0", "temp")).addTextNode("true");
		
		String endpoint = "http://127.0.0.1:8000/RR";
		SOAPMessage response = soapc.call(soapm, endpoint);
		soapc.close();
		
		final StringWriter sw = new StringWriter();

       
		
		SOAPBody responseBody = response.getSOAPBody();
		//System.out.println("here");
		
		if (responseBody.hasFault()) {
		    System.out.println(responseBody.getFault().getFaultString()); 
		} else {
			
			//QName AddResponseName = new QName("http://tut02/", "regReqResponse");
			//System.out.println(responseBody.getElementsByTagName("return").item(0).getTextContent());
			/*
			QName AddResultName = new QName("http://tut02/", "return");
			 try {
		            TransformerFactory.newInstance().newTransformer().transform(
		                new DOMSource(response.getSOAPBody().getLastChild()),
		                new StreamResult(sw));
		        } catch (TransformerException e) {
		            throw new RuntimeException(e);
		        }

            // Now you have the XML as a String:
            System.out.println(sw.toString());
			System.out.println(responseBody.getChildElements(AddResponseName));
			//System.out.println(responseBody.getChildElements(AddResultName).next().getValue());
*/
			SOAPBodyElement AddResponse = (SOAPBodyElement)
			       responseBody.getElementsByTagName("return").item(0);
			//SOAPBodyElement AddResult = (SOAPBodyElement)
			 //      AddResponse.getChildElements(AddResultName).next();
		
			System.out.println(AddResponse.getValue());
		}

	}

}
