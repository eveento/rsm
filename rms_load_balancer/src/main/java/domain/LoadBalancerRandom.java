package domain;

import java.util.List;

/*
 * 
 * Random
 * 
 */
public class LoadBalancerRandom extends LoadBalancer
{

	public LoadBalancerRandom(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
	}

	public LoadBalancerRandom()
	{
		super();
	}

	@Override
	public void Work()
	{

	}
}
