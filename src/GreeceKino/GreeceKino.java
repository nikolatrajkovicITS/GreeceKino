package GrckiKino2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class GreeceKino {
	
	static Scanner s = new Scanner(System.in);
		
	public static List<Integer> GreeceKinoNumbers = new ArrayList<Integer>(); 
	public static List<Integer> UsersNumbers = new ArrayList<Integer>();    

	static final int minBroj = 1;  
	static final int maxBroj = 80;

	static int userNumber; 

	static boolean ticketStatus;
	static int numberOfAffected;
	static int enteredNumber;

	static Thread thread;  

	/***********************************************
	 * 
	 * Setters and Getters
	 * 
	 ***********************************************/
	
	public static boolean isTicketStatus() {
		return ticketStatus;
	}
	public static int getNumberOfAffected() {
		return numberOfAffected;
	}
	public static void setNumberOfAffected(int numberOfAffected) {
		GreeceKino.numberOfAffected = numberOfAffected;
	}
	public static int getEnteredNumber() {
		return enteredNumber;
	}
	public static void setEnteredNumber(int enteredNumber) {
		GreeceKino.enteredNumber = enteredNumber;
	}
	public static void setTicketStatus(boolean statusTiketa) { 
		GreeceKino.ticketStatus = statusTiketa;
	}
	public static List<Integer> getGreeceKinoNumbers() { 
		return GreeceKinoNumbers;
	}
	
	public static void setGreeceKinoNumbers(List<Integer> greeceKinoNumbers) {
		GreeceKinoNumbers = greeceKinoNumbers;
	}
	public static List<Integer> getUsersNumbers() {  
		return UsersNumbers;
	}
	public static void setUsersNumbers(List<Integer> UsersNumbers){
		GreeceKino.UsersNumbers = UsersNumbers;
	}
	

	public static int getUserNumber() {
		return userNumber;
	}
	public static void setUserNumber(int userNumber) {
		GreeceKino.userNumber = userNumber;
	}
	/*************************************************
	 * 
	 * METHODS
	 * 
	 *************************************************/
	public static int numberGenerator(int min, int max){  
		int number;
		number = min + (int)(Math.random() * ((max - min) + 1));
		return number;
	}


	@SuppressWarnings("rawtypes")
	public static List GreeceKinoDrawing(){ 
		for(int i = 1; i <= 20;){
			int number = numberGenerator(minBroj, maxBroj);  
			if(GreeceKinoNumbers.contains(number)){  
			}                                     
			else{    
				GreeceKinoNumbers.add(number);                                                          
				i++;    
			}                            
		}
		return GreeceKinoNumbers;   
	}


	public static void ensemblesMyTicket(){ 
		try{	
			int matchedNumbers = 0;          
			int enteredNumber = 0;

			for(int i = 1; i <= 20;){                          
				System.out.println("Enter " + i + ". number");      
				try{                                           
					setUserNumber(s.nextInt());                
				}
				catch(Exception e){
					infoBox("GRESKA", "Ne mozete da unesete slova!");           
					System.exit(0);           
					System.out.println("Mora te ispocetka pokrenuti aplikaciju!");
				}
				if(getUserNumber() == 0 && i > 3){     
					break;            
				} 
				if(getUserNumber() != 0){             
					UsersNumbers.addAll(getUsersNumbers()); 
					i++;   
					enteredNumber++;                      
					setEnteredNumber(enteredNumber);     
					Collections.sort(UsersNumbers);                 
					if(GreeceKinoNumbers.contains(getUserNumber())){    
						matchedNumbers++;                               
						setNumberOfAffected(matchedNumbers);              
					}
				}
				else{   
					infoBox("Sistemska greska!", "Ne možete da pokrenete igru bez minimum 3 broja!");
				}
			}

			Compare(matchedNumbers, enteredNumber);
		
		}
		catch(Exception e){   
			infoBox("GRESKA", "Desila se greska u kreiranju tiketa");
			System.out.println("Aplikacija se gasi!");
			System.exit(0);
		}
	}
	
	public static void Compare(int one, int two){ 
		if(one == two){            
			setTicketStatus(true);   
		}
		else{
			setTicketStatus(false);
		}
	}
	public static void infoBox(String title, String infoMessage){    
		JOptionPane.showMessageDialog(null, infoMessage, title, JOptionPane.INFORMATION_MESSAGE); 
	}

	public static void yesOrNo(String title, String poruka){  
		if (JOptionPane.showConfirmDialog(null, poruka, title,   
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
				== JOptionPane.YES_OPTION) 
		{ 
			// if you click YES do this block code
			game();  
		}                 
		else  
		{
			// if you click NO do this block code
			System.exit(0);  
		}
	}

	@SuppressWarnings("static-access")
	public static void game(){
		infoBox("Hello!", "Welcome to Greece Kino!"); 
		System.out.println("Rule of game:\nChoose from 3 to 20 numbers, between 1 and 80\nThen click 0 when u entered your numbers!");
		System.out.println("After that from the drum draws 20 numbers.");
		GreeceKinoDrawing();   
		ensemblesMyTicket();
		System.out.println("Your entered numbers are: " + UsersNumbers);  
		for(int i = 0; i < 20; i++){ 
			System.out.println((i+1) + " draw number is: " + GreeceKinoNumbers.get(i));  
			try {
				infoBox(" ", "Draw number is: " + GreeceKinoNumbers.get(i));
				thread.sleep(2000);
			} catch (InterruptedException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();  
			}
		}
		if(isTicketStatus()){ 
			System.out.println("You have won a ticket");
		}
		else{  
			System.out.println("You fell Ticket");
		}
		Collections.sort(GreeceKinoNumbers);    
		System.out.println("Drawn numbers are: " + GreeceKinoNumbers);   
		System.out.println("You guessed it: " + getNumberOfAffected() + " from the " + getNumberOfAffected() + " entered numbers.");
	}


	//MAIN METHOD
	public static void main(String[] args) {      
		yesOrNo("Start", "Do you want to start the game?");
	}
}


