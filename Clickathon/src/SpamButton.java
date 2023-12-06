import javax.swing.JButton;
import javax.swing.JFrame;

public class SpamButton extends ClickButton {
	private int clickCount;			//How many times the button has been clicked

	public SpamButton(int spamPointValue) {
		super("CLICK", spamPointValue);
		clickCount = 0;
	}

	public void addClick() {
		clickCount += 1;
	}

	public int getClickCount() {
		return clickCount;
	}
}
