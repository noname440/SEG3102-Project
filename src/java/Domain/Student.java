/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity
public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studyProgram;
    
    @ManyToMany(
            targetEntity=CourseSection.class
            )
    @JoinTable(
            name="ENROLLMENT",
            joinColumns=@JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns=@JoinColumn(name="COURSE_ID")
            )
            List<CourseSection> enrolledIn;
    
    @ManyToMany(
            targetEntity=Team.class
            )
    @JoinTable(
            name="TEAM_APPLICATIONS",
            joinColumns=@JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns=@JoinColumn(name="TEAM_ID")
            )
            List<Team> teamsAppliedTo;
    
    @ManyToMany(
            targetEntity=Team.class
            )
    @JoinTable(
            name="TEAM_MEMBERSHIP",
            joinColumns=@JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns=@JoinColumn(name="TEAM_ID")
            )
            List<Team> teamsMemberOf;
    
    @OneToMany(mappedBy="liaison")
            List<Team> teamsLiaisonOf;
    
    public String getStudyProgram() {
        return studyProgram;
    }
    
    public void setStudyProgram(String prog) {
        this.studyProgram = prog;
    }
    
    public List<CourseSection> getCourses() {
        return enrolledIn;
    }
    
    public void addCourse(CourseSection course) {
        enrolledIn.add(course);
    }
    
    public List<Team> getTeamsAppliedTo(){
        return teamsAppliedTo;
    }
    
    public void addTeamAppliedTo(Team team) {
        teamsAppliedTo.add(team);
    }
    
    public List<Team> getTeamsMemberOf(){
        return teamsMemberOf;
    }
    
    public void addTeamMemberOf(Team team) {
        teamsMemberOf.add(team);
    }
    
    public List<Team> getTeamsLiaisonOf(){
        return teamsLiaisonOf;
    }
    
    public void addTeamLiaisonOf(Team team) {
        teamsLiaisonOf.add(team);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.Student[ userID=" + userID + " ]";
    }
    
}
