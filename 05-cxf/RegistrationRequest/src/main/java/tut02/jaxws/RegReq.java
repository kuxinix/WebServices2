
package tut02.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.5.5
 * Mon Mar 20 16:49:17 CET 2023
 * Generated source version: 3.5.5
 */

@XmlRootElement(name = "regReq", namespace = "http://tut02/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regReq", namespace = "http://tut02/")

public class RegReq {

    @XmlElement(name = "arg0")
    private boolean arg0;

    public boolean getArg0() {
        return this.arg0;
    }

    public void setArg0(boolean newArg0)  {
        this.arg0 = newArg0;
    }

}

