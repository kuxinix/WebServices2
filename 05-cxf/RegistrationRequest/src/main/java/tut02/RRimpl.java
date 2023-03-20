package tut02;

import javax.jws.WebService;

@WebService(targetNamespace = "http://tut02/", endpointInterface = "tut02.RR", portName = "RRimplPort", serviceName = "RRimplService")
public class RRimpl implements RR {

	public String regReq(boolean receipt) {
		if(receipt) {
			return "Confirmed";
		} else {
			return "Denied";
		}
	}

}
