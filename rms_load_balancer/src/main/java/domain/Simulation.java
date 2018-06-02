package domain;

import java.util.List;

public class Simulation
{
	Client client;
	LoadBalancer loadBalancer;
	List<ServerUnit> listOfServers;

	public Simulation(Client _client, LoadBalancer _loadBalancer, List<ServerUnit> _listOfServers)
	{
		client = _client;
		loadBalancer = _loadBalancer;
		listOfServers = _listOfServers;
	}

	public void RunAllOneRequestPerIteration()
	{
		do
		{
			RunOnceOneRequest();
		} while (!client.CheckIfAllRequestsSent());
	}
	
	public void RunOnce()
	{
		if (!client.CheckIfAllRequestsSent())
		{
			loadBalancer.AddRequests(client.Work());
			// loadBalancer.Work();

			for (ServerUnit serverUnit : listOfServers)
			{
				serverUnit.Work();
			}

			loadBalancer.Work();
		}
	}

	public void RunOnceOneRequest()
	{
		if (!client.CheckIfAllRequestsSent())
		{
			loadBalancer.AddRequests(client.Work(1));
			loadBalancer.Work();

			for (ServerUnit serverUnit : listOfServers)
			{
				serverUnit.Work();
			}
		}
	}

	public void RunAll()
	{
		do
		{
			RunOnce();
		} while (!client.CheckIfAllRequestsSent());
	}

}
