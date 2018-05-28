package data;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import java.awt.FlowLayout;


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
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { Application
		 * window = new Application(); window.frame.setVisible(true); } catch (Exception
		 * e) { e.printStackTrace(); } } });
		 */
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
	}

}
