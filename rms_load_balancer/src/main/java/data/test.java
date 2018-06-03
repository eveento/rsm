package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;

import domain.*;
import domain.ServerUnit.Builder;

public class test
{
	private List<ServerUnit> listOfServers;
	private Client client;
	private Simulation simulation;
	private LoadBalancer loadBalancer;

	public test(TypeOfLoadBalancer tlb,int tfMinWork, int tfMaxWork,int initRequestNumber, int requestsInOneQueue,double percentageRandomizeOfRequests,boolean randomizeNumberOfRequests) throws Exception
	{
			
		//System.out.println("Konstruktor testu");
		listOfServers = new ArrayList<ServerUnit>();

		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Weight(1)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Weight(5)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Weight(7)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Weight(9)
				.Build()); 
		
	//	System.out.println("Stworzylem serwer(y)");
		
		client = new Client
				.Builder()
				.MinimumWorkToDo(tfMinWork)//5
				.MaximumWorkToDo(tfMaxWork)//5
				.InitialRequestsNumber(initRequestNumber)//1000
				.RequestsInOneQueue(requestsInOneQueue)//35
				.PercentageRandomizeOfRequests(percentageRandomizeOfRequests)//0.0
				.RandomizeNumberOfRequests(randomizeNumberOfRequests)//false
				.Build();

		//System.out.println("Stworzylem klientow");

		loadBalancer = LoadBalancer.Build(tlb, listOfServers);

	//	System.out.println("Stworzylem load balancer");

		simulation = new Simulation(client, loadBalancer, listOfServers);

	//	System.out.println("Stworzylem symulacje");
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
		for (int i = 0; i < 2; i++)
		{
			System.out.println();
		}
	}

}
