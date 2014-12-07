package com.example.appointmentadvisor;

public class Professor {
	private String name;
	private String[] days;
	
	public Professor(String name, String[] days){
		this.name=name;
		this.days=days;
	}
	
	public String[] getDays()
	{
		return days;
	}
	
	

}
