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
		typeOfLoadBalancer = typeOfLoadBalancer.LeastConnections;
	}

	@Override
	public void Work()
	{
		int chosenServerIndex = 0;
		while (CheckIfContinueWork())
		{
			chosenServerIndex = ChooseIndexOfTheServerWithTheLeastConnections();
			if (ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
		}
	}

	private int ChooseIndexOfTheServerWithTheLeastConnections()
	{
		int index = 0;
		int numberOfConnections = Integer.MAX_VALUE;

		for (int i = 0; i < ListOfServers.size(); i++)
		{
			if (ListOfServers.get(i).GetNumberOfRequestsBeingServed() < numberOfConnections)
			{
				if (ListOfServers.get(i).CheckIfCanAcceptRequest())
				{
					index = i;
					numberOfConnections = ListOfServers.get(i).GetNumberOfRequestsBeingServed();
				}
			}
		}

		return index;
	}

	@Override
	public String toString()
	{
		return super.toString() + " LoadBalancerLC [ChooseIndexOfTheServerWithTheLeastConnections()="
				+ ChooseIndexOfTheServerWithTheLeastConnections() + "]";
	}
}
