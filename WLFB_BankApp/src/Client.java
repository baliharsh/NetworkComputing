import java.io.*;
import java.net.*;

public class Client {
	/* Taken from The Tutorial: Action Server Code (Professor Simon Taylor)
	https://blackboard.brunel.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_52767_1&content_id=_1478484_1
	 and modified by Harsh Bali 20-11-21 */
	
    public static void main(String[] args) throws IOException {

        //Setting up a Client socket 
        Socket ClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int SocketNumber = 4545;
        String ServerName = "192.168.187.1";
        //client's identity
        String ClientID = "Client1";


        //try to connect to server
        try {
            ClientSocket = new Socket(ServerName, SocketNumber);
            out = new PrintWriter(ClientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about the host: server");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ SocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

         
        System.out.println(ClientID + " connected to server");
        
     //Client informs user what requests can be made to server
        System.out.println(
               	"Enter the following TRANSACTION options with the value example:"
               		+ System.lineSeparator()+"1) Add value"+ 
               		System.lineSeparator()+"2) Subtract value"+System.lineSeparator()
               		+ "3) Transfer Username value"
               		);
        
        // Client speaks first 
        while (true) {
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("SENDING: " + fromUser + " to Server");
                out.println(fromUser);
                
            }
            fromServer = in.readLine();
            System.out.println( "RECEIVED: "+ fromServer);
            
        }
            
        
   
    }
}
