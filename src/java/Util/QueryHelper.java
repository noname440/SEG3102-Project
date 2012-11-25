/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Domain.*;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
public class QueryHelper {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OPSPU");
    private static EntityManager em = emf.createEntityManager();
    
    public static User searchUser(String userID) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userID = :userID");
        query.setParameter("userID", userID);
        query.setMaxResults(1);
        
        Collection<User> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<User>)queryResults).get(0);
    }
    
    public static User searchUser(String userID, String password) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userID = :userID AND u.password = :password");
        query.setParameter("userID", userID);
        query.setParameter("password", password);
        query.setMaxResults(1);
        
        Collection<User> queryResults = query.getResultList();
        if (queryResults.isEmpty()) {
            return null;
        }
        return ((List<User>)queryResults).get(0);
    }
    
    public static Student searchStudent(String studentID) {
        User u = searchUser(studentID);
        if (u instanceof Student) {
            return (Student)u;
        }
        else
            return null;
    }
    
    public static Instructor searchInstructor(String instructorID) {
        User u = searchUser(instructorID);
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
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();
    }
    
    public static void persist(Object o) {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }
    
}
