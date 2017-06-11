package groceryBuddy;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GUI extends JFrame {
	static JFrame frame = new JFrame();
	static DefaultListModel listModel = new DefaultListModel();
	static JList list = new JList(listModel);

	public void createGUI() {

		Container container = frame.getContentPane();
		JMenuBar menuBar = new JMenuBar();
		JMenu groceryBuddyMenu = new JMenu("GroceryBuddy");
		JMenu fileMenu = new JMenu("File");
		JMenuItem aboutMenuItem = new JMenuItem("About GroceryBuddy",
				new ImageIcon("Icons/AboutIcon.png"));
		groceryBuddyMenu.add(aboutMenuItem);
		JMenu newSubMenu = new JMenu("New...");
		JMenuItem newListItemMenuItem = new JMenuItem("Grocery List Item",
				new ImageIcon("Icons/CheckListItemIcon.png"));
		newListItemMenuItem.addActionListener(new NewCheckListItemAction());
		JMenuItem newListMenuItem = new JMenuItem("Grocery List Buddy",
				new ImageIcon("Icons/CheckListIcon.png"));
		newSubMenu.add(newListMenuItem);
		newSubMenu.add(newListItemMenuItem);
		newSubMenu.setIcon(new ImageIcon("Icons/NewIcon.png"));
		fileMenu.add(newSubMenu);
		JPanel listContentsPanel = new JPanel();
		JPanel listPanel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(450, 650));

		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter()

		{

			public void mouseClicked(MouseEvent event)

			{
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());
				CheckListItem item = (CheckListItem) list.getModel()
						.getElementAt(index);
				item.setSelected(!item.isSelected());
				if (item.isSelected()) {
					item.strikeThrough();
				} else {
					item.unStrikeThrough();
				}
				list.repaint(list.getCellBounds(index, index));
			}
		});

		menuBar.add(groceryBuddyMenu);
		menuBar.add(fileMenu);
		menuBar.add(Box.createHorizontalGlue());

		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(menuBar);
		container.add(listPanel);
		container.add(listContentsPanel);
		listContentsPanel.add(scrollPane);
		frame.pack();
		frame.setVisible(true);

	}

	class NewCheckListItemAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String itemName = CheckListItem.getItemName();
			if (itemName != null) {
				listModel.addElement(new CheckListItem(new JLabel(itemName)));
			}
		}
	}

}