package data;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

import domain.*;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Application
{

	private  JFrame frame;
	private  List<JRadioButton> listOfLoadBalancerButtons;
	private  TypeOfLoadBalancer chosenTypeOfLoadBalancer;
	private static JTextField tfMinWork;
	private static JTextField tfMaxWork;
	private static JTextField tfTotalRequests;
	private static JTextField tfRequestsInOneSet;
	private static JTextField tfRandomizePercentage;
	
	private final static ButtonGroup ChosenLoadBalancer = new ButtonGroup();
	
	private static  List<ServerUnit> listOfServers= new ArrayList<ServerUnit>();
	private static  Client client;
	private  LoadBalancer loadBalancer;
	private  Simulation simulation;
	private final Action action = new SwingAction();
	
	JLabel lblx = new JLabel("0x00");
	JLabel lblIterationNumberNumber = new JLabel("0x00");
	JLabel lblRemainingRequestsNumber = new JLabel("0x00");
	static JCheckBox checkRandomizeNumberOfRequestsInSet = new JCheckBox("Randomize number of requests in one set");
	private static int numberOfIteration=0;
	private static JTextField tfServerCapacity;
	private static JTextField tfWeightServer;
	private static JTextField tfPerformanceFactor;
	private JTable table = new JTable();
	DefaultTableModel model= (DefaultTableModel) table.getModel();
		
	public static int getNumberOfIteration() {
		return numberOfIteration;
	}

	public static void setNumberOfIteration(int numberOfIteration) {
		Application.numberOfIteration = numberOfIteration;
		
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args)
	{		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Application window = new Application();
					window.frame.setVisible(true);
					CreateClients(); 					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public Application()
	{
		initialize();
	}
	
	private static void CreateAddServerByUser() {
		int serverCapacity = Integer.parseInt(tfServerCapacity.getText()); 
		int serverWeight=Integer.parseInt(tfWeightServer.getText());
		int performanceFactor=Integer.parseInt(tfPerformanceFactor.getText());
		
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(serverCapacity)
				.Weight(serverWeight)
				.PerformanceFactor(performanceFactor)
				.Build());
		JOptionPane.showMessageDialog(null, "Server has been added!");
		
	}
	
	private static void CreateServers() {
		//listOfServers = new ArrayList<ServerUnit>();
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(100)
				.Weight(1)
				.PerformanceFactor(1.0)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(50)
				.Weight(5)
				.PerformanceFactor(0.8)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(90)
				.Weight(7)
				.PerformanceFactor(0.5)
				.Build());
		listOfServers.add(
				new ServerUnit
				.Builder()
				.ServerCapacity(10)
				.Weight(9)
				.PerformanceFactor(0.4)
				.Build()); 
	}
	
	private static void CreateClients()
	{
		int minWork = Integer.parseInt(tfMinWork.getText()); 
		int maxWork=Integer.parseInt(tfMaxWork.getText());
		int initRequestNumb=Integer.parseInt(tfTotalRequests.getText());
		int requestsInOneQueue=Integer.parseInt(tfRequestsInOneSet.getText());
		double percentageRandomizeOfRequests=Integer.parseInt(tfRandomizePercentage.getText());
		boolean randomizeNumberOfRequests=(checkRandomizeNumberOfRequestsInSet.isSelected()?true:false);
		if(percentageRandomizeOfRequests==0)
					JOptionPane.showMessageDialog(null, "Value of randomize must be positive! Plese input correct value and create simulation again!");
		else
			percentageRandomizeOfRequests=percentageRandomizeOfRequests/100;
		
		client = new Client
				.Builder()
				.MinimumWorkToDo(minWork)//5
				.MaximumWorkToDo(maxWork)//5
				.InitialRequestsNumber(initRequestNumb)//1000
				.RequestsInOneQueue(requestsInOneQueue)//35
				.PercentageRandomizeOfRequests(percentageRandomizeOfRequests)//0.0
				.RandomizeNumberOfRequests(randomizeNumberOfRequests)//false
				.Build();
	}
	
	private  void CreateLoadBalancer(TypeOfLoadBalancer tlb)
	{
		try 
		{
			//loadBalancer = LoadBalancer.Build(GetTypeOfLoadBalancer(), listOfServers);
			loadBalancer = LoadBalancer.Build(tlb, listOfServers);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private  void CreateSimulation(Client client,LoadBalancer loadBalancer,List<ServerUnit> listOfServers)
	{
		simulation = new Simulation(client, loadBalancer, listOfServers);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		listOfLoadBalancerButtons = new ArrayList<JRadioButton>();		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocation(50, 50);
		frame.setTitle("Load Balancer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblGenerateOnlyOneRequestPerIteration = new JLabel("Generate only one request per iteration");
		lblGenerateOnlyOneRequestPerIteration.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenerateOnlyOneRequestPerIteration.setBounds(960, 490, 300, 50);
		frame.getContentPane().add(lblGenerateOnlyOneRequestPerIteration);
		
		JButton btnRunAllOnePerIteration = new JButton("Run all");
		btnRunAllOnePerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{	
					setNumberOfIteration(1);
					if(listOfServers.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Add the server!");
					clearTable();
				}else {			
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunAllOneRequestPerIteration();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
				}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation! Don't forger about select iterators");
				}
			}
		});
		btnRunAllOnePerIteration.setBounds(960, 620, 300, 50);
		frame.getContentPane().add(btnRunAllOnePerIteration);
		
		JButton btnX10OnePerIteration = new JButton("x 10");
		btnX10OnePerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(10);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnceOneRequest();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX10OnePerIteration.setBounds(1035, 550, 65, 50);
		frame.getContentPane().add(btnX10OnePerIteration);
		
		JButton btnX1OnePerIteration = new JButton("x 1");
		btnX1OnePerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(1);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnceOneRequest();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX1OnePerIteration.setBounds(960, 550, 65, 50);
		frame.getContentPane().add(btnX1OnePerIteration);
		
		JButton btnX1000OnePerIteration = new JButton("x 1000");
		btnX1000OnePerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(1000);
				if(listOfServers.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Add the server!");
					clearTable();
				}else {
					
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnceOneRequest();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
				}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX1000OnePerIteration.setBounds(1185, 550, 75, 50);
		frame.getContentPane().add(btnX1000OnePerIteration);
		
		JButton btnX100OnePerIteration = new JButton("x 100");
		btnX100OnePerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(100);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnceOneRequest();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX100OnePerIteration.setBounds(1110, 550, 65, 50);
		frame.getContentPane().add(btnX100OnePerIteration);
		
		JButton btRunAllSet = new JButton("Run all");
		btRunAllSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{	
					setNumberOfIteration(1);
					if(listOfServers.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Add the server!");
					clearTable();
				}else {			
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunAll();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
				}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation! Don't forger about select iterators");
				}
			}
		});
		btRunAllSet.setBounds(960, 400, 300, 50);
		frame.getContentPane().add(btRunAllSet);
		
		JButton btnX1SetPerIteration = new JButton("x 1");
		btnX1SetPerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(1);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnce();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX1SetPerIteration.setBounds(960, 330, 65, 50);
		frame.getContentPane().add(btnX1SetPerIteration);
		
		JButton btnX10SetSetPerIteration = new JButton("x 10");
		btnX10SetSetPerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(10);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnce();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX10SetSetPerIteration.setBounds(1035, 330, 65, 50);
		frame.getContentPane().add(btnX10SetSetPerIteration);
		
		JButton btnX100SetSetPerIteration = new JButton("x 100");
		btnX100SetSetPerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(100);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnce();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX100SetSetPerIteration.setBounds(1110, 330, 65, 50);
		frame.getContentPane().add(btnX100SetSetPerIteration);
		
		JButton btnX1000SetSetPerIteration = new JButton("x 1000");
		btnX1000SetSetPerIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					setNumberOfIteration(1000);
					if(listOfServers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Add the server!");
						clearTable();
					}else {
					for (int i = 0; i < numberOfIteration; i++)
					{
						simulation.RunOnce();
					}
					System.out.println(numberOfIteration);	
					print();
					UpdateUI();
					}
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null, "Select algorithm and create simulation!");
				}
			}
		});
		btnX1000SetSetPerIteration.setBounds(1185, 330, 75, 50);
		frame.getContentPane().add(btnX1000SetSetPerIteration);
		
		JLabel lblGenerateSetOfRequestsPerIteration = new JLabel("Generate set of requests per iteration");
		lblGenerateSetOfRequestsPerIteration.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenerateSetOfRequestsPerIteration.setBounds(960, 270, 300, 50);
		frame.getContentPane().add(lblGenerateSetOfRequestsPerIteration);
		
		JLabel lblClientsSettings = new JLabel("Clients settings:");
		lblClientsSettings.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClientsSettings.setBounds(30, 30, 150, 50);
		frame.getContentPane().add(lblClientsSettings);
		
		JLabel lblLoadBalancer = new JLabel("Load balancer:");
		lblLoadBalancer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoadBalancer.setBounds(30, 350, 120, 50);
		frame.getContentPane().add(lblLoadBalancer);
		
		JRadioButton radioRandom = new JRadioButton("Random");
		ChosenLoadBalancer.add(radioRandom);
		radioRandom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioRandom.setBounds(30, 600, 220, 50);
		frame.getContentPane().add(radioRandom);
		
		JRadioButton radioWLC = new JRadioButton("Weighted least connections");
		ChosenLoadBalancer.add(radioWLC);
		radioWLC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioWLC.setBounds(30, 548, 220, 50);
		frame.getContentPane().add(radioWLC);
		
		JRadioButton radioLC = new JRadioButton("Least connections");
		ChosenLoadBalancer.add(radioLC);
		radioLC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioLC.setBounds(30, 500, 220, 50);
		frame.getContentPane().add(radioLC);
		
		JRadioButton radioWRR = new JRadioButton("Weighted round robin");
		ChosenLoadBalancer.add(radioWRR);
		radioWRR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioWRR.setBounds(30, 450, 220, 50);
		frame.getContentPane().add(radioWRR);
		
		JRadioButton radioRR = new JRadioButton("Round robin");
		ChosenLoadBalancer.add(radioRR);
		radioRR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioRR.setBounds(30, 400, 220, 50);
		frame.getContentPane().add(radioRR);
		
		tfMinWork = new JTextField();
		tfMinWork.setText("5");
		tfMinWork.setBounds(30, 100, 100, 25);
		frame.getContentPane().add(tfMinWork);
		tfMinWork.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Min work per request");
		lblNewLabel.setBounds(140, 100, 135, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMaxWorkPer = new JLabel("Max work per request");
		lblMaxWorkPer.setBounds(140, 130, 135, 25);
		frame.getContentPane().add(lblMaxWorkPer);
		
		tfMaxWork = new JTextField();
		tfMaxWork.setText("5");
		tfMaxWork.setColumns(10);
		tfMaxWork.setBounds(30, 130, 100, 25);
		frame.getContentPane().add(tfMaxWork);
		
		tfTotalRequests = new JTextField();
		tfTotalRequests.setText("1000");
		tfTotalRequests.setColumns(10);
		tfTotalRequests.setBounds(30, 167, 100, 25);
		frame.getContentPane().add(tfTotalRequests);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of requests");
		lblTotalNumberOf.setBounds(140, 167, 150, 25);
		frame.getContentPane().add(lblTotalNumberOf);
		
		tfRequestsInOneSet = new JTextField();
		tfRequestsInOneSet.setText("10");
		tfRequestsInOneSet.setColumns(10);
		tfRequestsInOneSet.setBounds(30, 204, 100, 25);
		frame.getContentPane().add(tfRequestsInOneSet);
		
		JLabel lblNumberOfRequests = new JLabel("Number of requests in one set");
		lblNumberOfRequests.setBounds(140, 205, 178, 25);
		frame.getContentPane().add(lblNumberOfRequests);		
		
		checkRandomizeNumberOfRequestsInSet.setBounds(30, 241, 300, 25);
		frame.getContentPane().add(checkRandomizeNumberOfRequestsInSet);
		
		tfRandomizePercentage = new JTextField();
		tfRandomizePercentage.setEditable(false);
		tfRandomizePercentage.setText("1");
		tfRandomizePercentage.setColumns(10);
		tfRandomizePercentage.setBounds(30, 283, 100, 25);
		frame.getContentPane().add(tfRandomizePercentage);
		
		JLabel lblOfRandomization = new JLabel("% of randomization");
		lblOfRandomization.setBounds(140, 284, 120, 25);
		frame.getContentPane().add(lblOfRandomization);
		
		JLabel lblRemainingRequests = new JLabel("Remaining requests:");
		lblRemainingRequests.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRemainingRequests.setBounds(975, 100, 153, 50);
		frame.getContentPane().add(lblRemainingRequests);
		
		JLabel lblIterationNumber = new JLabel("Iteration number:");
		lblIterationNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIterationNumber.setBounds(993, 167, 135, 50);
		frame.getContentPane().add(lblIterationNumber);		
		
		JButton btnResetSettings = new JButton("Reset settings");
		btnResetSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioLC.setSelected(false);
				radioRR.setSelected(false);
				radioRandom.setSelected(false);
				radioWLC.setSelected(false);
				radioWRR.setSelected(false);
				tfMaxWork.setText("5");
				tfMinWork.setText("5");
				tfRandomizePercentage.setText("1");
				tfRequestsInOneSet.setText("10");
				tfTotalRequests.setText("1000");
				lblx.setText("0");
				lblIterationNumberNumber.setText("0");
				lblRemainingRequestsNumber.setText("0");
				tfRandomizePercentage.setEditable(false);
				tfRandomizePercentage.enable();
				clearTable();
				removeServers();
				checkRandomizeNumberOfRequestsInSet.setSelected(false);
			}
		});
		
		btnResetSettings.setBounds(740, 620, 200, 50);
		frame.getContentPane().add(btnResetSettings);
		
		lblRemainingRequestsNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemainingRequestsNumber.setBounds(1160, 100, 80, 50);
		frame.getContentPane().add(lblRemainingRequestsNumber);		
		
		lblIterationNumberNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIterationNumberNumber.setBounds(1160, 170, 80, 50);
		frame.getContentPane().add(lblIterationNumberNumber);
		
		JLabel lblRequestsWaitingTo = new JLabel("Requests waiting to be assigned by load balancer:");
		lblRequestsWaitingTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRequestsWaitingTo.setBounds(761, 30, 387, 50);
		frame.getContentPane().add(lblRequestsWaitingTo);		
		
		lblx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblx.setBounds(1160, 30, 80, 50);
		frame.getContentPane().add(lblx);
			
		JButton btnCreateSimulation = new JButton("Create simulation");
		btnCreateSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				
				if(checkRandomizeNumberOfRequestsInSet.isSelected()) {
					tfRandomizePercentage.setEditable(true);
				}else {
					tfRandomizePercentage.setEditable(false);
				}			
				
				Enumeration<AbstractButton> clb = ChosenLoadBalancer.getElements();
				while(clb.hasMoreElements()) {
					JRadioButton jrb = (JRadioButton) clb.nextElement();
					if(jrb.isSelected()) {
						if(jrb.getText()=="Random") {
							chosenTypeOfLoadBalancer = TypeOfLoadBalancer.Random;
							CreateClients();
							CreateLoadBalancer(chosenTypeOfLoadBalancer);
							CreateSimulation(client,loadBalancer,listOfServers);
							
						}
						else if(jrb.getText()=="Weighted least connections") {
							chosenTypeOfLoadBalancer = TypeOfLoadBalancer.WeightedLeastConnections;
							CreateClients();
							CreateLoadBalancer(chosenTypeOfLoadBalancer);
							CreateSimulation(client,loadBalancer,listOfServers);
						
						}
						else if(jrb.getText()=="Least connections") {
							chosenTypeOfLoadBalancer = TypeOfLoadBalancer.LeastConnections;
							CreateClients();
							CreateLoadBalancer(chosenTypeOfLoadBalancer);
							CreateSimulation(client,loadBalancer,listOfServers);
							
						}else if(jrb.getText()=="Weighted round robin") {
							chosenTypeOfLoadBalancer = TypeOfLoadBalancer.WeightedRoundRobin;
							/*test _test = new test(chosenTypeOfLoadBalancer,minWork,maxWork,initRequestNumb,requestsInOneQueue,randomizePercentage,randomIsSet);
							_test.run();*/
							CreateClients();
							CreateLoadBalancer(chosenTypeOfLoadBalancer);
							CreateSimulation(client,loadBalancer,listOfServers);
							
						}else if(jrb.getText()=="Round robin") {
							chosenTypeOfLoadBalancer = TypeOfLoadBalancer.RoundRobin;
							CreateClients();
							CreateLoadBalancer(chosenTypeOfLoadBalancer);
							CreateSimulation(client,loadBalancer,listOfServers);							
						}
					}	
				}
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			}
		});
		btnCreateSimulation.setBounds(507, 620, 200, 50);
		frame.getContentPane().add(btnCreateSimulation);	
		
		JLabel lblServerSettings = new JLabel("Servers settings:");
		lblServerSettings.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblServerSettings.setBounds(332, 30, 150, 50);
		frame.getContentPane().add(lblServerSettings);
		
		tfServerCapacity = new JTextField();
		tfServerCapacity.setText("100");
		tfServerCapacity.setColumns(10);
		tfServerCapacity.setBounds(332, 99, 100, 25);
		frame.getContentPane().add(tfServerCapacity);
		
		JLabel labelServerCapacity = new JLabel("Server Capacity");
		labelServerCapacity.setBounds(444, 93, 90, 25);
		frame.getContentPane().add(labelServerCapacity);
		
		tfWeightServer = new JTextField();
		tfWeightServer.setText("1");
		tfWeightServer.setColumns(10);
		tfWeightServer.setBounds(332, 129, 100, 25);
		frame.getContentPane().add(tfWeightServer);
		
		JLabel labelWeightServer = new JLabel("Server Weight");
		labelWeightServer.setBounds(444, 130, 90, 25);
		frame.getContentPane().add(labelWeightServer);
		
		JButton btnAddServer = new JButton("Add ");
		btnAddServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAddServerByUser();
			}
		});
		btnAddServer.setBounds(444, 203, 118, 28);
		frame.getContentPane().add(btnAddServer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 265, 668, 308);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JButton buttonDefaultOption = new JButton("Set default options");
		buttonDefaultOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateServers();
				tfMaxWork.setText("5");
				tfMinWork.setText("5");
				tfRandomizePercentage.setText("1");
				tfRequestsInOneSet.setText("10");
				tfTotalRequests.setText("1000");
				lblx.setText("0");
				lblIterationNumberNumber.setText("0");
				lblRemainingRequestsNumber.setText("0");
			}
		});
		buttonDefaultOption.setBounds(272, 620, 200, 50);
		frame.getContentPane().add(buttonDefaultOption);
		
		tfPerformanceFactor = new JTextField();
		tfPerformanceFactor.setText("1");
		tfPerformanceFactor.setColumns(10);
		tfPerformanceFactor.setBounds(332, 166, 100, 25);
		frame.getContentPane().add(tfPerformanceFactor);
		
		JLabel labelPerformanceFactor = new JLabel("Performance Factor");
		labelPerformanceFactor.setBounds(444, 167, 129, 25);
		frame.getContentPane().add(labelPerformanceFactor);
		
		JButton btnShowServers = new JButton("Show Servers");
		btnShowServers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				initServerTable();
				updateServerTable();
			}
		});
		btnShowServers.setBounds(330, 203, 118, 28);
		frame.getContentPane().add(btnShowServers);
		
		JLabel lblRememberAfter = new JLabel("REMEMBER! After each server selection CREATE SYMULATION again");
		lblRememberAfter.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		lblRememberAfter.setForeground(Color.RED);
		lblRememberAfter.setBounds(275, 575, 665, 25);
		frame.getContentPane().add(lblRememberAfter);
		
		initTable();
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	public  void UpdateUI() {			
		int numberOfRequestWait = loadBalancer.GetNumberOfRequestsWaitingToBeAssigned();
		int remainingRequest =client.getRemainingRequestsNumber();
		int iterationNumber =getNumberOfIteration();
			lblx.setText(""+numberOfRequestWait);
			lblIterationNumberNumber.setText(""+iterationNumber);
			lblRemainingRequestsNumber.setText(""+remainingRequest);
			updateTable();
	}

	private void print()
	{
		System.out.println(client.toString());
		System.out.println(loadBalancer.toString());
		for (ServerUnit item : listOfServers)
		{
			System.out.println(item.toString());
		}
		System.out.println("\n");
	}
	public void initTable() {
		clearTable();
		String[] columnHeader= {"ID","Capacity","Weight","Performance Factor","Number of request being served","Percantage Fill","Can Accept Request"};
		model.setColumnIdentifiers(columnHeader);
		table.setModel(model);
		
	}
	public void initServerTable() {
		String[] columnHeader= {"ID","Capacity","Weight","Performance Factor"};
		model.setColumnIdentifiers(columnHeader);
		table.setModel(model);
	}
	public void updateTable() {
		clearTable();
		initTable();
		for(int i=0;i<listOfServers.size();i++) {
			int id = listOfServers.get(i).getServerId();
			int capacity=listOfServers.get(i).getServerCapacity();
			double weight = listOfServers.get(i).getWeight();
			double performanceFactor=listOfServers.get(i).getPerformanceFactor();
			int numberOfRequestBeingServed=listOfServers.get(i).GetNumberOfRequestsBeingServed();
			double percantageFill=listOfServers.get(i).GetPercantageFill();
			boolean canAcceptRequest=listOfServers.get(i).CheckIfCanAcceptRequest();
			
			Object[] row= {id,capacity,weight,performanceFactor,numberOfRequestBeingServed,percantageFill,canAcceptRequest};
			model.addRow(row);
			
		}Object[] row2= {"","","","","","",""};
		model.addRow(row2);
	}
	
	public void updateServerTable() {
		for(int i=0;i<listOfServers.size();i++) {
			int id = listOfServers.get(i).getServerId();
			int capacity=listOfServers.get(i).getServerCapacity();
			double weight = listOfServers.get(i).getWeight();
			double performanceFactor=listOfServers.get(i).getPerformanceFactor();
			
			Object[] row= {id,capacity,weight,performanceFactor};
			model.addRow(row);
		}
	}
	
	public void clearTable() {
		while(model.getRowCount() > 0)
		{
		    model.removeRow(0);
		}
	}
	public void removeServers() {
		try {
		//for (ServerUnit item: listOfServers) 
			listOfServers.removeAll(listOfServers);
		    
		}catch(ConcurrentModificationException e1) {
			JOptionPane.showMessageDialog(null, "All options have been deleted. Servers too.");
		}
		
	}
	

}

