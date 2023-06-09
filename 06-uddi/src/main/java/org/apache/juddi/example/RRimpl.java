/*
 * Copyright 2001-2010 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.juddi.example;

import javax.jws.WebService;

import org.apache.juddi.v3.annotations.UDDIService;
import org.apache.juddi.v3.annotations.UDDIServiceBinding;

/**
 * This example show you how to use UDDI Annotations to decorate a class.
 * When the Servlet Listener
 * 
 */

@UDDIService(
		businessKey="uddi:newbusinesskey:$department-asf",
		serviceKey="uddi:myrrservice:services-rr$department", 
		description = "Registration Request test service")
@UDDIServiceBinding(
		bindingKey="uddi:myrrservicekey",
	    description="WSDL endpoint for the myrrservice Service. This service is used for "
				  + "testing the jUDDI annotation functionality",
	    accessPointType="wsdlDeployment",
	    accessPoint="http://${serverName}:${serverPort}/uddi-annotations/services/rr?wsdl")



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
