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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Application
{

	private JFrame frame;
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
		btRunAllSet.setBounds(960, 402, 300, 50);
		frame.getContentPane().add(btRunAllSet);
		
		JButton btnX1SetPerIteration = new JButton("x 1");
		btnX1SetPerIteration.setBounds(960, 332, 65, 50);
		frame.getContentPane().add(btnX1SetPerIteration);
		
		JButton btnX10SetSetPerIteration = new JButton("x 10");
		btnX10SetSetPerIteration.setBounds(1035, 332, 65, 50);
		frame.getContentPane().add(btnX10SetSetPerIteration);
		
		JButton btnX100SetSetPerIteration = new JButton("x 100");
		btnX100SetSetPerIteration.setBounds(1110, 332, 65, 50);
		frame.getContentPane().add(btnX100SetSetPerIteration);
		
		JButton btnX1000SetSetPerIteration = new JButton("x 1000");
		btnX1000SetSetPerIteration.setBounds(1185, 332, 75, 50);
		frame.getContentPane().add(btnX1000SetSetPerIteration);
		
		JLabel lblGenerateSetOfRequestsPerIteration = new JLabel("Generate set of requests per iteration");
		lblGenerateSetOfRequestsPerIteration.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenerateSetOfRequestsPerIteration.setBounds(960, 272, 300, 50);
		frame.getContentPane().add(lblGenerateSetOfRequestsPerIteration);
		chosenTypeOfLoadBalancer = TypeOfLoadBalancer.RoundRobin;
	}
}
