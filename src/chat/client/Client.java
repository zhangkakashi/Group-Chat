package chat.client;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Frame {

	private static final long serialVersionUID = -1363523332801050867L;
	/**
	 * @param args
	 */
	Socket  s = null;
	DataOutputStream dos = null;
	TextField tf = new TextField();
	TextArea ta = new TextArea();

	public static void main(String[] args) {
		new Client().launchFrame();
	}

	public void launchFrame() {
		this.setLocation(400, 300);
		this.setSize(300, 300);
		this.add(tf, BorderLayout.SOUTH);
		this.add(ta, BorderLayout.NORTH);
		this.pack();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				disconnection();
				System.exit(0);
			}

		});
		tf.addActionListener(new TextFieldListerner());
		this.setVisible(true);
		connection();
	}
	
	private void connection(){
		try {
			s = new Socket("localhost", 9999);
			dos = new DataOutputStream(s.getOutputStream());
			System.out.println("connected");
			ta.setText("Connected!\n");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void disconnection(){
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private class TextFieldListerner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String content = tf.getText().trim();
			ta.setText(ta.getText() + content + "\n");
			tf.setText("");
			try {
				 
				dos.writeUTF(content);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
