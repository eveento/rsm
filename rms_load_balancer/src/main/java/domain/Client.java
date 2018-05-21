package domain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Client {

	private int minimumWorkToDo = 10;
	private int maximumWorkToDo = 50;
	private int initialRequestsNumber = 1000;
	private int remainingRequestsNumber = 1000;
	private double requestsGenerationDensity = 0.1; // percantage ( 0 < x <= 1)
	private final int maximumNumberOfRequestsInOneQueue = 100;
	private final double gaussianModifier = 0.25;
	private Random randomGenerator;

	public Client(int _minimumWorkToDo, int _maximumWorkToDo, int _initialRequestsNumber, double _requestsGenerationDensity ) {
		
		setMinimumWorkToDo(_minimumWorkToDo);
		setMaximumWorkToDo(_maximumWorkToDo);
		setInitialRequestsNumber(_initialRequestsNumber);
		setRequestsGenerationDensity(_requestsGenerationDensity);
		
		this.randomGenerator = new Random();
		randomGenerator.nextGaussian();
	}
	
	public Client()
	{
		this.randomGenerator = new Random();
		randomGenerator.nextGaussian();
	}

	public Queue<Request> Work() {
		return GenerateRequests();
	}

	private Queue<Request> GenerateRequests() {
		Queue<Request> queue = new LinkedList<Request>();

		int numberOfRequests = GenerateNumberOfRequestsInQueue();

		for (int i = 0; i < numberOfRequests; i++) {
			AddRequestToQueue(queue, GenerateRequest());
		}

		return queue;
	}

	private int GenerateNumberOfRequestsInQueue() {
		int number = (int) (requestsGenerationDensity * maximumNumberOfRequestsInOneQueue);
		number = (int) (number * (randomGenerator.nextGaussian() + gaussianModifier));
		if (number > maximumNumberOfRequestsInOneQueue) {
			number = maximumNumberOfRequestsInOneQueue;
		}
		return number;
	}

	private boolean AddRequestToQueue(Queue<Request> queue, Request RequestToAdd) {
		if (remainingRequestsNumber - 1 >= 0) {
			queue.add(RequestToAdd);
			return true;
		}
		return false;
	}

	private Request GenerateRequest() {
		return new Request(SetRequestWork());
	}

	private double SetRequestWork() {
		int number;
		number = randomGenerator.nextInt(maximumWorkToDo - minimumWorkToDo) + minimumWorkToDo;
		return number;
	}

	public double getMinimumWorkToDo() {
		return minimumWorkToDo;
	}

	public double getMaximumWorkToDo() {
		return maximumWorkToDo;
	}

	public int getInitialRequestsNumber() {
		return initialRequestsNumber;
	}

	public int getRemainingRequestsNumber() {
		return remainingRequestsNumber;
	}

	public double getRequestsGenerationDensity() {
		return requestsGenerationDensity;
	}

	public void setMinimumWorkToDo(int minimumWorkToDo) {
		this.minimumWorkToDo = MakePositive(minimumWorkToDo);
	}

	public void setMaximumWorkToDo(int maximumWorkToDo) {
		maximumWorkToDo = MakePositive(maximumWorkToDo);
		if (maximumWorkToDo < this.minimumWorkToDo) {
			maximumWorkToDo = this.minimumWorkToDo;
		}
		this.maximumWorkToDo = maximumWorkToDo;
	}

	public void setInitialRequestsNumber(int initialRequestsNumber) {
		this.initialRequestsNumber = MakePositive(initialRequestsNumber);
		this.remainingRequestsNumber = this.initialRequestsNumber;
	}

	public void setRequestsGenerationDensity(double requestsGenerationDensity) {
		this.requestsGenerationDensity = MakeValuePercantage(requestsGenerationDensity);
	}

	private int MakePositive(int number) {
		if (number < 0) {
			number = -number;
		} else if (number == 0) {
			number = 1;
		}
		return number;
	}

	private double MakeValuePercantage(double number) {
		if (number < 0) {
			number = 0 - number;
		}

		if (CheckIfPercantage(number)) {
			return number;
		}

		do {
			number = number / 10;
		} while (!CheckIfPercantage(number));

		return number;
	}

	private boolean CheckIfPercantage(double number) {
		if (number > 0 && number <= 100) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Client [minimumWorkToDo=" + minimumWorkToDo + ", maximumWorkToDo=" + maximumWorkToDo
				+ ", initialRequestsNumber=" + initialRequestsNumber + ", remainingRequestsNumber="
				+ remainingRequestsNumber + ", requestsGenerationDensity=" + requestsGenerationDensity
				+ ", maximumNumberOfRequestsInOneQueue=" + maximumNumberOfRequestsInOneQueue + ", gaussianModifier="
				+ gaussianModifier + ", randomGenerator=" + randomGenerator + "]";
	}
	
	
}
