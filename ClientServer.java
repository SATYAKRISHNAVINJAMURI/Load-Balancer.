package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer extends Thread{
	ServerSocket ss2 = null;
	public ClientServer(ServerSocket serversocket){
		ss2 = serversocket;
	}
	public void run() {
		Socket s = null;
	    int count = 0;
	    System.out.println("Server at Peer Listening......");

	    while(true){
	        try{
	            s= ss2.accept();
	            count++;
	            System.out.println("Processing a request of a peer");
	            new ServePeer(s).start();

	        }

	        catch(Exception e){
	        	e.printStackTrace();
	        	System.out.println("Connection Error");

	        }
	    }
    }	
}
