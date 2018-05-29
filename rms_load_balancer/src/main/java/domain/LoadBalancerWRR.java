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
	private Integer temp;
	//private List<Integer> tableOfWeight=new ArrayList<Integer>();
	//private List<Integer> checkIfServerWas = new ArrayList<Integer>();

	public LoadBalancerWRR(List<ServerUnit> _ListOfServers)
	{
		super(_ListOfServers);
		initChosenServerIndex();
	/*	initTableOfServer();
		initTempTableOfServer();*/
	}

	@Override
	public void Work()
	{	while(CheckIfContinueWork()) {
			SetNextIndex();
			System.out.println(chosenServerIndex);
			temp= (int) ListOfServers.get(chosenServerIndex).getWeight();
				
			for(int i=0;i<temp;i++)
				if(CheckIfContinueWork())
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
		} else
		{
			chosenServerIndex++;
		}
	}
/*	private void addServerToUseList(int index)
	{
		checkIfServerWas.add(index); // dodaje do listy uzytych
	}
*/
	/*private int SetNextIndexOfTable()
	{
		for (int i = 0; i < ListOfServers.size() - 1; i++)
		{
			for (int j = 0; j < checkIfServerWas.size() - 1; i++)
			{
				if (ListOfServers.get(i).getServerId() == checkIfServerWas.get(j)) { // czy byl uzyty
					continue; 					
				}
				else
				{
					chosenServerIndex = getIndexOfMaxValue(tableOfWeight);
					addServerToUseList(ListOfServers.get(i).getServerId());
				}
				break;			
			}
		}
		return chosenServerIndex;
	}*/

	private void initChosenServerIndex()
	{
		chosenServerIndex = -1;
	}
/*
	private void initTableOfServer()
	{
		//tableOfWeight = new ArrayList<Integer>();
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
	private int getWeigthOfMaxValue(int index) {
		for (ServerUnit su : ListOfServers) {
			if(su.getServerId()== index)
				return (int)su.getWeight();
		}
		return 0;
	}
	
	private void initTempTableOfServer()
	{
		checkIfServerWas = new ArrayList<Integer>();
		for (ServerUnit su : ListOfServers)
		{
			checkIfServerWas.add(0);
		}
	}*/
}
