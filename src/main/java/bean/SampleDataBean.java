package bean;

import java.io.Serializable;
import java.util.Date;

public class SampleDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int Student_ID_Number;
	private int Enrollment_Status;
	private Date Enrollment_Status_Date;
	private String Student_Name;
	private String Student_Pronunciation;
	private Date Date_of_birth;
	private String Students_postal_code;
	private String Students_address;
	private String Phone_number;
	private String Individuals_mail_address;
	private String Guardians_name_in_Kanji;
	private String Guardians_Pronunciation;
	private String Guardians_postal_code;
	private String Guardians_address;
	private String Parent_Guardian_Phone_Number;
	private String Guardians_email_address;

	public void setStudent_ID_Number(int student_ID_Number) {
		this.Student_ID_Number = student_ID_Number;
	}

	public void setEnrollment_Status(int enrollment_Status) {
		this.Enrollment_Status = enrollment_Status;
	}

	public void setEnrollment_Status_Date(Date enrollment_Status_Date) {
		this.Enrollment_Status_Date = enrollment_Status_Date;
	}

	public void setStudent_Name(String student_Name) {
		this.Student_Name = student_Name;
	}

	public void setStudent_Pronunciation(String student_Pronunciation) {
		this.Student_Pronunciation = student_Pronunciation;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.Date_of_birth = date_of_birth;
	}

	public void setStudents_postal_code(String students_postal_code) {
		this.Students_postal_code = students_postal_code;
	}

	public void setStudents_address(String students_address) {
		this.Students_address = students_address;
	}

	public void setPhone_number(String phone_number) {
		this.Phone_number = phone_number;
	}

	public void setIndividuals_mail_address(String individuals_mail_address) {
		this.Individuals_mail_address = individuals_mail_address;
	}

	public void setGuardians_name_in_Kanji(String guardians_name_in_Kanji) {
		this.Guardians_name_in_Kanji = guardians_name_in_Kanji;
	}

	public void setGuardians_Pronunciation(String guardians_Pronunciation) {
		this.Guardians_Pronunciation = guardians_Pronunciation;
	}

	public void setGuardians_postal_code(String guardians_postal_code) {
		this.Guardians_postal_code = guardians_postal_code;
	}

	public void setGuardians_address(String guardians_address) {
		this.Guardians_address = guardians_address;
	}

	public void setParent_Guardian_Phone_Number(String parent_Guardian_Phone_Number) {
		this.Parent_Guardian_Phone_Number = parent_Guardian_Phone_Number;
	}

	public void setGuardians_email_address(String guardians_email_address) {
		this.Guardians_email_address = guardians_email_address;
	}

	public int getStudent_ID_Number() {
		return Student_ID_Number;
	}

	public int getEnrollment_Status() {
		return Enrollment_Status;
	}

	public Date getEnrollment_Status_Date() {
		return Enrollment_Status_Date;
	}

	public String getStudent_Name() {
		return Student_Name;
	}

	public String getStudent_Pronunciation() {
		return Student_Pronunciation;
	}

	public Date getDate_of_birth() {
		return Date_of_birth;
	}

	public String getStudents_postal_code() {
		return Students_postal_code;
	}

	public String getStudents_address() {
		return Students_address;
	}

	public String getPhone_number() {
		return Phone_number;
	}

	public String getIndividuals_mail_address() {
		return Individuals_mail_address;
	}

	public String getGuardians_name_in_Kanji() {
		return Guardians_name_in_Kanji;
	}

	public String getGuardians_Pronunciation() {
		return Guardians_Pronunciation;
	}

	public String getGuardians_postal_code() {
		return Guardians_postal_code;
	}

	public String getGuardians_address() {
		return Guardians_address;
	}

	public String getParent_Guardian_Phone_Number() {
		return Parent_Guardian_Phone_Number;
	}

	public String getGuardians_email_address() {
		return Guardians_email_address;
	}

}
