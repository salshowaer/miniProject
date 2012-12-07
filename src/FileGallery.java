import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
  
public class FileGallery extends JFrame {  
  
    private JPanel panel; 
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem open, exit;
    private JTextArea textZone;
    private JScrollPane scroll;
    private JFileChooser findFile;
    private File afile;
  
  
    public FileGallery() { 
    
    	setTitle("Gift Text Editor - New File");  
    	setResizable(false);
    	setLayout(null);
    	
    	findFile = new JFileChooser();
    	
    	panel = new JPanel();
    	Dimension panelSize = new Dimension(600,400);
    	panel.setPreferredSize(panelSize);	
    	panel.setBackground(Color.black);
    	setContentPane(panel);
    	
    	menu = new JMenuBar();	
    	file = new JMenu("File");	
    	open = new JMenuItem("Open");
    	exit = new JMenuItem("Exit");
    	
    	file.add(open);
    	file.add(exit);
    	
    	textZone = new JTextArea(25,54);
    	textZone.setEditable(false);
    	Dimension tZSize = textZone.getPreferredSize();
    	textZone.setBounds(3,0,tZSize.width,tZSize.height);
    	textZone.setLineWrap(true);
    	textZone.setWrapStyleWord(true);
    	scroll = new JScrollPane(textZone);
    	panel.add(scroll);
    	
    	menu.add(file);
    	setJMenuBar(menu);
    	
    	Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
    	setBounds(400, 100, dimension.width - 200, dimension.height - 100);
    	
    	openFile();
    	
    	pack();
    	setVisible(true);
    	
    	
    	open.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				openFile();		
			}
		});
    	
    	
    	exit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
        
    }  
    
    private void openFile(){
    	
    	int openResult = findFile.showOpenDialog(null);
		if(openResult == JFileChooser.APPROVE_OPTION)
			afile = findFile.getSelectedFile();
    	
    	if(afile.canRead()){
    		String filePath = afile.getPath();
    		String fileContents = "";
    		
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
  
}  
