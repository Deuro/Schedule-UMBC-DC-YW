package com.example.appointmentadvisor;

public class Timeslot 
{
	private String time;
	private boolean isOpen;
	private String minClass;
	
	public Timeslot(String time, boolean isOpen,String minClass){
		this.time=time;
		this.isOpen=isOpen;
		this.minClass=minClass;
	}
	
	public String getTime()
	{return time;}
	
	public boolean isOpen()
	{return isOpen;}
	
	public String getminClass()
	{return minClass;}
	
	public String toString(){
		String flag ="";
		if (isOpen)
		{flag = "t";}
		else
		{flag = "f";}
		
		String str = time + "\n" + flag +"\n"+minClass+"\n";
		return str;
	}
	
	public void setisOpen(boolean flag){
		this.isOpen = flag;
	}

}
