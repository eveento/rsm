package domain;

import domain.Client;

public class test {
	
	public void Run()
	{
		int number = 10;
		Test1 test1 = new Test1(number);
		System.out.println("Test1");
		test1.print();
		
		Test2 test21, test22;
		test21 = new Test2(test1);
		test22 = new Test2(test1);
		
		System.out.println("Test21");
		test21.print();
		
		System.out.println("Test22");
		test22.print();
		
		System.out.println("Test21 se number 11");
		test21.setNumber(11);
		
		System.out.println("Test21");
		test21.print();
		
		System.out.println("Test22");
		test22.print();
	}
	
	
	public class Test1
	{
		private int number;
		public Test1(int _number)
		{
			number = _number;
		}
		
		public void setNumber(int _number)
		{
			number = _number;
		}
		
		public int getNumber()
		{ 
			return number; 
		}
		
		public void print()
		{
			System.out.println(number);
		}
	}
	
	public class Test2
	{
		Test1 test1;
		public Test2(Test1 _test1)
		{
			test1 = _test1;
		}
		
		public void setNumber(int _number)
		{
			test1.setNumber(_number);
		}
		
		public int getNumber()
		{ 
			return test1.getNumber(); 
		}
		
		public void print()
		{
			test1.print();
		}
	}

}
