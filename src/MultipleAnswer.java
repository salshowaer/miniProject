import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class MultipleAnswer extends JPanel {
	
	private JPanel panel;
	private JScrollPane scrPanel;
	private JTextField txtTitle;
	private TextArea txtBody;
	private String content;

	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private int i=0;
	
	public MultipleAnswer() {
		
		
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
									
				if((txtTitle.getText().compareTo("")==0) 
					| (txtBody.getText().compareTo("")==0) 
					| (rowList.get(0).getTxt().getText().compareTo("")==0))
								
						JOptionPane.showMessageDialog(null, "Complete the question before save it");
							
				else{
						
					content = "::" + txtTitle.getText() + "::\n" + txtBody.getText() + "{\n";
						
					for (int i = 0; i < rowList.size(); i++) {
							
						int value = (Integer)(rowList.get(i).getSpn().getValue());
							
						if(value > 0)	
							content += "~%" + rowList.get(i).getSpn().getValue() +"%" + rowList.get(i).getTxt().getText() + "\n";
						else
							content += "~" + rowList.get(i).getTxt().getText() + "\n";

					}
					
					content += "}\n\n";
								
					StartingGUI.newSaveFile(content);
					
					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
						
					for (int i = 0; i < rowList.size(); i++) {									
							rowList.get(i).getTxt().setText("");
							rowList.get(i).getSpn().setValue(0);
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
		rowList.remove(i);
	}
		
	private class Row extends JPanel {
		
		private SpinnerModel spnModel;
		private JSpinner spn;
		private JTextField txt;
		private String row;
		
		
		public Row(String arow){
			
			super();
			this.row = arow;
			
			setLayout(new MigLayout("", "[][grow][][]","[]"));
			
			spnModel = new SpinnerNumberModel(0, 0, 100, 5);
			spn = new JSpinner(spnModel);
			
			txt = new JTextField();
			
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
		