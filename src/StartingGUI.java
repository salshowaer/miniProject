import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;


	public class StartingGUI {
		
	    private JMenuBar menu;
	    private JMenu file;
	    private JMenuItem anew, open, gallery, exit;
	    static JFileChooser findFile;
	    static File currentFile, afile;
	    static String content;
	    static PrintWriter out;
	    static JFrame frame;
		
	    public StartingGUI(){
	    	
	    frame = new JFrame("Gift Text Editor - New File"); 	
		
		findFile = new JFileChooser();
		
		menu = new JMenuBar();
    	
    	file = new JMenu("File");
    	
    	anew = new JMenuItem("New");
    	open = new JMenuItem("Open");
    	gallery = new JMenuItem("Gallery");
    	exit = new JMenuItem("Exit");
    	
    	file.add(anew);
    	file.add(open);
    	file.add(gallery);
    	file.add(exit);
			
    	menu.add(file);
    	frame.setJMenuBar(menu);
				
		JTabbedPane tab = new JTabbedPane();
		tab.add("True/False",new TrueFalse());
		tab.add("Multiple Choice",new MultipleChoice());
		tab.add("Multiple Answer", new MultipleAnswer());
		tab.add("Matching", new Matching());
		tab.add("Missing Word", new MissingWord());
		tab.add("Number Range",new NumberRange());
		tab.add("Essay", new Essay());
		
		frame.add(tab);
		
		frame.setSize(1000,500);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
		
		
    	anew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int message = JOptionPane.showConfirmDialog(null, "Did you save your questions before leaving?", "Leaving current file",
				        JOptionPane.YES_NO_OPTION);
				
				if (message == JOptionPane.YES_OPTION){
					currentFile=null;
					newSaveFile("");				
				}
			}
		});
    	
    	open.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				openFile();
			}
		});
    	
    	gallery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new FileGallery();
			}
		});
    	
    	    	
    	exit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);				
			}
		});
	}
		
    
		private void openFile(){
	    	
	    	int openResult = findFile.showOpenDialog(null);
			if(openResult == JFileChooser.APPROVE_OPTION)
				afile = findFile.getSelectedFile();
	    	
	    	if(afile.canRead()){
	    		String filePath = afile.getPath();
	    		
	    		if(filePath.endsWith(".txt")){
	    			currentFile = afile;
	    			frame.setTitle("Gift Text Editor - " + filePath);
	    		}
	    		else{
	    			JOptionPane.showMessageDialog(null, "Only .txt files");
	    		}
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "Error loading file!");
	    	}
	    }
		
	    public static void newSaveFile(String content){
	    	
	    	if(currentFile==null){
				int saveResult = findFile.showSaveDialog(null);
				if(saveResult == JFileChooser.APPROVE_OPTION)
					currentFile = findFile.getSelectedFile();
			}
	    	
	    	if(currentFile!=null){
	 
		    	String filePath = currentFile.getPath();
		    	
		    	if(!filePath.endsWith(".txt"))
		    		filePath+=".txt";
		    	
		    	try{
		    		out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
		    		out.append(content);
		    		out.close();
		    		frame.setTitle("Gift Text Editor - " + filePath);
		    	}catch (Exception e) {}	
	    	}
	    }
	    	    
		
		public static void main(String[] args) {
			
			new StartingGUI();
			
		}

}
