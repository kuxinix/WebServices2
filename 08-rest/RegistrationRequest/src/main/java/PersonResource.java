import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;


@Path("/persons")
public class PersonResource {
	// map with userName as first element and persons as second
	private static Map<String, Person> persons = new HashMap<String, Person>();
	
	@Context
	UriInfo uriInfo;
	
	public PersonResource() {
		persons.put("JanezNovak", new Person("JanezNovak", "geslo1"));
		persons.put("MarijaErjavec", new Person("MarijaErjavec", "geslo2"));
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> getPersons (){
		return displayPersons();
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> putPersons() {
		return displayPersons();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> postPersons() {
		return displayPersons();
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Person> deletePersons() {
		return displayPersons();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("addPerson/{userName}/{passwd}")
	public void addPerson(@PathParam("userName") String userName, @PathParam("passwd") String passwd) {

		persons.put(userName, new Person(userName, passwd));
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("getPerson/{userName}")
	public Person getPerson(@PathParam("userName") String userName) {
		System.out.println(uriInfo.getAbsolutePath());
		return persons.get(userName);
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/changepasswd/{userName}/{newPasswd}")
	public void changePasswd (@PathParam("userName") String userName, @PathParam("newPasswd") String newPasswd){
		Person person = persons.get(userName);
		System.out.println(userName + " " + newPasswd);
		
		if (person == null) {
			System.out.println("person not in database!");
			return;
		}
		
		person.setPassword(newPasswd);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/deletePerson/{userName}")
	public void deletePerson(@PathParam("userName") String userName) {
		Person person = persons.get(userName);
		if (person == null) {
			System.out.println("person does not exist in the database");
			return;
		}
		persons.remove(userName);
	}
	
	
	public List<Person> displayPersons() {
		List<Person> returnedPersons = new ArrayList<Person>();
	    returnedPersons.addAll( persons.values() );
	    return returnedPersons;
	}
	
}
