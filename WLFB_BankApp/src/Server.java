import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
	/* Taken from The Tutorial: Action Server Code (Professor Simon Taylor)
	https://blackboard.brunel.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_52767_1&content_id=_1478484_1
	 and modified by Harsh Bali 20-11-21 */
	public static void main(String[] args) throws IOException {

		//Setting up a Server socket
		ServerSocket ServerSocket = null;
		boolean listening = true;
		String ServerName = "Server";
		int SocketNumber = 4545;
		
		// ALL user accounts are loaded up into an array
		ArrayList<User> UserDB = new ArrayList<User>();
		User UserA = new User("UserA",1000d); UserDB.add(UserA);
		User UserB = new User("UserB",1000d); UserDB.add(UserB);
		User UserC = new User("UserC",1000d); UserDB.add(UserC);

		
		//Share the user info with the threads
		TransactionState SharedStateObject = new TransactionState(UserDB);


		// Try to make the socket and accept connections
		try {
			ServerSocket = new ServerSocket(SocketNumber);
		} catch (IOException e) {
			System.err.println("Could not start " + ServerName + " at the specified port.");
			System.exit(-1);
		}

		System.out.println(ServerName + " started");
		//Listen for connections and accept them
		while (listening){
			new ServerThread(ServerSocket.accept(),"Thread1", SharedStateObject).start();
			new ServerThread(ServerSocket.accept(),"Thread2", SharedStateObject).start();
			new ServerThread(ServerSocket.accept(),"Thread3", SharedStateObject).start();
		}
		ServerSocket.close();
	}
}
