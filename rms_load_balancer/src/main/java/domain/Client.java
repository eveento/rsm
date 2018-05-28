package domain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Client
{

	private int minimumWorkToDo = 0;
	private int maximumWorkToDo = Integer.MAX_VALUE;
	private int initialRequestsNumber = 1000;
	private int remainingRequestsNumber = 1000;
	private int requestsInOneQueue = 10;
	private double percentageRandomizeOfRequests = 0;
	private boolean randomizeNumberOfRequests = false;
	private final int maximumNumberOfRequestsInOneQueue = 100;
	private Random randomGenerator;

	public Client(int _minimumWorkToDo, int _maximumWorkToDo, int _initialRequestsNumber,
			int _requestsInOneQueue, double _percentageRandomizeOfRequests, boolean _randomizeNumberOfRequests) 
	{
		setMaximumWorkToDo(_maximumWorkToDo);
		setMinimumWorkToDo(_minimumWorkToDo);
		setInitialRequestsNumber(_initialRequestsNumber);
		setRequestsInOneQueue(_requestsInOneQueue);
		setPercentageRandomizeOfRequests(_percentageRandomizeOfRequests);
		setRandomizeNumberOfRequests(_randomizeNumberOfRequests);
			
		this.randomGenerator = new Random();
		randomGenerator.nextGaussian();
	}

	public Client()
	{
		this.randomGenerator = new Random();
		randomGenerator.nextGaussian();
	}

	public Queue<Request> Work()
	{
		return GenerateRequests();
	}  
	
	public Queue<Request> Work(int howManyRequests)
	{
		return GenerateRequests(howManyRequests);
	}  
	
	public boolean CheckIfAllRequestsSent()
	{
		return remainingRequestsNumber <= 0;
	}

	public int getRequestsInOneQueue()
	{
		return requestsInOneQueue;
	}

	public void setRequestsInOneQueue(int requestsInOneQueue)
	{
		this.requestsInOneQueue = MakePositive(requestsInOneQueue);
	}

	public double getPercentageRandomizeOfRequests()
	{
		return percentageRandomizeOfRequests;
	}

	public void setPercentageRandomizeOfRequests(double percentageRandomizeOfRequests)
	{
		this.percentageRandomizeOfRequests = MakeValuePercantage(percentageRandomizeOfRequests);
	}

	public boolean isRandomizeNumberOfRequests()
	{
		return randomizeNumberOfRequests;
	}

	public void setRandomizeNumberOfRequests(boolean randomizeNumberOfRequests)
	{
		this.randomizeNumberOfRequests = randomizeNumberOfRequests;
	}

	public int getMinimumWorkToDo()
	{
		return minimumWorkToDo;
	}

	public int getMaximumWorkToDo()
	{
		return maximumWorkToDo;
	}

	public int getInitialRequestsNumber()
	{
		return initialRequestsNumber;
	}

	public int getRemainingRequestsNumber()
	{
		return remainingRequestsNumber;
	}

	public int getMaximumNumberOfRequestsInOneQueue()
	{
		return maximumNumberOfRequestsInOneQueue;
	}

	public void setMinimumWorkToDo(int minimumWorkToDo)
	{
		minimumWorkToDo = MakePositive(minimumWorkToDo);
		if (minimumWorkToDo > this.maximumWorkToDo)
		{
			minimumWorkToDo = this.maximumWorkToDo;
		}
		this.minimumWorkToDo = MakePositive(minimumWorkToDo);
	}

	public void setMaximumWorkToDo(int maximumWorkToDo)
	{
		maximumWorkToDo = MakePositive(maximumWorkToDo);
		if (maximumWorkToDo < this.minimumWorkToDo)
		{
			maximumWorkToDo = this.minimumWorkToDo;
		}
		this.maximumWorkToDo = maximumWorkToDo;
	}

	public void setInitialRequestsNumber(int initialRequestsNumber)
	{
		this.initialRequestsNumber = MakePositive(initialRequestsNumber);
		this.remainingRequestsNumber = this.initialRequestsNumber;
	}

	private double SetRequestWorkToDO()
	{
		int number;
		if(maximumWorkToDo == minimumWorkToDo)
		{
			number = minimumWorkToDo;
		}
		else
		{
			number = randomGenerator.nextInt(maximumWorkToDo - minimumWorkToDo) + minimumWorkToDo;
		}
		System.out.println(number);
		return number;
	}
	
	private Queue<Request> GenerateRequests(int howMany)
	{
		Queue<Request> queue = new LinkedList<Request>();

		int numberOfRequests = howMany;

		for (int i = 0; i < numberOfRequests; i++)
		{
			AddRequestToQueue(queue, GenerateRequest());
		}

		return queue;
	}

	private Queue<Request> GenerateRequests()
	{
		Queue<Request> queue = new LinkedList<Request>();

		int numberOfRequests = GenerateNumberOfRequestsInQueue();

		for (int i = 0; i < numberOfRequests; i++)
		{
			AddRequestToQueue(queue, GenerateRequest());
		}

		return queue;
	}

	private int GenerateNumberOfRequestsInQueue()
	{
		int number = this.requestsInOneQueue + GenerateAdditionalNumberOfRequestsInQueue();
		return MakePositive(number);
	}
	
	private int GenerateAdditionalNumberOfRequestsInQueue()
	{
		if(!randomizeNumberOfRequests)
		{
			return 0;
		}
		
		int randomNumber = randomGenerator.nextInt((int)(requestsInOneQueue * percentageRandomizeOfRequests));
		randomNumber = 2 * randomNumber - (int)(requestsInOneQueue * percentageRandomizeOfRequests);
		return randomNumber;
	}

	private boolean AddRequestToQueue(Queue<Request> queue, Request RequestToAdd)
	{
		if (remainingRequestsNumber - 1 >= 0)
		{
			queue.add(RequestToAdd);
			remainingRequestsNumber--;
			return true;
		}
		return false;
	}

	private Request GenerateRequest()
	{
		return new Request(SetRequestWorkToDO());
	}

	private int MakePositive(int number)
	{
		if (number < 0)
		{
			number = -number;
		} else if (number == 0)
		{
			number = 1;
		}
		return number;
	}

	private double MakeValuePercantage(double number)
	{
		if (number < 0)
		{
			number = 0 - number;
		}

		if (CheckIfPercantage(number))
		{
			return number;
		}

		do
		{
			number = number / 10;
		} while (!CheckIfPercantage(number));

		return number;
	}

	private boolean CheckIfPercantage(double number)
	{
		if (number >= 0 && number <= 1.0)
		{
			return true;
		} 
		else
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return "Client [minimumWorkToDo=" + minimumWorkToDo + ", maximumWorkToDo=" + maximumWorkToDo
				+ ", remainingRequestsNumber=" + remainingRequestsNumber + ", requestsInOneQueue=" + requestsInOneQueue
				+ ", percentageRandomizeOfRequests=" + percentageRandomizeOfRequests + ", randomizeNumberOfRequests="
				+ randomizeNumberOfRequests + "]";
	}
}
