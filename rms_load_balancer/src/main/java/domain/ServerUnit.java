package domain;

import java.util.ArrayList;
import java.util.List;

public class ServerUnit {
	private int serverId;
	private static int numerator = 0;
	private List<Request> ListOfRequests;
	private int serverCapacity;
	private double weight;
	private double performanceFactor;
	
	private ServerUnit(ServerBuilder builder)
	{
		this.serverId = ++ServerUnit.numerator;
		this.ListOfRequests = new ArrayList<Request>();
		
		this.serverCapacity = builder.serverCapacity;
		this.weight = builder.weight;
		this.performanceFactor = builder.performanceFactor;
	}
	
	public void Work()
	{
		WorkOnRequests();
		RemoveDoneRequests();
	}
	
	public boolean AddRequest(Request request)
	{
		if(CheckIfCanAcceptRequest())
		{
			ListOfRequests.add(request);
			return true;
		}
		return false;
	}
	
	private void WorkOnRequests()
	{
		for(Request request : ListOfRequests)
		{
			request.Work(GetAmountOfWorkToDo());
		}
	}
	
	private void RemoveDoneRequests()
	{
		for(Request request : ListOfRequests)
		{
			if(request.CheckIfDone())
			{
				ListOfRequests.remove(request);
			}
		}
	}
	
	private double GetAmountOfWorkToDo()
	{
		return performanceFactor;
	}
	
	private boolean CheckIfCanAcceptRequest()
	{
		return (ListOfRequests.size() + 1 ) >= this.serverCapacity;
	}
	
	public double GetPercantageFill()
	{
		if(this.ListOfRequests.isEmpty())
		{
			return 1.0;
		}

		return this.serverCapacity/this.ListOfRequests.size();
	}
	
	public int getServerId() {
		return serverId;
	}

	public List<Request> getListOfRequests() {
		return ListOfRequests;
	}
	
	public int getServerCapacity() {
		return serverCapacity;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getPerformanceFactor() {
		return performanceFactor;
	}
	
	public class ServerBuilder
	{
		private int serverCapacity = 200;
		private double weight = 1;
		private double performanceFactor = 1;
		
		public ServerBuilder serverCapacity(int serverCapacity) {
			this.serverCapacity = serverCapacity;
			return this;
		}
		public ServerBuilder weight(double weight) {
			this.weight = weight;
			return this;
		}
		public ServerBuilder performanceFactor(double performanceFactor) {
			this.performanceFactor = performanceFactor;
			return this;
		}
		
		public ServerUnit Build()
		{
			return new ServerUnit(this);
		}
		
		@Override
		public String toString() {
			return "ServerBuilder [serverCapacity=" + serverCapacity + ", weight=" + weight + ", performanceFactor="
					+ performanceFactor + "]";
		}		
	}
}
