
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
	private char answer;
	private static PrintWriter out;

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

		JRadioButton rdbtnTrue = new JRadioButton("True");
		JRadioButton rdbtnFalse = new JRadioButton("False");
		
		ButtonGroup btnGroup = new ButtonGroup();
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

				try {
					
					out = new PrintWriter(new BufferedWriter(new FileWriter("Gift.txt", true)));					
					out.append("::" + txtTitle.getText() + "::\n" + txtBody.getText() + " {" + answer + "}\n");
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

	public class myAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand() == "True") 
				answer = 'T';
			
			else
				answer = 'F';

		}

	}

}
