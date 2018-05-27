package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		listOfServers = new ArrayList<ServerUnit>();
		
		for(int i = 0; i < 4; i++)
			listOfServers.add(new ServerUnit(100, 1, 1));
		
		client = new Client(40, 70, 10000, 0.35);
		loadBalancer = LoadBalancer.Build(TypeOfLoadBalancer.RoundRobin, listOfServers);
		
		simulation = new Simulation(client, loadBalancer, listOfServers);
	}
	
	public void run() throws IOException, InterruptedException
	{
		System.out.println("Zaczynamy");
		int ile_razy = 100;
		for(int i = 0; i < ile_razy; i++)
		{
			simulation.RunOnceOneRequest();
			//simulation.RunAll();
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
