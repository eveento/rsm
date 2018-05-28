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

	private List<Integer> tableOfWeight;
	private List<Integer> checkIfServerWas = new ArrayList<Integer>();
	private int chosenServerIndex;

	public LoadBalancerWLC(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		initTableOfServer();
	}

	@Override
	public void Work()
	{
		chosenServerIndex = getIndexOfMaxValue(tableOfWeight);
		while (CheckIfContinueWork())
		{
			chosenServerIndex = ChooseIndexOfTheServerWithTheLeastConnections();
			if (ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
			SetNextIndexOfTable();
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

	private void addServerToUseList(int index)
	{
		checkIfServerWas.add(index); // dodaje do listy uzytych
	}

	private int getIndexOfMaxValue(List<Integer> tableOfWeight)
	{

		addServerToUseList(tableOfWeight.indexOf(Collections.max(tableOfWeight)));
		return tableOfWeight.indexOf(Collections.max(tableOfWeight));
	}

	private void initTableOfServer()
	{
		tableOfWeight = new ArrayList<Integer>();
		for (ServerUnit su : ListOfServers)
		{
			tableOfWeight.add((int) su.getWeight());
		}
	}

	private int SetNextIndexOfTable()
	{

		for (int i = 0; i < ListOfServers.size() - 1; i++)
		{
			for (int j = 0; j < checkIfServerWas.size() - 1; i++)
			{
				if (ListOfServers.get(i).getServerId() == checkIfServerWas.get(j)) // czy byl uzyty
					break; // byl wiec konczymy obieg i przechodzimy dalej
				else
				{
					chosenServerIndex = getIndexOfMaxValue(tableOfWeight);
					addServerToUseList(ListOfServers.get(i).getServerId());
				}
			}
		}
		return chosenServerIndex;
	}
}
