/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruger
 */
public class StudentFacade {
    
        private static StudentFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private StudentFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static StudentFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Student> getAllStudents(){
          EntityManager em = getEntityManager();
        try{
            List<Student> stu = em.createNamedQuery("Student.findAll").getResultList();
            return stu;
        }finally{
            em.close();
        }
        
    }
    
}
