package domain;

import java.util.ArrayList;
import java.util.List;

public class ServerUnit
{
	private int serverId;
	private static int numerator = 0;
	private List<Request> ListOfRequests;
	private int serverCapacity;
	private double weight;
	private double performanceFactor;
	
	private ServerUnit(Builder builder)
	{
		this.serverId = ++ServerUnit.numerator;
		this.ListOfRequests = new ArrayList<Request>();
		
		this.serverCapacity = builder.serverCapacity;
		this.weight = builder.weight;
		this.performanceFactor = builder.performanceFactor;
	}

	@Override
	protected void finalize() throws Throwable
	{
		numerator--;
		super.finalize();
	}
	
	public void Work()
	{
		WorkOnRequests();
		RemoveDoneRequests();
	}

	public int GetNumberOfRequestsBeingServed()
	{
		return ListOfRequests.size();
	}

	public boolean AddRequest(Request request)
	{
		if (CheckIfCanAcceptRequest())
		{
			ListOfRequests.add(request);
			return true;
		}
		return false;
	}

	public double GetPercantageFill()
	{
		if (this.serverCapacity <= 0)
		{
			return 0.0;
		}
		return (double) GetNumberOfRequestsBeingServed() / this.serverCapacity;
	}

	public int getServerCapacity()
	{
		return serverCapacity;
	}

	public void setServerCapacity(int serverCapacity)
	{
		this.serverCapacity = MakeNumberPositive(serverCapacity);
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = MakeNumberPositive(weight);
	}

	public double getPerformanceFactor()
	{
		return performanceFactor;
	}

	public void setPerformanceFactor(double performanceFactor)
	{
		this.performanceFactor = MakeNumberPositive(performanceFactor);
	}

	public int getServerId()
	{
		return serverId;
	}

	public static int getNumerator()
	{
		return numerator;
	}

	public List<Request> getListOfRequests()
	{
		return ListOfRequests;
	}

	public boolean CheckIfCanAcceptRequest()
	{
		return (ListOfRequests.size() + 1) <= this.serverCapacity;
	}

	private void WorkOnRequests()
	{
		for (Request request : ListOfRequests)
		{
			request.Work(getPerformanceFactor());
		}
	}

	private void RemoveDoneRequests()
	{
		ListOfRequests.removeIf(filter -> filter.CheckIfDone());
	}

	private double MakeNumberPositive(double number)
	{
		if (number >= 0)
		{
			return number;
		} else if (number < 0)
		{
			return -number;
		}
		return 1.0;
	}

	private int MakeNumberPositive(int number)
	{
		if (number >= 0)
		{
			return number;
		} else if (number < 0)
		{
			return -number;
		}
		return 1;
	}

	@Override
	public String toString()
	{
		return "ServerUnit [serverId=" + serverId + ", serverCapacity=" + serverCapacity + ", weight=" + weight
				+ ", performanceFactor=" + performanceFactor + ", GetNumberOfRequestsBeingServed()="
				+ GetNumberOfRequestsBeingServed() + ", GetPercantageFill()=" + GetPercantageFill()
				+ ", CheckIfCanAcceptRequest()=" + CheckIfCanAcceptRequest() + "]";
	}
	
	public static class Builder
	{
		private int serverCapacity = 100;
		private double weight = 1.0;
		private double performanceFactor = 1.0;
		
		public Builder ServerCapacity(int serverCapacity)
		{
			this.serverCapacity = MakeNumberPositive(serverCapacity);
			return this;
		}

		public Builder Weight(double weight)
		{
			this.weight = MakeNumberPositive(weight);
			return this;
		}

		public Builder PerformanceFactor(double performanceFactor)
		{
			this.performanceFactor = MakeNumberPositive(performanceFactor);
			return this;
		}
		
		public ServerUnit Build()
		{
			return new ServerUnit(this);
		}
		
		private double MakeNumberPositive(double number)
		{
			if (number >= 0)
			{
				return number;
			} else if (number < 0)
			{
				return -number;
			}
			return 1.0;
		}

		private int MakeNumberPositive(int number)
		{
			if (number >= 0)
			{
				return number;
			} else if (number < 0)
			{
				return -number;
			}
			return 1;
		}
	}

}
