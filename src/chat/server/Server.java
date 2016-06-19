package chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 * @throws IOException
	 */

	private static boolean flag = true;
	private static boolean start = true;
	private static DataInputStream dis = null;

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9999);
			while (flag) {
				Socket s = ss.accept();
				System.out.println("a client connected");
				dis = new DataInputStream(s.getInputStream());
				while (start) {
					String content = dis.readUTF();
					System.out.println(content);
				}

			}
			dis.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
