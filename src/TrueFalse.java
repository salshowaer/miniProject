import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class TrueFalse extends JPanel {

	private JTextField txtTitle;
	private TextArea txtBody;
	private char answer = ' ';
	private ButtonGroup btnGroup;
	private JRadioButton rdbtnTrue, rdbtnFalse;
	private String content;

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
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtTitle.setText("");
				txtBody.setText("");
					
				txtTitle.setText("");
				txtBody.setText("");
				btnGroup.clearSelection();
				answer = ' ';
				
			}
		});
		add(btnClear, "cell 0 2,growx");
		
		add(radioPanelGroup, "alignx center, aligny top, wrap");
		
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer, "aligny bottom");

			
		saveAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if((txtTitle.getText().compareTo("")==0) | (txtBody.getText().compareTo("")==0) | (answer!='T' & answer!='F'))
					JOptionPane.showMessageDialog(null, "Complete the question before save it");
				
				else{
					
					content = "::" + txtTitle.getText() + "::\n" + txtBody.getText() + " {" + answer + "}\n\n";
					
					StartingGUI.newSaveFile(content);
															
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
