import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MouseClass {

	final static int port = 8811;
	static DatagramSocket ss;
	final static float sensitivity = 1.6f;
	final static float scrollsensitivity = -0.16f;
	static float remX = 0, remY = 0;
	static float remAmount = 0;

	@Override
	protected void finalize() {
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		ss.close();
	}

	private static void handleFunctionality() throws Exception {
		ss = new DatagramSocket(port);
		Robot robot = new Robot();
		robot.setAutoDelay(5);
		KeyEventHandler keyEventHandler = new KeyEventHandler();
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, 1024);
			ss.receive(packet);
			String str = new String(data, 0, packet.getLength());
			// System.out.println(str);
			String[] array = str.split(" ");
			String action = array[0];
			if (action.equals("moved")) {
				float offsetX = Float.parseFloat(array[1]);
				float offsetY = Float.parseFloat(array[2]);
				mouseMoved(robot, offsetX, offsetY);
			} else if (action.equals("click")) {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} else if (action.equals("clickhold")) {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			} else if (action.equals("clickrelease")) {
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} else if (action.equals("rightclick")) {
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			} else if (action.equals("scroll")) {
				float scrollAmount = Float.parseFloat(array[1]);
				mouseScroll(robot, scrollAmount);
			} else if (action.equals("keydown") || action.equals("keyup")
					|| action.equals("keyin")) {
				keyEventHandler.handleKeyEvent(robot, action, array[1]);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		// System.out.println("Connection established");
		// System.out.println(InetAddress.getLocalHost() + " :" + port);

		// Show UI
		try {
			new AppFrame();
			handleFunctionality();
		}catch(BindException e){
			System.out.println("Port " + port + " already in use");
			System.exit(1);
		}
		catch (Exception e) {
			System.out.println("Some internal error occured: " + e.toString());
			System.exit(1);
		}
	}

	private static void mouseScroll(Robot robot, float scrollAmount) {
		float amount = scrollAmount * scrollsensitivity + remAmount;
		int iAmount = Math.round(amount);
		remAmount = amount - iAmount;
		robot.mouseWheel(iAmount);
	}

	private static void mouseMoved(Robot robot, float x, float y) {
		float dispX = x * sensitivity + remX;
		float dispY = y * sensitivity + remY;

		int ix = Math.round(dispX);
		int iy = Math.round(dispY);

		remX = dispX - ix;
		remY = dispY - iy;

		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		b.x += ix;
		b.y += iy;

		robot.mouseMove(b.x, b.y);
	}
}
