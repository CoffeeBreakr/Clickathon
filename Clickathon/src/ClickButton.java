import javax.swing.JButton;
import javax.swing.JFrame;

public class ClickButton extends JButton {
	private int pointValue;

	public ClickButton(String label, int pointValue) {
		super(label);
		this.pointValue = pointValue;
	}

	public ClickButton(int pointValue) {
		super("" + pointValue);
		this.pointValue = pointValue;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
		this.setText("" + pointValue);
	}
}
