package domain;

import java.util.Random;

public class Request {
	static int numerator = 0;
	private final int requestId;
	
	private final double initialWorkToDo;
	private double remainingWorkToDo;
	
	private String address;
	
	private final String defaultAddress = "192";
	
	public Request(double _initialWorkToDo)
	{
		requestId = ++numerator;
		
		initialWorkToDo = _initialWorkToDo;
		remainingWorkToDo = initialWorkToDo;
		
		setAddress();
	}
	
	public boolean CheckIfDone()
	{
		return remainingWorkToDo <= 0;
	}
	
	public void Work(double performanceFactor)
	{
		if(!CheckIfDone()) 
		{
			remainingWorkToDo -= performanceFactor;
		}
	}
	
	public boolean WorkAndCheckIfDone(double performanceFactor)
	{
		Work(performanceFactor);
		return CheckIfDone();
	}
	
	private void setAddress()
	{
		Random rand = new Random();
		String first = defaultAddress;
		//**** od 0 do 255 chyba
		int second = rand.nextInt(255);
		int third = rand.nextInt(255);
		int fourth = rand.nextInt(255);
		address = first +"."+String.valueOf(second)+"."+String.valueOf(third)+"."+String.valueOf(fourth);
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", initialWorkToDo=" + initialWorkToDo + ", remainingWorkToDo="
				+ remainingWorkToDo + ", address=" + address + ", defaultAddress=" + defaultAddress + "]";
	}
}
