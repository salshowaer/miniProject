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

public class Numerical extends JPanel {

	
	private JPanel panel, labelPanel, txtPanel;
	private JScrollPane scrText, scrPanel;
	private JTextField textField;;
	private JLabel label;

	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private int i=0;
	
	public static PrintWriter out;

	

	public Numerical() {
		
		
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
								
						rowList.get(i).getSpn2().setValue(0);
															
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
		
		private SpinnerModel spn1Model, spn2Model;
		private JSpinner spn1,spn2;
		private JTextField txt1,txt2;
		
		
		public Row(String row){
					
			spn1Model = new SpinnerNumberModel(0, 0, 100, 5);
			spn1 = new JSpinner(spn1Model);
			spn2Model = new SpinnerNumberModel(0, 0, 100, 5);
			spn2 = new JSpinner(spn2Model);
			txt1 = new JTextField();
			txt2 = new JTextField();
			
			setLayout(new MigLayout("", "[][grow][][][][grow][][]","[]"));
			
			add(new JLabel(row));
			add(txt1,"growx");
			add(new JLabel("Tolerance +/-"));
			add(spn1,"growx");
			
			add(new JLabel("Feedback (Optional)"));
			add(txt2,"growx");
			add(new JLabel("%"));
			add(spn2,"growx");
			
		}


		public JSpinner getSpn1() {
			return spn1;
		}


		public void setSpn1(JSpinner spn1) {
			this.spn1 = spn1;
		}


		public JSpinner getSpn2() {
			return spn2;
		}


		public void setSpn2(JSpinner spn2) {
			this.spn2 = spn2;
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
		