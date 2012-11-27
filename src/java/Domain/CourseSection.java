/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Michael
 */
@Entity
public class CourseSection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String courseCode;
    private String semester;
    private String section;
    private String courseName;
    private String description;
    private int maxStudents;
    private int minStudents;
    //@Temporal(javax.persistence.TemporalType.DATE)
    private String startDate;
   // @Temporal(javax.persistence.TemporalType.DATE)
    private String endDate;
    
    @OneToMany
            //@JoinColumn(name="student_fk")
            List<Team> teams = new ArrayList<Team>();
    
    @ManyToMany (targetEntity = Student.class)
            List<Student> enrolledStudents= new ArrayList<Student>();
    
    public List<Team> getTeams(){
        return teams;
    }
    
    public void addTeam(Team team) {
        teams.add(team);
    }
    
    public void addStudent(Student s) {
        enrolledStudents.add(s);
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String id) {
        this.courseCode = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        return hash;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getMinStudents() {
        return minStudents;
    }

    public void setMinStudents(int minStudents) {
        this.minStudents = minStudents;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseSection)) {
            return false;
        }
        CourseSection other = (CourseSection) object;
        if ((this.courseCode == null && other.courseCode != null) || (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.CourseSection[ id=" + courseCode + " ]";
    }

    
}
