/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.Customer;
import javax.persistence.ElementCollection;

/**
 *
 * @author Bruger
 */
public class Tester {
    
    private static EntityManagerFactory emf;
    private static List<String> hobbies = new ArrayList();

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        hobbies.add("Jumping");
        Address a1 = new Address("vejen","vejle");
        Address a2 = new Address("ruten","roskilde");
        Address a3 = new Address("vænget","kerteminde");
       
        
        Persistence.generateSchema("pu", null);
        Customer c1 = new Customer("Fred", "Løwe", hobbies);
        
        c1.setAddress(a1);
        a1.setCustomer(c1);
        c1.addHobby("climbing");
        Customer c2 = new Customer("Arne", "Nougatgren",hobbies);
        c2.setAddress(a2);
        c2.addHobby("riding");
        Customer c3 = new Customer("Thorbjørn", "Mortensen", hobbies);
        c3.setAddress(a3);
        c1.addHobby("Comedy");
        
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Customer.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.getTransaction().commit();
            System.out.println(c1.getHobbies());
            System.out.println(c2.getFirstname());
            System.out.println(c3.getLastname());
            System.out.println(a1.getStreet());


        } finally {
            em.close();
        }
        
        
    }
}
