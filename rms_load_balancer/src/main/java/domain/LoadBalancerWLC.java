package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 * Weighted least Connections
 * 
 */
public class LoadBalancerWLC extends LoadBalancer
{

	//private List<Integer> tableOfWeight = new ArrayList<Integer>();
	//private List<Integer> checkIfServerWas = new ArrayList<Integer>();
	private int chosenServerIndex;
	private Integer temp;

	public LoadBalancerWLC(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);

	}

	@Override
	public void Work()
	{

		while (CheckIfContinueWork())
		{
			chosenServerIndex = ChooseIndexOfTheServerWithTheLeastConnections();
			temp= (int) ListOfServers.get(chosenServerIndex).getWeight();
			for(int i=0;i<temp;i++)
				if(CheckIfContinueWork())
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

	
}
