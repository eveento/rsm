package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 * Weighted round robin
 * 
 */
public class LoadBalancerWRR extends LoadBalancer
{
	private int chosenServerIndex;
	private List<Integer> tableOfWeight;// =new ArrayList<Integer>();
	private List<Integer> tempTablenew = new ArrayList<Integer>();
	private List<Integer> checkIfServerWas = new ArrayList<Integer>();

	public LoadBalancerWRR(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		initChosenServerIndex();
		initTableOfServer();
	}

	@Override
	public void Work()
	{
		while (CheckIfContinueWork())
		{
			chosenServerIndex = getIndexOfMaxValue(tableOfWeight);

			if (ListOfServers.get(chosenServerIndex).CheckIfCanAcceptRequest())
			{
				ListOfServers.get(chosenServerIndex).AddRequest(QueueOfRequests.poll());
			}
			SetNextIndexOfTable();
		}
	}

	private void addServerToUseList(int index)
	{
		checkIfServerWas.add(index); // dodaje do listy uzytych
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

	private void initChosenServerIndex()
	{
		chosenServerIndex = -1;
	}

	private void initTableOfServer()
	{
		tableOfWeight = new ArrayList<Integer>();
		for (ServerUnit su : ListOfServers)
		{
			tableOfWeight.add((int) su.getWeight());
		}
	}

	private int getIndexOfMaxValue(List<Integer> tableOfWeight)
	{
		addServerToUseList(tableOfWeight.indexOf(Collections.max(tableOfWeight)));
		return tableOfWeight.indexOf(Collections.max(tableOfWeight));
	}
}
