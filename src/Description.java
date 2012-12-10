import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class Description extends JPanel {
	
	private JTextField txtTitle;
	private TextArea txtBody;
	private String content;
	
	public Description() {

		setLayout(new MigLayout("", "[right][grow,fill]", "[][grow][]"));
		
		JLabel lblTitle = new JLabel("Exam Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		txtBody = new TextArea();
		JScrollPane scrPane = new JScrollPane(txtBody);
		add(scrPane, "grow, wrap");
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtTitle.setText("");
				txtBody.setText("");
				
			}
		});
		add(btnClear, "cell 0 2,growx");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		saveAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if((txtTitle.getText().compareTo("")==0) | (txtBody.getText().compareTo("")==0))
					JOptionPane.showMessageDialog(null, "Complete the question before save it");
				
				else{
					
					content = "::" + txtTitle.getText() + "::\n" + txtBody.getText() + " {}" + "\n\n";
					
					StartingGUI.newSaveFile(content);
							
					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
				}
			}
		});
		
	}

}
