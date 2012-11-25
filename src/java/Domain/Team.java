/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Michael
 */
@Entity
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    private boolean status;
    private int minExempt;
    private int maxExempt;
    
    @ManyToOne
            CourseSection courseSection;
    
    @ManyToMany(
            mappedBy = "teamsAppliedTo",
            targetEntity=Student.class
            
            )
            List<Student> applicants;
    
    @ManyToMany(
            mappedBy = "teamsMemberOf",
            targetEntity=Student.class
            
            )
            List<Student> members;
    
    @ManyToOne
            Student liaison;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getMinExempt() {
        return minExempt;
    }

    public void setMinExempt(int minExempt) {
        this.minExempt = minExempt;
    }

    public int getMaxExempt() {
        return maxExempt;
    }

    public void setMaxExempt(int maxExempt) {
        this.maxExempt = maxExempt;
    }
    
    public CourseSection getCourseSection() {
        return courseSection;
    }
    
    public void setCourseSection(CourseSection course) {
        courseSection = course;
    }
    
    public List<Student> getApplicants() {
        return applicants;
    }
    
    public void addApplicant(Student s) {
        applicants.add(s);
    }
    
    public List<Student> getMembers() {
        return members;
    }
    
    public void addMember(Student s) {
        members.add(s);
    }
    
    public Student getLiaison() {
        return liaison;
    }
    
    public void setLiaison(Student s) {
        liaison = s;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domain.Team[ id=" + id + " ]";
    }
    
}
