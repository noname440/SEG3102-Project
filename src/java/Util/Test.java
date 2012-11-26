/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Domain.Instructor;

/**
 *
 * @author Michael
 */
public class Test {
    
    public static void main(String[] args) {
        
        Instructor i = new Instructor();
        i.setFirstName("Blah");
        i.setLastName("HONKK");
        QueryHelper.init();
        QueryHelper.persist(i);
        System.out.println("done");
    }
    
}
