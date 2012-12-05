import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Easy Gift Format Question Editor"); 
		JTabbedPane tab = new JTabbedPane();
		
		tab.add("True-False",new True_False());
		tab.add("Multiple Choice", new MultipleChoice());
		tab.add("Matching", new Matching());
		tab.add("Missing Word", new Missing_Word());
		
		frame.add(tab);
		frame.setDefaultCloseOperation(3);
		frame.pack();
		frame.setVisible(true);
		
//		ArrayList<Integer> s = new ArrayList<Integer>();
//		
//		s.add(1);
//		s.add(2);
//		s.add(3);
//		s.remove(2);
//		
//		System.out.println(s);
		
	}

}
