package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import domain.*;
import domain.ServerUnit.Builder;

public class test
{
	private List<ServerUnit> listOfServers;
	private Client client;
	private Simulation simulation;
	private LoadBalancer loadBalancer;

	public test() throws Exception
	{
		System.out.println("Konstruktor testu");
		listOfServers = new ArrayList<ServerUnit>();

		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Build());

		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Build()); 
		
		System.out.println("Stworzylem serwer(y)");
		
		client = new Client
				.Builder()
				.MinimumWorkToDo(5)
				.MaximumWorkToDo(5)
				.InitialRequestsNumber(1000)
				.RequestsInOneQueue(35)
				.PercentageRandomizeOfRequests(0.0)
				.RandomizeNumberOfRequests(false)
				.Build();

		System.out.println("Stworzylem klientow");

		loadBalancer = LoadBalancer.Build(TypeOfLoadBalancer.Random, listOfServers);

		System.out.println("Stworzylem load balancer");

		simulation = new Simulation(client, loadBalancer, listOfServers);

		System.out.println("Stworzylem symulacje");
	}

	public void run() throws IOException, InterruptedException
	{
		System.out.println("Zaczynamy");
		int ile_razy = 20;
		for (int i = 0; i < ile_razy; i++)
		{
			simulation.RunOnce();
			// simulation.RunOnceOneRequest();
			// simulation.RunAll();
		}
		DuzoEnterow();
		System.out.println("Po : " + ile_razy + " iteracjach");
		print();
	}

	private void print()
	{
		System.out.println(client.toString());
		System.out.println(loadBalancer.toString());
		for (ServerUnit item : listOfServers)
		{
			System.out.println(item.toString());
		}
	}

	private void DuzoEnterow()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println();
		}
	}

}
