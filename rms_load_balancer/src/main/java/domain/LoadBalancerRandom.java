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
		int chosenServerIndex;
		while (CheckIfContinueWork())
		{
			do
			{
				chosenServerIndex = random.nextInt(this.numberOfServers);
			} while (!ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest());

			ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
		}
	}
}
