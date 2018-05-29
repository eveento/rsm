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

import domain.TypeOfLoadBalancer;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class Application
{

	private JFrame frame;
	private JTextField MinWorkField;
	private JTextField MaxWorkField;
	private JTextField RequestsInOneQueueField;
	private JTextField TotalRequestsField;
	private JTextField RandomizePercentageField;
	private List<JRadioButton> listOfLoadBalancerButtons;
	private TypeOfLoadBalancer chosenTypeOfLoadBalancer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		System.out.println("Aplikacja dziala");
		try
		{
			test _test = new test();
			_test.run();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		} finally
		{
			System.out.println("Aplikacja zakonczyla dzialanie");
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		listOfLoadBalancerButtons = new ArrayList<JRadioButton>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 855, 544);
		frame.setLocation(50, 50);
		frame.setTitle("Load Balancer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnX = new JButton("x1");
		springLayout.putConstraint(SpringLayout.WEST, btnX, 457, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnX, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnX);
		
		JButton btnX_1 = new JButton("x10");
		springLayout.putConstraint(SpringLayout.NORTH, btnX, 0, SpringLayout.NORTH, btnX_1);
		springLayout.putConstraint(SpringLayout.EAST, btnX, -6, SpringLayout.WEST, btnX_1);
		springLayout.putConstraint(SpringLayout.WEST, btnX_1, 550, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnX_1, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnX_1);
		
		JButton btnX_2 = new JButton("x100");
		springLayout.putConstraint(SpringLayout.NORTH, btnX_1, 0, SpringLayout.NORTH, btnX_2);
		springLayout.putConstraint(SpringLayout.EAST, btnX_1, -6, SpringLayout.WEST, btnX_2);
		springLayout.putConstraint(SpringLayout.WEST, btnX_2, 643, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnX_2);
		
		JButton btnAll = new JButton("All");
		springLayout.putConstraint(SpringLayout.NORTH, btnAll, 427, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAll, 740, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAll, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAll, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnX_2, 0, SpringLayout.NORTH, btnAll);
		springLayout.putConstraint(SpringLayout.SOUTH, btnX_2, 0, SpringLayout.SOUTH, btnAll);
		springLayout.putConstraint(SpringLayout.EAST, btnX_2, -10, SpringLayout.WEST, btnAll);
		frame.getContentPane().add(btnAll);
		
		JLabel lblRun = new JLabel("Run simulation steps");
		springLayout.putConstraint(SpringLayout.WEST, lblRun, 0, SpringLayout.WEST, btnX);
		springLayout.putConstraint(SpringLayout.SOUTH, lblRun, -6, SpringLayout.NORTH, btnX);
		lblRun.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblRun);
		
		JButton btnRunOnceOne = new JButton("Run once one request");
		springLayout.putConstraint(SpringLayout.NORTH, btnRunOnceOne, 0, SpringLayout.NORTH, btnX);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRunOnceOne, 0, SpringLayout.SOUTH, btnX);
		springLayout.putConstraint(SpringLayout.EAST, btnRunOnceOne, -6, SpringLayout.WEST, btnX);
		frame.getContentPane().add(btnRunOnceOne);
		
		JLabel lblClientParameters = new JLabel("Clients parameters:");
		lblClientParameters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblClientParameters);
		
		JLabel lblLoadBalancer = new JLabel("Load Balancer:");
		springLayout.putConstraint(SpringLayout.WEST, lblClientParameters, 0, SpringLayout.WEST, lblLoadBalancer);
		springLayout.putConstraint(SpringLayout.WEST, lblLoadBalancer, 10, SpringLayout.WEST, frame.getContentPane());
		lblLoadBalancer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblLoadBalancer);
		
		JRadioButton RadioRandom = new JRadioButton("Random");
		springLayout.putConstraint(SpringLayout.NORTH, RadioRandom, 18, SpringLayout.NORTH, btnX);
		springLayout.putConstraint(SpringLayout.WEST, RadioRandom, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(RadioRandom);
		listOfLoadBalancerButtons.add(RadioRandom);
		
		JRadioButton RadioWLC = new JRadioButton("WeightedLeastConnections");
		springLayout.putConstraint(SpringLayout.NORTH, RadioWLC, 15, SpringLayout.NORTH, lblRun);
		springLayout.putConstraint(SpringLayout.WEST, RadioWLC, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, RadioWLC, -6, SpringLayout.NORTH, RadioRandom);
		frame.getContentPane().add(RadioWLC);
		listOfLoadBalancerButtons.add(RadioWLC);
		
		JRadioButton RadioLC = new JRadioButton("Least Connections");
		springLayout.putConstraint(SpringLayout.WEST, RadioLC, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, RadioLC, -6, SpringLayout.NORTH, RadioWLC);
		frame.getContentPane().add(RadioLC);
		listOfLoadBalancerButtons.add(RadioLC);
		
		JRadioButton RadioWRR = new JRadioButton("Weighted Round Robin");
		springLayout.putConstraint(SpringLayout.WEST, RadioWRR, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, RadioWRR, -6, SpringLayout.NORTH, RadioLC);
		frame.getContentPane().add(RadioWRR);
		listOfLoadBalancerButtons.add(RadioWRR);
		
		JRadioButton RadioRR = new JRadioButton("Round Robin");
		springLayout.putConstraint(SpringLayout.WEST, RadioRR, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblLoadBalancer, -6, SpringLayout.NORTH, RadioRR);
		springLayout.putConstraint(SpringLayout.SOUTH, RadioRR, -6, SpringLayout.NORTH, RadioWRR);
		frame.getContentPane().add(RadioRR);
		RadioRR.setSelected(true);
		chosenTypeOfLoadBalancer = TypeOfLoadBalancer.RoundRobin;
		listOfLoadBalancerButtons.add(RadioRR);
		
		JButton btnReset = new JButton("RESET");
		springLayout.putConstraint(SpringLayout.NORTH, btnReset, -58, SpringLayout.SOUTH, btnX);
		springLayout.putConstraint(SpringLayout.SOUTH, btnReset, 0, SpringLayout.SOUTH, btnX);
		springLayout.putConstraint(SpringLayout.EAST, btnReset, -6, SpringLayout.WEST, btnRunOnceOne);
		frame.getContentPane().add(btnReset);
		
		JLabel lblMinWork = new JLabel("Min. work for request");
		springLayout.putConstraint(SpringLayout.SOUTH, lblClientParameters, -6, SpringLayout.NORTH, lblMinWork);
		springLayout.putConstraint(SpringLayout.WEST, lblMinWork, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblMinWork, -426, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblMinWork);
		
		JLabel lblMaxWork = new JLabel("Max. work for request");
		springLayout.putConstraint(SpringLayout.NORTH, lblMaxWork, 14, SpringLayout.SOUTH, lblMinWork);
		springLayout.putConstraint(SpringLayout.WEST, lblMaxWork, 0, SpringLayout.WEST, lblClientParameters);
		frame.getContentPane().add(lblMaxWork);
		
		JLabel lblNumberOfRequests = new JLabel("Number of requests in one queue");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfRequests, 17, SpringLayout.SOUTH, lblMaxWork);
		springLayout.putConstraint(SpringLayout.WEST, lblNumberOfRequests, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNumberOfRequests);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of requests");
		springLayout.putConstraint(SpringLayout.NORTH, lblTotalNumberOf, 16, SpringLayout.SOUTH, lblNumberOfRequests);
		springLayout.putConstraint(SpringLayout.WEST, lblTotalNumberOf, 0, SpringLayout.WEST, lblClientParameters);
		frame.getContentPane().add(lblTotalNumberOf);
		
		JCheckBox RandomizeCheckBox = new JCheckBox("Randomize number of requests in one queue");
		springLayout.putConstraint(SpringLayout.NORTH, RandomizeCheckBox, 16, SpringLayout.SOUTH, lblTotalNumberOf);
		springLayout.putConstraint(SpringLayout.WEST, RandomizeCheckBox, 0, SpringLayout.WEST, lblClientParameters);
		frame.getContentPane().add(RandomizeCheckBox);
		
		JLabel lblOfRandomize = new JLabel("% of randomize");
		springLayout.putConstraint(SpringLayout.NORTH, lblOfRandomize, 12, SpringLayout.SOUTH, RandomizeCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, lblOfRandomize, 0, SpringLayout.WEST, lblClientParameters);
		frame.getContentPane().add(lblOfRandomize);
		
		MinWorkField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, MinWorkField, -3, SpringLayout.NORTH, lblMinWork);
		springLayout.putConstraint(SpringLayout.WEST, MinWorkField, 74, SpringLayout.EAST, lblMinWork);
		frame.getContentPane().add(MinWorkField);
		MinWorkField.setColumns(10);
		
		MaxWorkField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, MaxWorkField, -3, SpringLayout.NORTH, lblMaxWork);
		springLayout.putConstraint(SpringLayout.EAST, MaxWorkField, 0, SpringLayout.EAST, MinWorkField);
		frame.getContentPane().add(MaxWorkField);
		MaxWorkField.setColumns(10);
		
		RequestsInOneQueueField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, RequestsInOneQueueField, 6, SpringLayout.EAST, lblNumberOfRequests);
		springLayout.putConstraint(SpringLayout.SOUTH, RequestsInOneQueueField, 0, SpringLayout.SOUTH, lblNumberOfRequests);
		frame.getContentPane().add(RequestsInOneQueueField);
		RequestsInOneQueueField.setColumns(10);
		
		TotalRequestsField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, TotalRequestsField, 0, SpringLayout.SOUTH, lblTotalNumberOf);
		springLayout.putConstraint(SpringLayout.EAST, TotalRequestsField, 0, SpringLayout.EAST, MinWorkField);
		frame.getContentPane().add(TotalRequestsField);
		TotalRequestsField.setColumns(10);
		
		RandomizePercentageField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, RandomizePercentageField, 6, SpringLayout.EAST, lblOfRandomize);
		springLayout.putConstraint(SpringLayout.SOUTH, RandomizePercentageField, 0, SpringLayout.SOUTH, lblOfRandomize);
		frame.getContentPane().add(RandomizePercentageField);
		RandomizePercentageField.setColumns(10);
	}
}
