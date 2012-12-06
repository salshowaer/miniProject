import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class Matching extends JPanel {
	
	private JPanel panel;
	private JScrollPane scrText, scrPanel;
	private JTextField textField;;

	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	int i=0;

	public Matching() {
		
		setLayout(new MigLayout("", "[right][grow,fill][grow,fill]", "[][grow][][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		textField = new JTextField();
		add(textField, "spanx, growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion,"alignx right,aligny top");
		
		JTextArea textArea = new JTextArea();
		scrText = new JScrollPane(textArea);
		add(scrText, "spanx, grow, wrap");
		
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
	}


	protected void buildMatch(int i) {
		
		rowList.add(new Row(letters[i]));
		
		for (int x = 0; x <= i; x++) {
			
			panel.add(rowList.get(x),"growx, wrap");
		}	
	}

	protected void removeMatch(int i) {
				
		panel.remove(rowList.get(i));
	
	}

	private class Row extends JPanel {
		
		private JLabel lab1, lab2;
		private JTextField txt1, txt2;
		
		public Row(String row){
			
			setLayout(new MigLayout("", "[][grow][][grow]","[]"));

			add(new JLabel(row));
			add(new JTextField(),"growx");

			add(new JLabel(row));
			add(new JTextField(),"growx");
			
				
		}

		public JLabel getLab1() {
			return lab1;
		}

		public void setLab1(JLabel lab1) {
			this.lab1 = lab1;
		}

		public JLabel getLab2() {
			return lab2;
		}

		public void setLab2(JLabel lab2) {
			this.lab2 = lab2;
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

