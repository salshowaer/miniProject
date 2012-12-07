import java.awt.TextArea;
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
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

import net.miginfocom.swing.MigLayout;

public class MultipleChoice extends JPanel {

	
	private JPanel panel;
	private JScrollPane scrPanel;
	private JTextField txtTitle;
	private TextArea txtBody;
	private static PrintWriter out;
	
	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private int i=0;
	

	public MultipleChoice() {
		
		
		setLayout(new MigLayout("", "[right][grow,fill][grow,fill]", "[][grow][][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "span, growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		txtBody = new TextArea();
		add(txtBody, "spanx, grow, wrap");
		
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
					out = new PrintWriter(new BufferedWriter(new FileWriter("Gift.txt", true)));
							
					out.append("::" + txtTitle.getText() + "::\n" + txtBody.getText() + "\n" +
							"{=" + rowList.get(0).getTxt1().getText() + " #" + rowList.get(0).getTxt2().getText());
					
					for (int i = 1; i < rowList.size(); i++) {
						
						out.append(" ~" + rowList.get(i).getTxt1().getText() +" #" +  rowList.get(i).getTxt2().getText());
						
					}
					out.append("}\n\n");
					out.close();
							
														
				} catch (IOException e) {
					e.printStackTrace();
				
				} finally {
					
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
					
			txt1 = new JTextField();
			txt2 = new JTextField();
			
			setLayout(new MigLayout("", "[][grow][][grow]","[]"));
			
			add(new JLabel(row));
			add(txt1,"growx");
			
			add(new JLabel("Feedback (Optional)"));
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
		