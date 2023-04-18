import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	
	private String userName;
	private String password;
	
	public Person() {
		this.userName = "User Name";
		this.password = "some pswd";
	}
	
	public Person(String usrName, String pswd) {
		this.userName = usrName;
		this.password = pswd;
	}
	
	@XmlElement(name="userName")
	private String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@XmlElement(name="passwd")
	private String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
