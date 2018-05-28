package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import domain.*;

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

		for (int i = 0; i < 4; i++)
			listOfServers.add(new ServerUnit(100, 2, 1));

		System.out.println("Stworzylem serwery");

		client = new Client(5, 5, 1000, 35, 0.0, false);

		System.out.println("Stworzylem klientow");

		loadBalancer = LoadBalancer.Build(TypeOfLoadBalancer.WeightedRoundRobin, listOfServers);

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
