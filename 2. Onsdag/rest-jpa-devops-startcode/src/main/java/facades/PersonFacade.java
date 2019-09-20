package facades;

import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    
    public interface IPersonFacade {
    public Person addPerson(String fName, String lName, String phone);  
    public Person deletePerson(int id) throws PersonNotFoundException;  
    public Person getPerson(int id) throws PersonNotFoundException;  
    public List<Person> getAllPersons();  
    public Person editPerson(Person p) throws PersonNotFoundException;  
}

    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long PersonCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return PersonCount;
        }finally{  
            em.close();
        }   
    }
    
        //TODO Remove/Change this before use
    public void addPerson(Person p){
        EntityManager em = emf.createEntityManager();
        
        try{
             em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }finally{  
            em.close();
        }   
    }
    
        //TODO Remove/Change this before use
    public void deletePerson(long id){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> query 
                    = em.createQuery("DELETE FROM Person p where p.id =:id", Person.class);
            query.setParameter("id", id).executeUpdate();
//            em.createQuery( "DELETE FROM Person p WHERE p.id < :q").setParameter(q, id).executeUpdate();
        }finally{  
            em.close();
        }   
    }
    
        //TODO Remove/Change this before use
    public Person getPerson(long id){
        EntityManager em = emf.createEntityManager();
        try{
            Person p1 = em.find(Person.class, id);
            return p1;
        }finally{  
            em.close();
        }   
    }
    
        //TODO Remove/Change this before use
    public List<Person> getAllPersons(){
        EntityManager em = emf.createEntityManager();
        try{
            List<Person> pers = em.createQuery("SELECT p from Person p").getResultList();
            return pers;
        }finally{  
            em.close();
        }   
    }
    
        //TODO Remove/Change this before use
    public void editPerson(Person p){
        EntityManager em = emf.createEntityManager();
       Person personNew = new Person();
       p.setFirstname(personNew.getFirstname());
       p.setLastname(personNew.getLastname());
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }finally{  
            em.close();
        }   
    }

}
