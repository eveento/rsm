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
	private int numberOfServers;
	
	public LoadBalancerRR(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		initChosenServerIndex();
		initNumberOfServers();
	}
	
	@Override
	public void Work()
	{
		while(!AllServersFilledToMaximum() || QueueOfRequests.isEmpty())
		{
			SetNextIndex();
			if(ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
		}
	}
	
	public int GetNumberOfRequestsWaitingToBeAssigned()
	{
		return QueueOfRequests.size();
	}
	
	private void SetNextIndex()
	{
		if(chosenServerIndex + 1 >= numberOfServers)
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
	
	private void initNumberOfServers()
	{
		numberOfServers = ListOfServers.size();
	}
	
	private boolean AllServersFilledToMaximum()
	{
		for(ServerUnit item : ListOfServers)
		{
			if(item.CheckIfCanAcceptRequest())
			{
				return false;
			}
		}
		return true;
	}
}
