import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


	public class StartingGUI extends JFrame{
		
		public StartingGUI(){
		
		super("Easy Gift Format Question Editor"); 
		JTabbedPane tab = new JTabbedPane();
		
		File gift = new File("Gift.txt");
		gift.delete();
		
		tab.add("True/False",new TrueFalse());
		tab.add("Multiple Choice", new MultipleChoice());
		tab.add("Matching", new Matching());
		tab.add("Missing Word", new MissingWord());
		tab.add("Essay", new Essay());
		tab.add("Numerical",new Numerical());
		tab.add("Number Range",new MathRange());
		
		add(tab);
				
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
	
		setBounds(200, 100, dimension.width - 200, dimension.height - 100);
		setDefaultCloseOperation(3);
		
		setSize(1000,500);
		setVisible(true);
	}
		
		public static void main(String[] args) {
			
			new StartingGUI();
			
		}

}
