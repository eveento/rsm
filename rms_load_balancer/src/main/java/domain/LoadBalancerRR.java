package domain;

import java.util.List;

/*
 * 
 * Round robin
 * 
 */
public class LoadBalancerRR extends LoadBalancer{

	public LoadBalancerRR(List<ServerUnit> _ListOfServers) 
	{
		super(_ListOfServers);
	}
	
	public LoadBalancerRR()
	{
		super();
	}

	@Override
	public void Work()
	{
		
	}
}