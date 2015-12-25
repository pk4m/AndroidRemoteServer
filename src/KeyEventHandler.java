import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import static java.awt.event.KeyEvent.*;

public class KeyEventHandler {

	private HashMap<String, Integer> codes;

	public KeyEventHandler() {
		codes = new HashMap<String, Integer>();
		populateHashMap();
	}

	private void populateHashMap() {
		codes.put("VK_CONTROL", VK_CONTROL);
		codes.put("VK_SPACE", VK_SPACE);
		codes.put("VK_BACKSPACE", VK_BACK_SPACE);
		codes.put("VK_SHIFT", VK_SHIFT);
		codes.put("VK_TAB", VK_TAB);
		codes.put("VK_ENTER", VK_ENTER);

		codes.put("`", VK_BACK_QUOTE);
		codes.put("1", VK_1);
		codes.put("2", VK_2);
		codes.put("3", VK_3);
		codes.put("4", VK_4);
		codes.put("5", VK_5);
		codes.put("6", VK_6);
		codes.put("7", VK_7);
		codes.put("8", VK_8);
		codes.put("9", VK_9);
		codes.put("0", VK_0);

		codes.put("q", VK_Q);
		codes.put("w", VK_W);
		codes.put("e", VK_E);
		codes.put("r", VK_R);
		codes.put("t", VK_T);
		codes.put("y", VK_Y);
		codes.put("u", VK_U);
		codes.put("i", VK_I);
		codes.put("o", VK_O);
		codes.put("p", VK_P);
		codes.put("a", VK_A);
		codes.put("s", VK_S);
		codes.put("d", VK_D);
		codes.put("f", VK_F);
		codes.put("g", VK_G);
		codes.put("h", VK_H);
		codes.put("j", VK_J);
		codes.put("k", VK_K);
		codes.put("l", VK_L);
		codes.put("z", VK_Z);
		codes.put("x", VK_X);
		codes.put("c", VK_C);
		codes.put("v", VK_V);
		codes.put("b", VK_B);
		codes.put("n", VK_N);
		codes.put("m", VK_M);

		// Capital or Shifted values are negated
		codes.put("Q", -1 * VK_Q);
		codes.put("W", -1 * VK_W);
		codes.put("E", -1 * VK_E);
		codes.put("R", -1 * VK_R);
		codes.put("T", -1 * VK_T);
		codes.put("Y", -1 * VK_Y);
		codes.put("U", -1 * VK_U);
		codes.put("I", -1 * VK_I);
		codes.put("O", -1 * VK_O);
		codes.put("P", -1 * VK_P);
		codes.put("A", -1 * VK_A);
		codes.put("S", -1 * VK_S);
		codes.put("D", -1 * VK_D);
		codes.put("F", -1 * VK_F);
		codes.put("G", -1 * VK_G);
		codes.put("H", -1 * VK_H);
		codes.put("J", -1 * VK_J);
		codes.put("K", -1 * VK_K);
		codes.put("L", -1 * VK_L);
		codes.put("Z", -1 * VK_Z);
		codes.put("X", -1 * VK_X);
		codes.put("C", -1 * VK_C);
		codes.put("V", -1 * VK_V);
		codes.put("B", -1 * VK_B);
		codes.put("N", -1 * VK_N);
		codes.put("M", -1 * VK_M);

		codes.put("~", -1 * VK_BACK_QUOTE);
		codes.put("!", -1 * VK_1);
		codes.put("@", -1 * VK_2);
		codes.put("#", -1 * VK_3);
		codes.put("$", -1 * VK_4);
		codes.put("%", -1 * VK_5);
		codes.put("^", -1 * VK_6);
		codes.put("&", -1 * VK_7);
		codes.put("*", -1 * VK_8);
		codes.put("(", -1 * VK_9);
		codes.put(")", -1 * VK_0);
		codes.put("-", VK_MINUS);
		codes.put("_", -1 * VK_MINUS);
		codes.put("=", VK_EQUALS);
		codes.put("+", -1 * VK_EQUALS);

		codes.put("{", -1 * VK_OPEN_BRACKET);
		codes.put("}", -1 * VK_CLOSE_BRACKET);
		codes.put("[", VK_OPEN_BRACKET);
		codes.put("]", VK_CLOSE_BRACKET);
		codes.put("\\", VK_BACK_SLASH);
		codes.put("|", -1 * VK_BACK_SLASH);
		codes.put(";", VK_SEMICOLON);
		codes.put(":", -1 * VK_SEMICOLON);
		codes.put("'", VK_QUOTE);
		codes.put("\"", -1 * VK_QUOTE);
		codes.put(",", VK_COMMA);
		codes.put("<", -1 * VK_COMMA);
		codes.put(".", VK_PERIOD);
		codes.put(">", -1 * VK_PERIOD);
		codes.put("/", VK_SLASH);
		codes.put("?", -1 * VK_SLASH);

		codes.put("VK_LEFT", VK_LEFT);
		codes.put("VK_RIGHT", VK_RIGHT);
	}

	private int getKeyCode(String keyData) {
		System.out.println(keyData);
		Integer val = codes.get(keyData);
		if (val == null) {
			return CHAR_UNDEFINED;
		}
		System.out.println(val.toString());
		return val.intValue();
	}

	public void handleKeyEvent(Robot robot, String action, String keyData) {
		// TODO Auto-generated method stub
		populateHashMap();
		int keyCode = getKeyCode(keyData);
		if (keyCode == CHAR_UNDEFINED) {
			// do nothing
			return;
		}
		try {
			if (action.equals("keydown")) {
				robot.keyPress(keyCode);
			} else if (action.equals("keyup")) {
				robot.keyRelease(keyCode);
			} else if (action.equals("keyin")) {
				if (keyCode < 0) {
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(keyCode * -1);
					robot.keyRelease(keyCode * -1);
					robot.keyRelease(KeyEvent.VK_SHIFT);
				} else {
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
				}
			}
		} catch (Exception e) {
		}
	}
}
