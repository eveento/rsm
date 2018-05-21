package domain;

import java.util.List;

/*
 * 
 * Weighted round robin
 * 
 */
public class LoadBalancerWRR extends LoadBalancer
{

	public LoadBalancerWRR(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
	}

	public LoadBalancerWRR()
	{
		super();
	}

	@Override
	public void Work()
	{

	}
}
