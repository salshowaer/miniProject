import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

	public class StartingGUI {
		
		private JToolBar toolBar;
	    private JMenuBar menu;
	    private JMenu fileMenu;
	    static JFileChooser findFile = new JFileChooser();
	    static File currentFile, afile;
	    static String content, filePath;
	    static int preview;
	    static PrintWriter out;
	    static JFrame frame;
	    private JTabbedPane tab = new JTabbedPane();
	    private Action newAction,openAction,prevCurrentAction,prevAnotherAction,exitAction;

	public StartingGUI(){
	    	
	    frame = new JFrame("Gift Text Editor - New File"); 	
		
		toolBar = new JToolBar();
		menu = new JMenuBar();
    	fileMenu = new JMenu("File");
    	fileMenu.setMnemonic('F');
    	
    	newAction = new NewAction();
		openAction = new OpenAction();
		prevCurrentAction = new PrevCurrentAction();
		prevAnotherAction = new PrevAnotherAction();
		exitAction = new ExitAction();
    	
    	toolBar.add(newAction);				toolBar.addSeparator();
    	toolBar.add(openAction);			toolBar.addSeparator();
    	toolBar.add(prevCurrentAction);		toolBar.addSeparator();
    	toolBar.add(prevAnotherAction);		toolBar.addSeparator();
    	toolBar.add(exitAction);			toolBar.addSeparator();
    	    	
    	fileMenu.add(newAction);
    	fileMenu.add(openAction);
    	fileMenu.add(prevCurrentAction);
    	fileMenu.add(prevAnotherAction);
    	fileMenu.add(exitAction);
    	
    	menu.add(fileMenu);
						
		tab.add("True/False"		, new TrueFalse());
		tab.add("Multiple Choice"	, new MultipleChoice());
		tab.add("Multiple Answer"	, new MultipleAnswer());
		tab.add("Matching"			, new Matching());
		tab.add("Short Answer"		, new ShortAnswer());
		tab.add("Numerical"		, new Numerical());
		tab.add("Essay"				, new Essay());
		tab.add("Description"		, new Description());
		
		JPanel panel = new JPanel();
		panel.add(toolBar, BorderLayout.CENTER);
    	
		frame.setJMenuBar(menu);
		frame.add(panel,BorderLayout.SOUTH);
		frame.add(tab,BorderLayout.CENTER);
		
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);		   	
	} 
		
///////////////////////////////////////////////////////////////////////////////		    
	    private class NewAction extends AbstractAction {

			public NewAction() {
				putValue(NAME, "New File");
				putValue(SMALL_ICON, new ImageIcon(("images/new.png")));
				putValue(SHORT_DESCRIPTION, "New Gift.txt file");
				putValue(MNEMONIC_KEY, new Integer('N'));
			}

			public void actionPerformed(ActionEvent e) {
				
				int message = 0;				
				
				if(currentFile!=null){
					message = JOptionPane.showConfirmDialog(null, 
							"Have you saved your questions before leaving?",
							"Leaving current file",JOptionPane.YES_NO_OPTION);
				
					if (message == JOptionPane.YES_OPTION){
						currentFile=null;
						newSaveFile("");
					}
				}
				else
					newSaveFile("");
			}
		}
///////////////////////////////////////////////////////////////////////////////	    
	    private class OpenAction extends AbstractAction {

			public OpenAction() {
				putValue(NAME, "Open & Append");
				putValue(SMALL_ICON, new ImageIcon(("images/open.png")));
				putValue(SHORT_DESCRIPTION, "Open & Append");
				putValue(MNEMONIC_KEY, new Integer('O'));
			}

			public void actionPerformed(ActionEvent e) {
				
				openFile();			
			}
		}
/////////////////////////////////////////////////////////////////////////////	     
	    private class PrevCurrentAction extends AbstractAction {

			public PrevCurrentAction() {
				putValue(NAME, "Preview Current File");
				putValue(SMALL_ICON, new ImageIcon(("images/prevCurrent.png")));
				putValue(SHORT_DESCRIPTION, "Preview Current File");
				putValue(MNEMONIC_KEY, new Integer('C'));
			}

			public void actionPerformed(ActionEvent e) {
				
				preview = 0;
				new FilePreview();			
			}
		}
/////////////////////////////////////////////////////////////////////////////	  
	    private class PrevAnotherAction extends AbstractAction {

			public PrevAnotherAction() {
				putValue(NAME, "Preview Another File");
				putValue(SMALL_ICON, new ImageIcon(("images/prevAnother.png")));
				putValue(SHORT_DESCRIPTION, "Preview Another File");
				putValue(MNEMONIC_KEY, new Integer('A'));
			}

			public void actionPerformed(ActionEvent e) {
				
				preview = 1;
				new FilePreview();			
			}
		}	    
/////////////////////////////////////////////////////////////////////////////	    
	    private class ExitAction extends AbstractAction {

			public ExitAction() {
				putValue(NAME, "Exit");
				putValue(SMALL_ICON, new ImageIcon(("images/exit.png")));
				putValue(SHORT_DESCRIPTION, "Exit");
				putValue(MNEMONIC_KEY, new Integer('E'));
			}

			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);			
			}
		}
/////////////////////////////////////////////////////////////////////////////	    
	    

	    
		private void openFile(){
	    	
	    	int openResult = findFile.showOpenDialog(null);
			
	    	if(openResult == JFileChooser.APPROVE_OPTION){
				afile = findFile.getSelectedFile();
	    	
		    	if(afile.canRead()){
		    		filePath = afile.getPath();
		    		
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
	    }
		
	   
		public static void newSaveFile(String content){
	    	
	    	if(currentFile==null){
				int saveResult = findFile.showSaveDialog(null);
				if(saveResult == JFileChooser.APPROVE_OPTION)
					currentFile = findFile.getSelectedFile();
			}
	    	
	    	if(currentFile!=null){
	    		
    			filePath = currentFile.getPath();
		    	
		    	if(!filePath.endsWith(".txt")){
		    		filePath+=".txt";
		    		currentFile = new File(filePath);
		    	}
		    	try{
		    		out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
		    		out.append(content);
		    		out.close();
		    		frame.setTitle("Gift Text Editor - " + filePath);
		    	}catch (Exception e) {}	
	    	}
	    }

}
