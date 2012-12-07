import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class Matching extends JPanel {
	
	private JPanel panel;
	private JScrollPane scrPanel;
	private JTextField txtTitle;
	private TextArea txtBody;
	private String content;
	
	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	int i=0;

	public Matching() {
		
		setLayout(new MigLayout("", "[right][grow,fill][grow,fill]", "[][grow][][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "spanx, growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion,"alignx right,aligny top");
		
		txtBody = new TextArea();
		add(txtBody, "spanx, grow, wrap");
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow]", "[]"));

		scrPanel = new JScrollPane(panel);
		add(scrPanel, "skip 1, spanx, grow, wrap");
		
		
		rowList.add(new Row(letters[i]));
		
		panel.add(rowList.get(i),"growx, wrap");
		
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		JButton btnAdd = new JButton("Add");
		add(btnAdd);
		
		JButton btnDel = new JButton("Delete");
		add(btnDel);
		
		
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				buildMatch(++i);
				scrPanel.updateUI();
			}
		});
		
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(i>0)
				{
					removeMatch(i--);
					scrPanel.updateUI();
				}
				else
					JOptionPane.showMessageDialog(null, "You must have at least one Match");
			}
		});
		
		saveAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if((txtTitle.getText().compareTo("")==0) 
					| (txtBody.getText().compareTo("")==0) 
					| (rowList.get(0).getTxt1().getText().compareTo("")==0)
					| (rowList.get(0).getTxt2().getText().compareTo("")==0))
							
						JOptionPane.showMessageDialog(null, "Complete the question before save it");
						
				else{
					
					content = "::" + txtTitle.getText() + "::\n" + txtBody.getText() + "{\n";
					
					for (int i = 0; i < rowList.size(); i++) {
						
						content += "=" + rowList.get(i).getTxt1().getText() + " -> " + rowList.get(i).getTxt2().getText() + "\n";
					}
					
					content += "}\n\n";
					
					StartingGUI.newSaveFile(content);

					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
						
					for (int i = 0; i < rowList.size(); i++) {								
						rowList.get(i).getTxt1().setText("");
						rowList.get(i).getTxt2().setText("");
					}
				}
			}
		});
	}


	protected void buildMatch(int i) {
		
		rowList.add(new Row(letters[i]));
		
		for (int x = 0; x <= i; x++) {
			
			panel.add(rowList.get(x),"growx, wrap");
		}	
	}

	protected void removeMatch(int i) {
				
		panel.remove(rowList.get(i));
		rowList.remove(i);
	}

	private class Row extends JPanel {
		
		private JTextField txt1;
		private JTextField txt2;
		private String row;
		

		public Row(String arow) {
			
			super();
			this.row = arow;
			
			setLayout(new MigLayout("", "[][grow][][grow]","[]"));

			txt1 = new JTextField();
			txt2 = new JTextField();
			
			add(new JLabel(row));
			add(txt1,"growx");

			add(new JLabel(row));
			add(txt2,"growx");
			
		}

		public JTextField getTxt1() {
			return txt1;
		}


		public void setTxt1(JTextField txt1) {
			this.txt1 = txt1;
		}


		public JTextField getTxt2() {
			return txt2;
		}


		public void setTxt2(JTextField txt2) {
			this.txt2 = txt2;
		}
		
	}
}

