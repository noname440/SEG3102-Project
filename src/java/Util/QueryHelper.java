/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Domain.*;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;

/**
 *
 * @author Michael
 */
@PersistenceContext(name="persistence/dbunit", unitName="Del3PU")
public class QueryHelper {
    
    @Resource 
    private static UserTransaction utx; 
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Del3PU");
    private static EntityManager em = emf.createEntityManager();
    //private static Context envCtx;
    //private static EntityManager em;
    
    public static void init() {
        try {
            //envCtx = (Context) new InitialContext().lookup("java:comp/env");
            //em = (EntityManager) envCtx.lookup("persistence/dbunit");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static TMSUser searchUser(Long userID) {
        Query query = em.createQuery("SELECT u FROM TMSUser u WHERE u.userID = :userID");
        query.setParameter("userID", userID);
        query.setMaxResults(1);
        
        Collection<TMSUser> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<TMSUser>)queryResults).get(0);
    }
    
    public static TMSUser searchUser(String userID, String password) {
        Query query = em.createQuery("SELECT u FROM TMSUser u WHERE u.userID = :userID AND u.password = :password");
        query.setParameter("userID", userID);
        query.setParameter("password", password);
        query.setMaxResults(1);
        
        Collection<TMSUser> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<TMSUser>)queryResults).get(0);
    }
    
    public static Student searchStudent(Long studentID) {
        TMSUser u = searchUser(studentID);
        if (u instanceof Student) {
            return (Student)u;
        }
        else
            return null;
    }
    
    public static Instructor searchInstructor(Long instructorID) {
        TMSUser u = searchUser(instructorID);
        if (u instanceof Instructor) {
            return (Instructor)u;
        }
        else
            return null;
    }
    
    public static Team searchTeam(String teamID) {
        Query query = em.createQuery("SELECT t FROM Team t WHERE t.id = :teamID");
        query.setParameter("teamID", teamID);
        query.setMaxResults(1);
        
        Collection<Team> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<Team>)queryResults).get(0);
    }
    
    public static CourseSection searchCourseSection(String courseID) {
        Query query = em.createQuery("SELECT c FROM CourseSection c WHERE c.courseID = :courseID");
        query.setParameter("courseID", courseID);
        query.setMaxResults(1);
        
        Collection<CourseSection> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<CourseSection>)queryResults).get(0);
    }
    
    public static void merge(Object o) {
        try{
            utx.begin();
            em.merge(o);
            utx.commit();
        } catch(Exception e) {
            e.printStackTrace();
        }       
    }
    
    public static void persist(Object o) {
        try{
            utx.begin();
            em.persist(o);
            utx.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } 
        /*
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        */
    }
    
}
