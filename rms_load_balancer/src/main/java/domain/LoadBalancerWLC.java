package domain;

import java.util.List;

/*
 * 
 * Weighted least Connections
 * 
 */
public class LoadBalancerWLC extends LoadBalancer
{

	public LoadBalancerWLC(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
	}

	public LoadBalancerWLC()
	{
		super();
	}

	@Override
	public void Work()
	{

	}
}
