/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bruger
 */
public class MakeTestData {

      private static EntityManagerFactory emf;

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
        public static void main(String[] args) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
            Person p1 = new Person("fred", "Balle", "12345678");
            
            try{
            
            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
            
            
        }finally{
            em.close();
        }
    }
    
}
