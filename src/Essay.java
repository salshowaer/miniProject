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


public class Essay extends JPanel {
	
	public Essay() {

		setLayout(new MigLayout("", "[right][grow,fill]", "[][grow][]"));
		
		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		JTextField textField = new JTextField();
		add(textField, "growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrPane = new JScrollPane(textArea);
		add(scrPane, "grow, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
	}

}
