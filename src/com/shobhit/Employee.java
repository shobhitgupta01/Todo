package com.shobhit;
 
//Persistent class for Employee

public class Employee {
	
	private int id;
	private String name,password;
	
	//setter methods
	
	public void setId(int id)
	{
		this.id=id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setPassword(String pass)
	{
		password=pass;
	}
	
	//getter methods
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPassword()
	{
		return this.password;
	}

}
