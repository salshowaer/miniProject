import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
  
public class FilePreview extends JFrame {  
  
    private JPanel panel; 
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem open, exit;
    private JTextArea textZone;
    private JScrollPane scroll;
    private JFileChooser findFile;
    private File afile;
    private boolean chk = true;
  
    public FilePreview() { 
    
    	setTitle("Gift Text Editor - New File");  
    	
    	findFile = new JFileChooser();
   	
    	menu = new JMenuBar();	
    	file = new JMenu("File");	
    	open = new JMenuItem("Open");
    	exit = new JMenuItem("Exit");
    	
    	file.add(open);
    	file.add(exit);
    	
    	menu.add(file);
    	setJMenuBar(menu);
    	
    	panel = new JPanel();
    	panel.setBackground(Color.black);
    	textZone = new JTextArea(27,50);
    	textZone.setEditable(false);

    	textZone.setLineWrap(true);
    	textZone.setWrapStyleWord(true);
    	scroll = new JScrollPane(textZone);
    	
    	panel.add(scroll);
    	add(panel);
    	    		    	    	
    	setSize(600,500);
    	setResizable(false);
    	setLocationRelativeTo(null);
    	
    	if(StartingGUI.preview==0)
    		previewCurrentFile();
    	
    	if(StartingGUI.preview==1)
    		previewAnotherFile();
    	
    	if(chk)
    		setVisible(true);
    	
    	
    	open.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				previewAnotherFile();		
			}
		});
    	
    	
    	exit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
        
    }  
    
    private void previewCurrentFile(){
    	
    	Font font = new Font("Aria", Font.BOLD, 12);
		textZone.setFont(font);
		
		afile = StartingGUI.currentFile;
		    	
	    if(afile!=null){
	    	String filePath = afile.getPath();
	    			
	    	try {
	    		setTitle("Gift Text Editor - " + filePath);
	    		Scanner scan = new Scanner(new FileInputStream(afile));
	    		while(scan.hasNextLine()){
	    			textZone.append(scan.nextLine() + "\n");
	    		}
	    		scan.close();
				
	    	} catch (FileNotFoundException e) {}
	    }
	    else{
	    	JOptionPane.showMessageDialog(null, "No question saved to preview");
	    	chk = false;
	    }
    }
    
    
    private void previewAnotherFile(){
    	
    	Font font = new Font("Aria", Font.BOLD, 12);
		textZone.setFont(font);
		
    	int openResult = findFile.showOpenDialog(null);
    	
		if(openResult == JFileChooser.APPROVE_OPTION){
			afile = findFile.getSelectedFile();
			textZone.setText("");
    	
	    	if(afile.canRead()){
	    		String filePath = afile.getPath();
	    		
	    		if(filePath.endsWith(".txt")){
	    			
	    			try {
	    				setTitle("Gift Text Editor - " + filePath);
	    				Scanner scan = new Scanner(new FileInputStream(afile));
	    				while(scan.hasNextLine()){
	    					textZone.append(scan.nextLine() + "\n");
	    				}
	    				scan.close();
					
	    			} catch (FileNotFoundException e) {}
	    		}
	    		else{
	    			JOptionPane.showMessageDialog(null, "Only .txt files");
	    		}
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "Error loading file!");
	    	}
    	}
    	else
    		chk = false;
    }
  
}  
