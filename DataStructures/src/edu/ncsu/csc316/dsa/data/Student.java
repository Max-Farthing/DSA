package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** student's first name **/
	private String first;
	/** student's last name **/
	private String last;
	/** student's id **/
	private int id;
	/** student's credit hours **/
	private int creditHours; 
	/** student's gpa **/
	private double gpa;
	/** student's unity ID **/
	private String unityID;
	
	/**
	 * constructor for Student object
	 * @param first student first name
	 * @param last student last name
	 * @param id student ID
	 * @param creditHours student Credits
	 * @param gpa student GPA
	 * @param unityID student Unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * getter for first name
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * setter for first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * getter for last name
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * setter for last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * getter for id 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter for id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter for credit hours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * setter for credit hours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * getter for GPA
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * setter for GPA
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * getter for UNITY id
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * setter for UNITY ID
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * overrides hashcode to be used for student objects
	 * @return int of hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(creditHours, first, gpa, id, last, unityID);
	}

	/**
	 * equals method for comparing studnet objects
	 * @param obj object
	 * @return boolean if objects are equal
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student that = (Student) obj;
			return this.first.equals(that.first) &&
					this.last.equals(that.last) && 
					this.id == that.id;
		} else {
			return false;
		}
	}
	
	/**
	 * compares student objects based upon last name and then unity ID
	 * @param other other student
	 * @return int of comparison
	 */
	public int compareTo(Student other) {
		int a = this.getLast().compareTo(other.getLast());
		int b = this.getFirst().compareTo(other.getFirst());
		int c = this.getUnityID().compareTo(other.getUnityID());
		
		if(a != 0) {
			return a;
		} else if(a == 0 && b != 0) {
			return b;
		} else {
			return c;
		}
	}

	/**
	 * Overriding toString to help with debugging
	 * @return String of student
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}
	
	
}
