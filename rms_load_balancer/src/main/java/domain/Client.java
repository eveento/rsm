package domain;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {

	private static List<String> list = new ArrayList<String>(); 
	private final String defaultAddress = "192";
	public String clientAddress;
	//*********************************************************
	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		Client.list = list;
	}
	//*********************************************************
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	//*********************************************************
	
	/*
	 * metoda sprawdzajaca czy wygenerowany adres sie nie powtarza
	 * 
	 * nie jestem pewny funkcji checkIfAddressExist
	 */
	private boolean checkIfAddressExist(String address) {
	/*	if(address.isEmpty())
			return false;
		else {
			for(int i=0; i<list.size()-1;i++) {
				try {
					if(list.get(i).getBytes(address).equals(address))
						return true;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return false;*/
		return list.contains(address);
	}
	
	/*
	 * metoda generujada losowo adress ip 192.*.*.* gdzie * jest przedzialem
	 * od <0;255>
	 * metoda zwraca wygenerowany adres w postaci ciagu znakow String 
	 */
	private String generateAddress() {
		Random rand = new Random();
		String first = defaultAddress;
		//**** od 0 do 255 chyba
		int second = rand.nextInt(169);
		int third = rand.nextInt(256);
		int fourth = rand.nextInt(256);
		return first +"."+String.valueOf(second)+"."+String.valueOf(third)+"."+String.valueOf(fourth);
	}
	/*
	 * konstruktor
	 */
	public Client() {
		generateClientAddress();
	}
	/*
	 * metoda generujaca adress ip
	 */
	private void generateClientAddress() {
		String address="";
		do {
			address = generateAddress();
		} while(checkIfAddressExist(address));
	}
}
