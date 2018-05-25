package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServeClient extends Thread{
	Socket socket = null;
	private String request = null;
	public ServeClient(Socket clientsocket){
		socket = clientsocket;
	}
	public void run() {
		BufferedReader br = null;
		OutputStreamWriter os = null;
		PrintWriter pw = null;
		Scanner scan = new Scanner(System.in);
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new OutputStreamWriter(socket.getOutputStream());
			pw = new PrintWriter(os);
			System.out.println("Waiting for the request");
			request = br.readLine();
			System.out.println("Received command "+ request);
		}
		catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		while(!request.equals("quit")) {
			if(request.equals("getsys")){
				/*Check all threshold of all the clients.
				if threshold is less then get that system ip and port number where the
				peer can be contacted to server another peer.
				 */
				try {
					pw.write("localhost\n");
					pw.flush();
					pw.write(5555);
					pw.flush();
					System.out.println("Sent details");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.out.println("Bad Protocol request");
			}
			try {
				request = br.readLine();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Client down");
		try {
			socket.close();
			scan.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
