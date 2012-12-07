
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class TrueFalse extends JPanel {

	private JTextField txtTitle;
	private TextArea txtBody;
	private char answer = ' ';
	private static PrintWriter out;
	private ButtonGroup btnGroup;
	private JRadioButton rdbtnTrue, rdbtnFalse;
	static String TrueFalse_String;

	public TrueFalse() {
		
		
		setLayout(new MigLayout("", "[right][grow]", "[][grow][][]"));

		JLabel lblTitle = new JLabel("Question Title (optional)");
		add(lblTitle);
		
		txtTitle = new JTextField();
		add(txtTitle, "growx, wrap");
		
		JLabel lblQuestion = new JLabel("Question");
		add(lblQuestion, "alignx right,aligny top");
		
		txtBody = new TextArea();
		add(txtBody, "grow, wrap");

		rdbtnTrue = new JRadioButton("True");
		rdbtnFalse = new JRadioButton("False");
				
		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnTrue);
		btnGroup.add(rdbtnFalse);
				
		rdbtnTrue.addActionListener(new myAction());
		rdbtnFalse.addActionListener(new myAction());

		JPanel radioPanelGroup = new JPanel();

		radioPanelGroup.setLayout(new MigLayout(""));
		radioPanelGroup.setBorder(BorderFactory.createLineBorder(Color.black));
		TitledBorder title = BorderFactory.createTitledBorder("Answer");
		radioPanelGroup.setBorder(title);

		radioPanelGroup.add(rdbtnTrue);
		radioPanelGroup.add(rdbtnFalse);
		
		add(radioPanelGroup, "skip 1, alignx left, aligny top, wrap");
		
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer, "aligny bottom");

			
		saveAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if((txtTitle.getText().compareTo("")==0) | (txtBody.getText().compareTo("")==0) | (answer!='T' & answer!='F'))
					JOptionPane.showMessageDialog(null, "Complete the question before save it");
				
				else{
					
					TrueFalse_String = "::" + txtTitle.getText() + "::\n" + txtBody.getText() + " {" + answer + "}\n\n";
					StartingGUI.newSaveFile(TrueFalse_String);
															
					JOptionPane.showMessageDialog(null, "Question has been saved");
					txtTitle.setText("");
					txtBody.setText("");
					btnGroup.clearSelection();
					answer = ' ';

				}
			}
		});
	}

	public class myAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand() == "True") 
				answer = 'T';
			
			else
				answer = 'F';

		}

	}	

}
