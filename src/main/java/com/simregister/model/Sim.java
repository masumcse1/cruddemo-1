package com.simregister.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.simregister.validator.Msisdn;
import com.simregister.validator.Sex;
import com.simregister.validator.SimType;


public class Sim implements Serializable{
	
    private static final long serialVersionUID = 1L;

	@Msisdn
	@NotNull
	@CsvBindByName(column = "MSISDN")
	String msisdn;
	
	@SimType
	@NotNull
	@CsvBindByName(column = "SIM_TYPE")
	String simType;
	
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "should not any special character")
	@NotNull
	@CsvBindByName(column = "NAME")
	String name;

	@Past(message = "Can not future date or today date")
	@NotNull
	@CsvDate(value = "MM/dd/yyyy")
	@CsvBindByName(column = "DATE_OF_BIRTH")
	LocalDate  dateOfBirth;

	@Sex
	@NotNull
	@CsvBindByName(column = "GENDER")
	String gender;
	
	@Size(min = 20, message = "Number of characters should be in 20 character")
	@NotNull
	@CsvBindByName(column = "ADDRESS")
	String address;
	
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$", message = "should be alpha numeric")
	@NotNull
	@CsvBindByName(column = "ID_NUMBER")
	String idNumber;

	
	
	
	


	public Sim(@Msisdn @NotNull String msisdn, @NotNull String simType,
			@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "should not any special character") @NotNull String name,
			@Past(message = "Can not future date or today date") @NotNull LocalDate dateOfBirth, @NotNull String gender,
			@Size(min = 20, message = "Number of characters should be in 20 character") @NotNull String address,
			@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$", message = "should be alpha numeric") @NotNull String idNumber) {
		super();
		this.msisdn = msisdn;
		this.simType = simType;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.idNumber = idNumber;
	}

	public Sim() {
		super();
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSimType() {
		return simType;
	}

	public void setSimType(String simType) {
		this.simType = simType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
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
		Sim other = (Sim) obj;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		return true;
	}

}
