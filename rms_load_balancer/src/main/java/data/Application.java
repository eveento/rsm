package data;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

import domain.*;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;

public class Application
{

	private JFrame frame;
	private List<JRadioButton> listOfLoadBalancerButtons;
	private TypeOfLoadBalancer chosenTypeOfLoadBalancer;
	private JTextField tfMinWork;
	private JTextField tfMaxWork;
	private JTextField tfTotalRequests;
	private JTextField tfRequestsInOneSet;
	private JTextField tfRandomizePercentage;
	private final ButtonGroup ChosenLoadBalancer = new ButtonGroup();
	
	private List<ServerUnit> listOfServers;
	private Client client;
	private LoadBalancer loadBalancer;
	private Simulation simulation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		System.out.println("Aplikacja rozpoczyna testowanie");
		try
		{
			test _test = new test();
			_test.run();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		} finally
		{
			System.out.println("Aplikacja zakonczyla testowanie");
		}

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Application window = new Application();
					window.frame.setVisible(true);
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
	
	private void CreateClients()
	{
		client = new Client
				.Builder()
				.MinimumWorkToDo(5)
				.MaximumWorkToDo(5)
				.InitialRequestsNumber(1000)
				.RequestsInOneQueue(35)
				.PercentageRandomizeOfRequests(0.0)
				.RandomizeNumberOfRequests(false)
				.Build();
	}
	
	private void CreateLoadBalancer()
	{
		try 
		{
			loadBalancer = LoadBalancer.Build(GetTypeOfLoadBalancer(), listOfServers);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void CreateSimulation()
	{
		simulation = new Simulation(client, loadBalancer, listOfServers);
	}
	
	private TypeOfLoadBalancer GetTypeOfLoadBalancer()
	{
		TypeOfLoadBalancer chosenTypeOfLoadBalancer = TypeOfLoadBalancer.RoundRobin;
		
		// TODO:
		// implement choosing the load balancer type
		
		return chosenTypeOfLoadBalancer;
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
		btnRunAllOnePerIteration.setBounds(960, 620, 300, 50);
		frame.getContentPane().add(btnRunAllOnePerIteration);
		
		JButton btnX10OnePerIteration = new JButton("x 10");
		btnX10OnePerIteration.setBounds(1035, 550, 65, 50);
		frame.getContentPane().add(btnX10OnePerIteration);
		
		JButton btnX1OnePerIteration = new JButton("x 1");
		btnX1OnePerIteration.setBounds(960, 550, 65, 50);
		frame.getContentPane().add(btnX1OnePerIteration);
		
		JButton btnX1000OnePerIteration = new JButton("x 1000");
		btnX1000OnePerIteration.setBounds(1185, 550, 75, 50);
		frame.getContentPane().add(btnX1000OnePerIteration);
		
		JButton btnX100OnePerIteration = new JButton("x 100");
		btnX100OnePerIteration.setBounds(1110, 550, 65, 50);
		frame.getContentPane().add(btnX100OnePerIteration);
		
		JButton btRunAllSet = new JButton("Run all");
		btRunAllSet.setBounds(960, 400, 300, 50);
		frame.getContentPane().add(btRunAllSet);
		
		JButton btnX1SetPerIteration = new JButton("x 1");
		btnX1SetPerIteration.setBounds(960, 330, 65, 50);
		frame.getContentPane().add(btnX1SetPerIteration);
		
		JButton btnX10SetSetPerIteration = new JButton("x 10");
		btnX10SetSetPerIteration.setBounds(1035, 330, 65, 50);
		frame.getContentPane().add(btnX10SetSetPerIteration);
		
		JButton btnX100SetSetPerIteration = new JButton("x 100");
		btnX100SetSetPerIteration.setBounds(1110, 330, 65, 50);
		frame.getContentPane().add(btnX100SetSetPerIteration);
		
		JButton btnX1000SetSetPerIteration = new JButton("x 1000");
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
		radioWLC.setBounds(30, 550, 220, 50);
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
		lblNewLabel.setBounds(140, 100, 200, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMaxWorkPer = new JLabel("Max work per request");
		lblMaxWorkPer.setBounds(140, 130, 200, 25);
		frame.getContentPane().add(lblMaxWorkPer);
		
		tfMaxWork = new JTextField();
		tfMaxWork.setText("5");
		tfMaxWork.setColumns(10);
		tfMaxWork.setBounds(30, 130, 100, 25);
		frame.getContentPane().add(tfMaxWork);
		
		tfTotalRequests = new JTextField();
		tfTotalRequests.setText("1000");
		tfTotalRequests.setColumns(10);
		tfTotalRequests.setBounds(30, 180, 100, 25);
		frame.getContentPane().add(tfTotalRequests);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of requests");
		lblTotalNumberOf.setBounds(140, 180, 200, 25);
		frame.getContentPane().add(lblTotalNumberOf);
		
		tfRequestsInOneSet = new JTextField();
		tfRequestsInOneSet.setText("10");
		tfRequestsInOneSet.setColumns(10);
		tfRequestsInOneSet.setBounds(30, 230, 100, 25);
		frame.getContentPane().add(tfRequestsInOneSet);
		
		JLabel lblNumberOfRequests = new JLabel("Number of requests in one set");
		lblNumberOfRequests.setBounds(140, 230, 200, 25);
		frame.getContentPane().add(lblNumberOfRequests);
		
		JCheckBox checkRandomizeNumberOfRequestsInSet = new JCheckBox("Randomize number of requests in one set");
		checkRandomizeNumberOfRequestsInSet.setBounds(30, 260, 300, 25);
		frame.getContentPane().add(checkRandomizeNumberOfRequestsInSet);
		
		tfRandomizePercentage = new JTextField();
		tfRandomizePercentage.setEnabled(false);
		tfRandomizePercentage.setText("0");
		tfRandomizePercentage.setColumns(10);
		tfRandomizePercentage.setBounds(30, 290, 100, 25);
		frame.getContentPane().add(tfRandomizePercentage);
		
		JLabel lblOfRandomization = new JLabel("% of randomization");
		lblOfRandomization.setBounds(140, 290, 200, 25);
		frame.getContentPane().add(lblOfRandomization);
		
		JLabel lblRemainingRequests = new JLabel("Remaining requests:");
		lblRemainingRequests.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRemainingRequests.setBounds(960, 100, 200, 50);
		frame.getContentPane().add(lblRemainingRequests);
		
		JLabel lblIterationNumber = new JLabel("Iteration number:");
		lblIterationNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIterationNumber.setBounds(960, 170, 200, 50);
		frame.getContentPane().add(lblIterationNumber);
		
		JButton btnResetSettings = new JButton("Reset settings");
		btnResetSettings.setBounds(740, 620, 200, 50);
		frame.getContentPane().add(btnResetSettings);
		
		JButton btnCreateSimulation = new JButton("Create simulation");
		btnCreateSimulation.setBounds(520, 620, 200, 50);
		frame.getContentPane().add(btnCreateSimulation);
		
		JLabel lblRemainingRequestsNumber = new JLabel("0");
		lblRemainingRequestsNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemainingRequestsNumber.setBounds(1160, 100, 80, 50);
		frame.getContentPane().add(lblRemainingRequestsNumber);
		
		JLabel lblIterationNumberNumber = new JLabel("0");
		lblIterationNumberNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIterationNumberNumber.setBounds(1160, 170, 80, 50);
		frame.getContentPane().add(lblIterationNumberNumber);
		
		JLabel lblRequestsWaitingTo = new JLabel("Requests waiting to be assigned by load balancer:");
		lblRequestsWaitingTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRequestsWaitingTo.setBounds(350, 30, 387, 50);
		frame.getContentPane().add(lblRequestsWaitingTo);
		
		JLabel label_3 = new JLabel("0");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(740, 30, 80, 50);
		frame.getContentPane().add(label_3);
		chosenTypeOfLoadBalancer = TypeOfLoadBalancer.RoundRobin;
	}
}
