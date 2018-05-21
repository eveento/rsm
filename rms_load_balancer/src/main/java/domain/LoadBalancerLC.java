package domain;

import java.util.List;

/*
 * 
 * Least Connections
 * 
 */
public class LoadBalancerLC extends LoadBalancer
{

	public LoadBalancerLC(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
	}

	public LoadBalancerLC()
	{
		super();
	}

	@Override
	public void Work()
	{

	}
}
