package project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {

	public static void main(String[] args) throws UnknownHostException ,Exception{
		String ip  = "localhost";
		String peer_ip = null;
		int peer_port = 0;
		int port = 9998;
		ServerSocket ss2 = new ServerSocket(5556); //different numbers for different clients.
		Socket s = new Socket(ip,port);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		PrintWriter pw = new PrintWriter(os);
		Scanner scan = new Scanner(System.in);
		new ClientServer(ss2).start();         // run a server program to server other peers.
		try {
			while(true) {
				/*
				 * if a new process was generated check for the threshold in the same system
				 * if it does not exceed threshold just execute. 
				 * if it exceeds send a getsys request for the server.
				 * get an ip and port number to send process and get output
				 * if got nosys request then execute the command in the same system.
				 */
				String job = scan.nextLine();
				if(job.equals("nojob")) {
					pw.write("quit\n");
					pw.flush();
					break;
				}
				else {
					pw.write("getsys\n");
					pw.flush();
					System.out.println("Command sent");
					peer_ip = br.readLine();
					peer_port = br.read();
					System.out.println("Got details");
					//process these details and make a connection to that particular peer
					System.out.println("Making connection with peer");
					@SuppressWarnings("resource")
					Socket peer_socket = new Socket(peer_ip,peer_port);
					BufferedReader peer_br = new BufferedReader(new InputStreamReader(peer_socket.getInputStream()));
					OutputStreamWriter 	peer_os = new OutputStreamWriter(peer_socket.getOutputStream());
					PrintWriter peer_pw = new PrintWriter(peer_os);
					peer_pw.write(job + "\n");
					peer_pw.flush();
					String output = peer_br.readLine();    // get output from peer.
					String out[] = output.split("\t");
					System.out.println("Output:");
					for(String str :out) {
						System.out.println(str);
					}
				}
					
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			System.out.println("Shutting down so no process can be sent to server but can execute process for other system");
			pw.close();
			os.close();
			br.close();
			s.close();
			scan.close();
		}

	}

}
