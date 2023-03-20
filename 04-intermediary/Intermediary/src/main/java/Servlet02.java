
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPHeader;
import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Servlet02
 */
public class Servlet02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try {
			SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
			SOAPConnection soapc = soapcf.createConnection();
			
			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage soapm = mf.createMessage(null, request.getInputStream());
			//System.out.println(soapm);
				
			SOAPPart soapp = soapm.getSOAPPart();
			SOAPEnvelope soape = soapp.getEnvelope();
			SOAPBody soapb = soape.getBody();
	
			soape.getHeader().detachNode();
			QName name = new QName("http://tut02/", "regReq", "temp");
			SOAPElement soapel = soapb.addBodyElement(name);
	
			
			soapel.addChildElement(
			    new QName("http://tut02/", "arg0", "temp")).addTextNode("true");
			
			String endpoint = "http://127.0.0.1:8000/RR";
			SOAPMessage res= soapc.call(soapm, endpoint);
			soapc.close();
			
			//final StringWriter sw = new StringWriter();
	
	       
			
			SOAPBody responseBody = res.getSOAPBody();
			//System.out.println("here");
			
			if (responseBody.hasFault()) {
			    System.out.println(responseBody.getFault().getFaultString()); 
			} else {
				
				SOAPBodyElement AddResponse = (SOAPBodyElement)
				       responseBody.getElementsByTagName("return").item(0);
				//SOAPBodyElement AddResult = (SOAPBodyElement)
				 //      AddResponse.getChildElements(AddResultName).next();
				
				//response.setContentType("text/xml");

				response.setContentType("text/xml");
				PrintWriter printWriter = response.getWriter();
				response.addHeader("responseHeader", "header");
				if (AddResponse.getValue() == "Denied") {
					
					printWriter.print(AddResponse.getValue());
					printWriter.print("Please try again!");
				} else {
					printWriter.print(AddResponse.getValue());
					printWriter.print("Your registration was successful");
				}
				
			
				//System.out.println(AddResponse.getValue());
				
				
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
