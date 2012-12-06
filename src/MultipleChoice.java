import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import net.miginfocom.swing.MigLayout;

public class MultipleChoice extends JPanel {

	
	private JPanel panel, labelPanel, txtPanel;
	private JScrollPane scrText, scrPanel;
	private JTextField textField;;
	private JLabel label;

	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private int i=0;
	
	public static PrintWriter out;

	

	public MultipleChoice() {
		
		
		setLayout(new MigLayout("", "[right][grow,fill][grow,fill]", "[][grow][][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		textField = new JTextField();
		add(textField, "span, growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		JTextArea textArea = new JTextArea();
		scrText = new JScrollPane(textArea);
		add(scrText, "spanx, grow, wrap");
		
		JLabel lblAnswer = new JLabel("Choices");
		add(lblAnswer);
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		scrPanel = new JScrollPane(panel);
		add(scrPanel,"spanx, grow, wrap");
		
		rowList.add(new Row(letters[i]));
		
		panel.add(rowList.get(i),"growx, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		JButton addAnswer = new JButton("Add");
		add(addAnswer);
		
		JButton delAnswer = new JButton("Delete");
		add(delAnswer);
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				addAnswer(++i);
				scrPanel.updateUI();

			}
		});
		
		delAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(i>0)
				{
					delAnswer(i--);
					scrPanel.updateUI();
				}
				else
					JOptionPane.showMessageDialog(null, "You must have at least one Answer");				
			}
		});	
		
		
		saveAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				try {
						out = new PrintWriter(new BufferedWriter(new FileWriter(
								"ExamQ.txt", true)));
								
//						out.append("::" + qusetionTitle + "::" + qusetionQ + "{"
//										+ "\n~%" + setzero(mark1) +"%"+ choise1 + "\n~%" +setzero(mark2) + "%"+ choise2 + "\n~%" + 
//										setzero(mark3) + "%"+ choise3+ "\n~%" + 
//										setzero(mark4) + "%"+ choise4+"\n}" + "\n");
						out.close();
								
						rowList.get(i).getSpn().setValue(0);
															
							} catch (IOException e) {
								e.printStackTrace();
							}
			
						}
			
						private String setzero(String mark) {
							if(mark.compareTo("0")==0){
								mark = "-100";
								return mark;
							}else
								return mark;
							
							
						}
					});
	}
	
	
	private void addAnswer(int i) {
		
		rowList.add(new Row(letters[i]));
		
		for (int x = 0; x <= i; x++) {
			
			panel.add(rowList.get(x),"growx, wrap");
		}	
	}	
	
	private void delAnswer(int i) {
		
		panel.remove(rowList.get(i));			

	}
		
	private class Row extends JPanel {
		
		private SpinnerModel spnModel;
		private JSpinner spn;
		private JTextField txt = new JTextField();
		
		
		public Row(String row){
			
			setLayout(new MigLayout("", "[][grow][][]","[]"));
			
			spnModel = new SpinnerNumberModel(0, 0, 100, 5);
			spn = new JSpinner(spnModel);
			
			add(new JLabel(row));
			add(txt,"growx");
			add(new JLabel("%"));
			add(spn,"growx");
			
		}

		public JTextField getTxt() {
			return txt;
		}

		public void setTxt(JTextField txt) {
			this.txt = txt;
		}

		public JSpinner getSpn() {
			return spn;
		}

		public void setSpn(JSpinner spn) {
			this.spn = spn;
		}
	}

}
		