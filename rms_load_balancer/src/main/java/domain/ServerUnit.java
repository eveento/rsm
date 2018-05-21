package domain;

import java.util.ArrayList;
import java.util.List;

public class ServerUnit {
	private int serverId;
	private static int numerator = 0;
	private List<Request> ListOfRequests;
	private int serverCapacity;
	private double weight;
	private double performanceFactor;

	public ServerUnit(int _serverCapacity, double _weight, double _performanceFactor) {
		this.serverId = ++ServerUnit.numerator;
		this.ListOfRequests = new ArrayList<Request>();

		setServerCapacity(_serverCapacity);
		setWeight(_weight);
		setPerformanceFactor(_performanceFactor);
	}
	
	public ServerUnit() {}

	public void Work() {
		WorkOnRequests();
		RemoveDoneRequests();
	}

	public boolean AddRequest(Request request) {
		if (CheckIfCanAcceptRequest()) {
			ListOfRequests.add(request);
			return true;
		}
		return false;
	}

	private void WorkOnRequests() {
		for (Request request : ListOfRequests) {
			request.Work(GetAmountOfWorkToDo());
		}
	}

	private void RemoveDoneRequests() {
		for (Request request : ListOfRequests) {
			if (request.CheckIfDone()) {
				ListOfRequests.remove(request);
			}
		}
	}

	private double GetAmountOfWorkToDo() {
		return performanceFactor;
	}

	private boolean CheckIfCanAcceptRequest() {
		return (ListOfRequests.size() + 1) >= this.serverCapacity;
	}

	public double GetPercantageFill() {
		if (this.ListOfRequests.isEmpty()) {
			return 1.0;
		}

		return this.serverCapacity / this.ListOfRequests.size();
	}

	public int getServerId() {
		return serverId;
	}

	public List<Request> getListOfRequests() {
		return ListOfRequests;
	}

	public int getServerCapacity() {
		return serverCapacity;
	}

	public double getWeight() {
		return weight;
	}

	public double getPerformanceFactor() {
		return performanceFactor;
	}

	public void setServerCapacity(int serverCapacity) {
		this.serverCapacity = serverCapacity;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setPerformanceFactor(double performanceFactor) {
		this.performanceFactor = performanceFactor;
	}

	@Override
	public String toString() {
		return "ServerUnit [serverId=" + serverId + ", ListOfRequests=" + ListOfRequests + ", serverCapacity="
				+ serverCapacity + ", weight=" + weight + ", performanceFactor=" + performanceFactor + "]";
	}


}
