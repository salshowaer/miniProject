import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Easy Gift Format Question Editor"); 
		JTabbedPane tab = new JTabbedPane();
		
		tab.add("True-False",new True_False());
		tab.add("Multiple Choice", new MultipleChoice());
		
		frame.add(tab);
		frame.setDefaultCloseOperation(3);
		frame.pack();
		frame.setVisible(true);
		
	}

}
