package chat.client;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client extends Frame {

	private static final long serialVersionUID = -1363523332801050867L;
	/**
	 * @param args
	 */

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
				System.exit(0);
			}

		});
		tf.addActionListener(new TextFieldListerner());
		this.setVisible(true);
	}

	private class TextFieldListerner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String content = tf.getText().trim();
			ta.setText(ta.getText() + content + "\n");
			tf.setText("");
		}
	}
}
