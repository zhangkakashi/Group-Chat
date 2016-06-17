package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	private static boolean flag = true;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(999);
		while(flag){
			Socket s = ss.accept();
			System.out.println("a client connected");
		}

	}

}
