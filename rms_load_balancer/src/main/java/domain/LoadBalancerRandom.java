package domain;

import java.util.List;
import java.util.Random;

/*
 * 
 * Random
 * 
 */
public class LoadBalancerRandom extends LoadBalancer
{

	private Random random;

	public LoadBalancerRandom(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		random = new Random();
	}

	@Override
	public void Work()
	{
		int chosenServerIndex = random.nextInt(this.numberOfServers);
		while (CheckIfContinueWork())
		{
			if (ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
		}
	}
}
