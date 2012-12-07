import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class NumberRange extends JPanel {

	
	private JPanel panel;
	private JScrollPane scrPanel;
	private JTextField txtTitle,answer;
	private TextArea txtBody;
	private JSpinner spnRange, spnMark;
	private static PrintWriter out;

	

	public NumberRange() {
		
		
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
									
				try {
						out = new PrintWriter(new BufferedWriter(new FileWriter("Gift.txt", true)));
								
						out.append("::" + txtTitle.getText() + "::\n" + txtBody.getText() +
									" {#\n=" + answer.getText() + ":0" +
									"\n=%" + spnMark.getValue() + "%" + answer.getText() + ":" + spnRange.getValue());
						
						out.append("}\n\n");
						out.close();
								
															
					} catch (IOException e) {
						e.printStackTrace();
					
					} finally {
						
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
	