package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
 
    
    public PersonResource(){
        if(FACADE.getPersonCount()==0){
            FACADE.addPerson(new Person("fred","mortensen", "12345"));
        }
    }
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonById(@PathParam("id") int id) throws PersonNotFoundException {
        Person p1 = FACADE.getPerson(id);
        if(p1 == null){
            throw new PersonNotFoundException("id not found");
        }
      return gson.toJson(p1);
    }
    
   
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
                return gson.toJson(FACADE.getAllPersons());
    }

//dont think you can change the phonenumber, only the first and last name
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editPerson(@PathParam("id") int id) {
        FACADE.editPerson(FACADE.getPerson(id));
        return "Person chnaged";
    }
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String personAsJson) {
        Person personNew = gson.fromJson(personAsJson, Person.class);
        return gson.toJson(personNew);
    }
    

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String deletePerson(@PathParam("id") int id) {
      FACADE.deletePerson(id);
      return "deleted";
    }
 
}
