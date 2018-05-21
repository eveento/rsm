package domain;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class LoadBalancer {
	protected List<ServerUnit> ListOfServers;
	protected Queue<Request> QueueOfRequests;
	
	public LoadBalancer(List<ServerUnit> _ListOfServers)
	{
		this.ListOfServers = _ListOfServers;
		this.QueueOfRequests = new LinkedList<Request>();
	}
	
	public void Work()
	{
		
	}
	
	public void AddRequest(Request request)
	{
		QueueOfRequests.add(request);
	}
	
	public void AddRequests(Queue<Request> _QueueOfRequests)
	{
		QueueOfRequests.addAll(_QueueOfRequests);
	}
	
	public List<ServerUnit> getListOfServers() {
		return ListOfServers;
	}

	public Queue<Request> getQueueOfRequests() {
		return QueueOfRequests;
	}
}
