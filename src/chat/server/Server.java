package chat.server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws IOException
	 */

	private static boolean flag = false;

	private static ServerSocket ss = null;

	private static int port = 9999;
	// private static Socket s = null;

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(port);
			flag = true;
		} catch (BindException e){
			System.out.println("Port: " + port + " is in using");
			System.exit(0);
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (flag) {
				Socket s = ss.accept();
				ClientThread c = new ClientThread(s);
				System.out.println("a client connected");
				Thread t = new Thread(c);
				t.start();
			}

		} catch (EOFException e) {
			System.out.println("a client disconnected");
			// e.printStackTrace();
		} catch (Exception e) {
			// System.out.println("a client disconnected");
			e.printStackTrace();
		} finally {
			try {
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	class ClientThread implements Runnable {

		private Socket s = null;
		private DataInputStream dis = null;
		private boolean connected = false;

		public ClientThread(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.connected = true;
		}

		@Override
		public void run() {

			try {
				String content;
				while (connected) {
					content = dis.readUTF();
					System.out.println(content);

				}
			} catch (EOFException e) {
				System.out.println("a client disconnected");
				// e.printStackTrace();
			} catch (Exception e) {
				// System.out.println("a client disconnected");
				e.printStackTrace();
			} finally {
				try {
					if (dis != null) {
						dis.close();
					}
					if (s != null) {
						s.close();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
}
