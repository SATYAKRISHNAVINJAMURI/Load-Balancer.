package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServePeer extends Thread{
	Socket s = null;
	BufferedReader br = null;
	OutputStreamWriter os = null;
	PrintWriter pw = null;
	public ServePeer(Socket s2) {
		// TODO Auto-generated constructor stub
		s = s2;
	}
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			os = new OutputStreamWriter(s.getOutputStream());
			pw = new PrintWriter(os);
			System.out.println("receiving the process");
			String process = br.readLine();
			//execute process and get output.
			pw.write("output sent is" + process + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
				try {
					pw.close();
					br.close();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		

	}
}
