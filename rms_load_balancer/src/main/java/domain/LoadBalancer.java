package domain;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class LoadBalancer
{
	protected List<ServerUnit> ListOfServers;
	protected Queue<Request> QueueOfRequests;

	public LoadBalancer(List<ServerUnit> _ListOfServers)
	{
		this.ListOfServers = _ListOfServers;
		this.QueueOfRequests = new LinkedList<Request>();
	}
	
	public static LoadBalancer Build(TypeOfLoadBalancer typeOfLoadBalancer) throws Exception
	{
		return Build(typeOfLoadBalancer, null);
	}

	public static LoadBalancer Build(TypeOfLoadBalancer typeOfLoadBalancer, List<ServerUnit> listOfServers) throws Exception
	{
		LoadBalancer _LoadBalancer = null;
		
		switch(typeOfLoadBalancer)
		{
		case RoundRobin:
			_LoadBalancer = new LoadBalancerRR(listOfServers);
			break;
			
		case WeightedRoundRobin:
			_LoadBalancer = new LoadBalancerWRR(listOfServers);
			break;
			
		case LeastConnections:
			_LoadBalancer = new LoadBalancerLC(listOfServers);
			break;
			
		case WeightedLeastConnections:
			_LoadBalancer = new LoadBalancerWLC(listOfServers);
			break;
			
		case Random:
			_LoadBalancer = new LoadBalancerRandom(listOfServers);
			break;

		default:
			throw new Exception("Wrong type of load balancer");
		}
		
		return _LoadBalancer;
	}

	public LoadBalancer()
	{
		
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

	public List<ServerUnit> getListOfServers()
	{
		return ListOfServers;
	}

	public Queue<Request> getQueueOfRequests()
	{
		return QueueOfRequests;
	}
}
