package project;
//This is the echo server handling multiple clients... Runs fine and good using Threads

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer{
	private static int port = 9998;
	private static String ip = "localhost";
public static void main(String args[]){
	Socket s = null;
    int count = 0;
    ServerSocket ss2=null;
    System.out.println("Server Listening......");
    try{
        ss2 = new ServerSocket(port); // can also use static final PORT_NUM , when defined

    }
    catch(IOException e){
    e.printStackTrace();
    System.out.println("Server error");

    }

    while(count < 3){
        try{
            s= ss2.accept();
            count++;
            System.out.println("connection Established with client"+ count);
            System.out.println("Thread-" + count  + " createdSystem.out.println(\"Shutting down\");");
            new ServeClient(s).start();
            s = null;

        }

        catch(Exception e){
        	e.printStackTrace();
        	System.out.println("Connection Error");

        }
    }
    
    try {
    	ss2.close();
    }
    catch (IOException e) {
		// TODO Auto-generated catch block
    	e.printStackTrace();
	}

}
}
