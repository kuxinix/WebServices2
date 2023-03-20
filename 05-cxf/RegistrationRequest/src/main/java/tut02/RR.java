package tut02;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "RR", targetNamespace = "http://tut02/")
public interface RR {
	  
	    @WebMethod(operationName = "regReq", action = "urn:RegReq")
		public String regReq(boolean receipt);

}

