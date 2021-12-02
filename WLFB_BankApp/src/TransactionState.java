import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class TransactionState {

	ArrayList<User> Users = new ArrayList<User>();
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers
	private double SharedVariable;
	User CurrentUser;
	User TransferUser;
	String[] temp;
	private String ErrorMsg= "You are suppossed to enter: add value, subtract value or transfer Username value";
	public TransactionState(ArrayList<User> users) {
		this.Users =users;
	}


	//accquire a lock if the lock is available 
	public synchronized void acquireLock() throws InterruptedException{
		Thread CurrentThread = Thread.currentThread(); 
		System.out.println(CurrentThread.getName()+" is attempting to acquire a lock!");	
		++threadsWaiting;
		while (accessing) {  
			System.out.println(CurrentThread.getName()+
					" waiting to accquire lock, someone is already accessing.. ");
			wait();
		}
		--threadsWaiting;
		accessing = true;
		System.out.println(CurrentThread.getName()+" got a lock!"); 
	}

	// Releases the lock and inform other threads that the lock is released.
	public synchronized void releaseLock() {
		accessing = false;
		notifyAll();
		Thread me = Thread.currentThread(); // get a ref to the current thread
		System.out.println(me.getName()+" released a lock!");
	}

	//How server should handle the input by 3 clients
	public synchronized String processInput(String myThreadName, String theInput) {
		String theOutput=null;
		System.out.println(theInput);
		if(myThreadName.equalsIgnoreCase("thread1")) {
			CurrentUser = Users.get(0); 	
			theOutput =   Transactions(theInput);
		}
		else if(myThreadName.equalsIgnoreCase("thread2")) {
			CurrentUser = Users.get(1); 
			theOutput = Transactions(theInput);
		}
		else if(myThreadName.equalsIgnoreCase("thread3")){
			CurrentUser = Users.get(2); 
			theOutput = Transactions(theInput);
		}
		return theOutput;
	}

	// DETERMINE transaction and then DO them
	public synchronized String Transactions(String theInput) {
		String theOutput="";

		if(theInput.contains("add")) {
			if(spliter(theInput).matches("[0-9]+")) {
				theOutput=	AddMoney(CurrentUser,spliter(theInput));
			}
			else {
				theOutput= ErrorMsg;
			}
		}
		else if(theInput.contains("subtract")) {
			if(spliter(theInput).matches("[0-9]+")) {
				theOutput=	SubtractMoney(CurrentUser, spliter(theInput));
			}
			else {
				theOutput= ErrorMsg;
			}
		}
		else if(theInput.contains("transfer")){
			String temp = spliter(theInput);
			String[] funds = theInput.split(" ");

			for(int i=0; i <Users.size();i++){
				if(temp.equalsIgnoreCase(Users.get(i).getUserName())) {
					TransferUser= Users.get(i);
					System.out.println(TransferUser.getUserName());
				}
			}
			if((TransferUser!=null)&&(funds[2].matches("[0-9]+") &&(!TransferUser.getUserName().equals(CurrentUser.getUserName())))){
			theOutput= TransferMoney(CurrentUser, TransferUser, funds[2]);
			TransferUser=null;
			}
			else {
				theOutput= ErrorMsg;
			}
			
		}
		else {
			theOutput= ErrorMsg;
		}
		return theOutput;
	}

	//split the message
	public synchronized String spliter(String Input) {
		String theOutput=null;
		try {
		temp = Input.split(" ");
		theOutput =temp[1];
		}
		catch(Exception e) {
			theOutput= ErrorMsg;
		}
		return theOutput;
	}
	//convert into double
	public synchronized double DoubleConverter(String Input) {
		return Double.valueOf(Input);
	}

	//deposit money
	public synchronized String AddMoney(User user, String FundsAdded) {
		System.out.println("Username =" +user.getUserName());
		System.out.println("User Balance before: " +user.getCurrentBalance());
		user.setCurrentBalance(user.getCurrentBalance()+ DoubleConverter(FundsAdded));
		System.out.println("User Balance afer: " +user.getCurrentBalance());
		return "Funds added and balance is: " + user.getCurrentBalance();	
	}

	//withdraw money
	public synchronized String SubtractMoney(User user, String FundsDeducted) {
		System.out.println("Username =" +user.getUserName());
		System.out.println("User Balance before: " +user.getCurrentBalance());
		user.setCurrentBalance( user.getCurrentBalance()- DoubleConverter(FundsDeducted));
		System.out.println("User Balance afer: " +user.getCurrentBalance());
		return "Funds withdrawn and balance is: " + user.getCurrentBalance();
	}
	//transfer money
	public synchronized String TransferMoney(User userX, User userY, String Funds) {
		System.out.println(userX.getUserName() +  " balance before: " + userX.getCurrentBalance()+ Funds);
		System.out.println(userY.getUserName()+ " balance before: "+userY.getCurrentBalance()+Funds);
		userX.setCurrentBalance( userX.getCurrentBalance()- DoubleConverter(Funds));
		userY.setCurrentBalance(userY.getCurrentBalance()+ DoubleConverter(Funds));
		System.out.println(userX.getUserName() +  " balance after: " + userX.getCurrentBalance()+ Funds);
		System.out.println(userY.getUserName()+ " balance after: "+userY.getCurrentBalance()+Funds);
		return "Balance after transfer is :" + userX.getCurrentBalance();
	}
}
