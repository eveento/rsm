package data;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

public class Application
{

	private JFrame frame;

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
		
		JLabel lblClientParameters = new JLabel("Client parameters:");
		springLayout.putConstraint(SpringLayout.NORTH, lblClientParameters, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblClientParameters, 10, SpringLayout.WEST, frame.getContentPane());
		lblClientParameters.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblClientParameters);
		
		JLabel lblLoadBalancer = new JLabel("Load Balancer:");
		springLayout.putConstraint(SpringLayout.WEST, lblLoadBalancer, 10, SpringLayout.WEST, frame.getContentPane());
		lblLoadBalancer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblLoadBalancer);
		
		JRadioButton RadioRandom = new JRadioButton("Random");
		springLayout.putConstraint(SpringLayout.NORTH, RadioRandom, 18, SpringLayout.NORTH, btnX);
		springLayout.putConstraint(SpringLayout.WEST, RadioRandom, 0, SpringLayout.WEST, lblClientParameters);
		frame.getContentPane().add(RadioRandom);
		
		JRadioButton RadioWLC = new JRadioButton("WeightedLeastConnections");
		springLayout.putConstraint(SpringLayout.NORTH, RadioWLC, 15, SpringLayout.NORTH, lblRun);
		springLayout.putConstraint(SpringLayout.WEST, RadioWLC, 0, SpringLayout.WEST, lblClientParameters);
		springLayout.putConstraint(SpringLayout.SOUTH, RadioWLC, -6, SpringLayout.NORTH, RadioRandom);
		frame.getContentPane().add(RadioWLC);
		
		JRadioButton RadioLC = new JRadioButton("Least Connections");
		springLayout.putConstraint(SpringLayout.WEST, RadioLC, 0, SpringLayout.WEST, lblClientParameters);
		springLayout.putConstraint(SpringLayout.SOUTH, RadioLC, -6, SpringLayout.NORTH, RadioWLC);
		frame.getContentPane().add(RadioLC);
		
		JRadioButton RadioWRR = new JRadioButton("Weighted Round Robin");
		springLayout.putConstraint(SpringLayout.WEST, RadioWRR, 0, SpringLayout.WEST, lblClientParameters);
		springLayout.putConstraint(SpringLayout.SOUTH, RadioWRR, -6, SpringLayout.NORTH, RadioLC);
		frame.getContentPane().add(RadioWRR);
		
		JRadioButton RadioRR = new JRadioButton("Round Robin");
		springLayout.putConstraint(SpringLayout.SOUTH, lblLoadBalancer, -6, SpringLayout.NORTH, RadioRR);
		springLayout.putConstraint(SpringLayout.WEST, RadioRR, 0, SpringLayout.WEST, lblClientParameters);
		springLayout.putConstraint(SpringLayout.SOUTH, RadioRR, -6, SpringLayout.NORTH, RadioWRR);
		frame.getContentPane().add(RadioRR);
	}
}
