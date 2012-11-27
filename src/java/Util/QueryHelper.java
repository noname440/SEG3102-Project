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
    
    
    //private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Del3PU");
    //private static EntityManager em = emf.createEntityManager();
    private static Context envCtx;
    private static EntityManager em;
    
    public static void init() {
        try {
            envCtx = (Context) new InitialContext().lookup("java:comp/env");
            em = (EntityManager) envCtx.lookup("persistence/dbunit");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static TMSUser searchUser(String userID) {
        init();
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
        init();
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
    
    public static Student searchStudent(String studentID) {
        TMSUser u = searchUser(studentID);
        if (u instanceof Student) {
            return (Student)u;
        }
        else
            return null;
    }
    
    public static Instructor searchInstructor(String instructorID) {
        TMSUser u = searchUser(instructorID);
        if (u instanceof Instructor) {
            return (Instructor)u;
        }
        else
            return null;
    }
    
    public static Team searchTeam(Long teamID) {
        init();
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
        init();
        Query query = em.createQuery("SELECT c FROM CourseSection c WHERE c.courseID = :courseID");
        query.setParameter("courseID", courseID);
        query.setMaxResults(1);
        
        Collection<CourseSection> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<CourseSection>)queryResults).get(0);
    }

    
    
    
    public static void merge(Object o,UserTransaction utx) {
        try{
            init();
            utx.begin();
            em.merge(o);
            utx.commit();
        } catch(Exception e) {
            try {         
                    utx.rollback();
            } catch (Exception f){}
            e.printStackTrace();
        }       
    }
    
    
    public static void persist(Object o,UserTransaction utx) {
       
        try{
            init();
            utx.begin();
            em.persist(o);
            utx.commit();
        } catch(Exception e) {
            try {         
                    utx.rollback();
            } catch (Exception f){}
            e.printStackTrace();
        } 
        
    
        /*
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        */
    }
    
    public static void populateDatabase(UserTransaction utx){
            CourseSection course = new CourseSection();
            course.setCourseID("SEG3102");
            course.setCourseName("design");
            course.setDescription("fun");
            course.setMinStudents(1);
            course.setMaxStudents(5);
            course.setSection("A");
            course.setStartDate("2012-11-11");
            course.setEndDate("2012-12-12");
            
            persist(course,utx);
            
        }
}
