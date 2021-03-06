/**
 * 
 */
package com.gvit.gims.attendance.dtos;

import java.io.Serializable;

import java.util.ArrayList;

import com.gvit.gims.attendance.Student;
/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class ClassInformationDTO implements Serializable {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 7883193096125835452L;
	
	private String group;
	
	//TODO change to branch
	private String department;
	
	private String year;
	
	private String section;
	
	private String period;
	
	private String subject;

	private ArrayList<Student> absentees;
	
	private String attendanceDate;
	
	public ClassInformationDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public ArrayList<Student> getAbsentees() {
		return absentees;
	}

	public void setAbsentees(ArrayList<Student> absentees) {
		this.absentees = absentees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((absentees == null) ? 0 : absentees.hashCode());
		result = prime * result
				+ ((attendanceDate == null) ? 0 : attendanceDate.hashCode());
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassInformationDTO other = (ClassInformationDTO) obj;
		if (absentees == null) {
			if (other.absentees != null)
				return false;
		} else if (!absentees.equals(other.absentees))
			return false;
		if (attendanceDate == null) {
			if (other.attendanceDate != null)
				return false;
		} else if (!attendanceDate.equals(other.attendanceDate))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClassInformationDTO [group=" + group + ", department="
				+ department + ", year=" + year + ", section=" + section
				+ ", period=" + period + ", subject=" + subject
				+ ", absentees=" + absentees + ", attendanceDate="
				+ attendanceDate + "]";
	}
}
