import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

public class MathRange extends JPanel {

	
	private JPanel panel;
	private JScrollPane scrText, scrPanel;
	private JTextField textField,answer,startPoint,endPoint;
	
	public static PrintWriter out;

	

	public MathRange() {
		
		
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
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][][][grow][][grow][][grow][][grow]", "[]"));
		
		scrPanel = new JScrollPane(panel);
		add(scrPanel,"skip 1, spanx, grow, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		answer = new JTextField(5);
		startPoint = new JTextField(5);
		endPoint = new JTextField(5);
			
		panel.add(new JLabel("specified interval end points?"));
		panel.add(new JCheckBox());
		panel.add(new JLabel("Answer"));
		panel.add(answer);
		panel.add(new JLabel("Range +/-"));
		
		SpinnerModel spnModel = new SpinnerNumberModel(0, 0, 100, 5);
		JSpinner spn = new JSpinner(spnModel);
		
		panel.add(spn);
		panel.add(new JLabel("Start Point"));
		panel.add(startPoint);
		panel.add(new JLabel("Start Point"));
		panel.add(endPoint);
			
	}	
}	
	