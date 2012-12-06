import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class Essay extends JPanel {
	
	private JTextField txtTitle;
	private TextArea txtBody;
	private static PrintWriter out;
	
	public Essay() {

		setLayout(new MigLayout("", "[right][grow,fill]", "[][grow][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		txtBody = new TextArea();
		JScrollPane scrPane = new JScrollPane(txtBody);
		add(scrPane, "grow, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		saveAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					
					out = new PrintWriter(new BufferedWriter(new FileWriter("Gift.txt", true)));					
					out.append("::" + txtTitle.getText() + "::\n" + txtBody.getText() + " {}" + "\n");
					out.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				
				} finally {
					
					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
				}

			}
		});
		
	}

}
