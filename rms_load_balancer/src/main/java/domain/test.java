package domain;

import domain.Client;

public class test {
	
	Client client;
	ServerUnit server;
	LoadBalancer loadBalancer;
	public test()
	{

		client = new Client(10, 50, 1000, 0.1);
		System.out.println(client.toString());
		
		server = new ServerUnit(100, 1.0, 1.0);
		System.out.println(server.toString());
		
		loadBalancer = new LoadBalancerRR();
		
	}

}
