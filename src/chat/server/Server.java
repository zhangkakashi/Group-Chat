package chat.server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws IOException
	 */

	private static boolean flag = true;
	private static boolean connected = false;
	private static DataInputStream dis = null;
	private static ServerSocket ss = null;
	private static Socket s = null;

	public static void main(String[] args) {
		try {
			ss = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (flag) {
				s = ss.accept();
				connected = true;
				System.out.println("a client connected");
				dis = new DataInputStream(s.getInputStream());
				while (connected) {
					String content = dis.readUTF();
					System.out.println(content);
				}

			}
			dis.close();
			ss.close();
			
		} catch (EOFException e) {
			System.out.println("a client disconnected");
			//e.printStackTrace();
		}catch (Exception e) {
			//System.out.println("a client disconnected");
			e.printStackTrace();
		} finally {
			try {
				if (dis != null) {
					dis.close();
				}
				if (s != null) {
					s.close();
				}
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
