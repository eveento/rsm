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

	public ServerUnit(int _serverCapacity, double _weight, double _performanceFactor)
	{
		this.serverId = ++ServerUnit.numerator;
		this.ListOfRequests = new ArrayList<Request>();

		setServerCapacity(_serverCapacity);
		setWeight(_weight);
		setPerformanceFactor(_performanceFactor);
	}

	public ServerUnit() {}

	public void Work()
	{
		WorkOnRequests();
		RemoveDoneRequests();
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
		if (this.ListOfRequests.isEmpty())
		{
			return 1.0;
		}
		return this.serverCapacity / this.ListOfRequests.size();
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
		return (ListOfRequests.size() + 1) >= this.serverCapacity;
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
		for (Request request : ListOfRequests)
		{
			if (request.CheckIfDone())
			{
				ListOfRequests.remove(request);
			}
		}
	}
	
	private double MakeNumberPositive(double number)
	{
		if(number >= 0)
		{
			return number;
		}
		else if(number < 0)
		{
			return -number;
		}
		return 1.0;
	}
	
	private int MakeNumberPositive(int number)
	{
		if(number >= 0)
		{
			return number;
		}
		else if(number < 0)
		{
			return -number;
		}
		return 1;
	}

	@Override
	public String toString()
	{
		return "ServerUnit [serverId=" + serverId + ", ListOfRequests=" + ListOfRequests + ", serverCapacity="
				+ serverCapacity + ", weight=" + weight + ", performanceFactor=" + performanceFactor + "]";
	}

}
