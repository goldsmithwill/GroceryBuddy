package groceryBuddy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CheckList {

}

class CheckListItem extends JLabel

{

	private JLabel label;

	private String labelText;

	private String struckThroughLabelText;

	private boolean isSelected = false;

	public CheckListItem(JLabel label)

	{

		this.label = label;

	}

	public boolean isSelected()

	{

		return isSelected;

	}

	public void setSelected(boolean isSelected)

	{

		this.isSelected = isSelected;

	}

	public String toString()

	{

		return label.getText();

	}

	public void strikeThrough() {
		struckThroughLabelText = "<html><strike>" + label.getText()
				+ "</strike></html>";
		labelText = label.getText();
		label.setText(struckThroughLabelText);
	}

	public void unStrikeThrough() {
		label.setText(labelText);
	}

	public static String getItemName() {
		String s = (String) JOptionPane
				.showInputDialog(
						GUI.frame,
						"Please type in what you want your\nGrocery List Item to be called:",
						"Get Grocery List Item  Name",
						JOptionPane.PLAIN_MESSAGE, new ImageIcon(
								"Icons/WriteIcon.png"), null, "Item");

		if ((s != null) && (s.length() > 0)) {
			return s;
		} else {
			return null;
		}
	}
}
