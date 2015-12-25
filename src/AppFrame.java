import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

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
	
	private String getIp() {
		String ip = null;
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			StringBuilder buff = new StringBuilder();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while(inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					//if the address is not a loopback address and is an IPv4 
					if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						buff.append(inetAddress.getHostAddress() + ", ");
					}
				}
			}
			
			//remove the extra , added at the last
			buff.delete(buff.length()-2, buff.length());
			ip = buff.toString();
		} catch (Exception e) {
			ip = "Error determining IP address";
		}
		return ip;
	}
	
	private void initializeVars() {
		lines = new String[9];
		lines[0] = "The server application is now running";
		String ip = getIp();
		
		lines[1] = " ";
		lines[2] = "Your IP : " + ip;
		lines[3] = " ";
		lines[4] = "Enter this IP on the start screen of the";
		lines[5] = "Remote Mouse & Keyboard application on your phone to begin.";
		lines[6] = " ";
		lines[7] = " ";
		lines[8] = "Copyright 2013 Pandurang Kamath";
	}

	@Override
	public void paint(Graphics g) {
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
