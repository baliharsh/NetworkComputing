import java.net.*;
import java.io.*;

public class ServerThread extends Thread {

	
	private Socket Socket = null;
	private TransactionState SharedStateObject;
	private String ThreadName;


	public ServerThread(Socket Socket, String threadName, TransactionState SharedStateObject) {
		super("ServerThread");
		this.Socket =Socket;
		this.SharedStateObject =SharedStateObject;
		this.ThreadName= threadName;

	}

	  public void run() {
		  System.out.println(ThreadName+" started");
		    try {
		      System.out.println(ThreadName+" initialising.");
		      PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
		      BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
		      String inputLine, outputLine;

		      while ((inputLine = in.readLine()) != null) {
		    	  // Get a lock first
		    	  try { 
		    		  SharedStateObject.acquireLock();  
		    		  outputLine = SharedStateObject.processInput(ThreadName, inputLine);
		    		  out.println(outputLine);
		    		  SharedStateObject.releaseLock();  
		    	  } 
		    	  catch(InterruptedException e) {
		    		  System.err.println("Failed to get lock when reading:"+e);
		    	  }
		      }
		       out.close();
		       in.close();
		       Socket.close();

		    } catch (IOException e) {
		      e.printStackTrace();
		    }
}
}
