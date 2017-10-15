package com.shobhit;

//Persistent class for Todo
public class Todo {
	
	private int id;
	private String content;
	private String status;
	
	//getter methods
	public int getId()
	{
		return this.id;
	}
	public String getContent()
	{
		return this.content;
	}
	public String getStatus()
	{
		return this.status;
	}
	
	//setter methods
	public void setId(int id)
	{
		this.id=id;
	}
	
	public void setContent(String content)
	{
		this.content=content;
	}
	
	public void setStatus(String status)
	{
		this.status=status;
	}

}
