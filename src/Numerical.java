import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Numerical extends JPanel {
	
	private JPanel panel;
	private JScrollPane scrPanel;
	private JTextField txtTitle,answer;
	private TextArea txtBody;
	private JSpinner spnRange, spnMark;
	private String content;

	public Numerical() {	
		
		setLayout(new MigLayout("", "[right][grow, center][]", "[][grow][][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "span, growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		txtBody = new TextArea();
		add(txtBody, "spanx, grow, wrap");
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][]50[][]50[][]", "[]"));
		
		scrPanel = new JScrollPane(panel);
		add(scrPanel,"skip 1, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtTitle.setText("");
				txtBody.setText("");
					
				answer.setText("");
				spnMark.setValue(0);
				spnRange.setValue(0);
				
			}
		});
		add(btnClear, "cell 0 2,growx");
		
		answer = new JTextField(5);
			
		panel.add(new JLabel("Answer"));
		panel.add(answer);
				
		panel.add(new JLabel("Range +/-"));
		SpinnerModel spnModel1 = new SpinnerNumberModel(0, 0, 100, 1);
		spnRange = new JSpinner(spnModel1);
		panel.add(spnRange);
		
		panel.add(new JLabel("Mark %"));
		SpinnerModel spnModel2 = new SpinnerNumberModel(0, 0, 100, 5);
		spnMark = new JSpinner(spnModel2);
		panel.add(spnMark);
	
		
		saveAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				if((txtTitle.getText().compareTo("")==0) 
					| (txtBody.getText().compareTo("")==0) 
					| (answer.getText().compareTo("")==0))
								
						JOptionPane.showMessageDialog(null, "Complete the question before save it");
							
				else{
						
					content = "::" + txtTitle.getText() + "::\n" + txtBody.getText() +
								" {#\n=" + answer.getText() + ":0" +
								"\n=%" + spnMark.getValue() + "%" + answer.getText() + ":" + spnRange.getValue();
						
					content += "\n}\n\n";
					
					StartingGUI.newSaveFile(content);
						
					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
						
					answer.setText("");
					spnMark.setValue(0);
					spnRange.setValue(0);
				}			
			}
			
		});		
	}	
}	
	