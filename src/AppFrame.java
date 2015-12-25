import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.InetAddress;

import javax.swing.JFrame;

public class AppFrame extends JFrame {

	private static String title = "Remote Mouse & Keyboard Server (Beta)";
	private String[] lines;

	private static final long serialVersionUID = 4648172894076113183L;

	public AppFrame() {
		super(title);
		initializeVars();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setSize(410, 300);
		setBackground(Color.GRAY);
		setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		setVisible(true);
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		lines = new String[9];
		lines[0] = "The server application is now running";
		String Ip = "";
		try {
			String[] str = InetAddress.getLocalHost().toString().split("/");
			Ip = str[str.length-1];
		} catch (Exception e) {
			Ip = "Error determining IP address";
		}
		lines[1] = " ";
		lines[2] = "Your IP : " + Ip;
		lines[3] = " ";
		lines[4] = "Enter this IP on the start screen of the";
		lines[5] = "Remote Mouse & Keyboard application on your phone to begin.";
		lines[6] = " ";
		lines[7] = " ";
		lines[8] = "Copyright © 2013 Pandurang Kamath";
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppFrame();
		//frame.repaint();
	}*/

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Font fontHeader = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		Font fontNormal = new Font(Font.SERIF, Font.PLAIN, 14);
		g.setFont(fontHeader);
		g.drawString(title, 20, 50);
		g.setFont(fontNormal);
		for(int i = 0, y = 80; i < lines.length; i++, y+=20){
			g.drawString(lines[i], 20, y);
		}
	}

}
