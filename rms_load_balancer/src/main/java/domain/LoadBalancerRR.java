package domain;

import java.util.List;
import java.util.Queue;

/*
 * 
 * Round robin
 * 
 */
public class LoadBalancerRR extends LoadBalancer
{

	private int chosenServerIndex;

	public LoadBalancerRR(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		initChosenServerIndex();
	}

	@Override
	public void Work()
	{
		while (CheckIfContinueWork())
		{
			SetNextIndex();
			if (ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
		}
	}

	private void SetNextIndex()
	{
		if (chosenServerIndex + 1 >= numberOfServers)
		{
			chosenServerIndex = 0;
		} 
		else
		{
			chosenServerIndex++;
		}
	}

	private void initChosenServerIndex()
	{
		chosenServerIndex = -1;
	}

	@Override
	public String toString()
	{
		return super.toString() + " LoadBalancerRR [chosenServerIndex=" + chosenServerIndex + "]";
	}

	
	
	
}
