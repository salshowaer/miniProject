import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class missing1 extends JPanel {

	private JPanel panel, panel_1, panel_2, panel_3;
	JScrollPane scrPane;

	private ArrayList<Row> answer = new ArrayList<Row>();
	private Row rows;
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	int i=1;

	
	public missing1() {

		setLayout(new MigLayout("", "[right][grow][]", "[]10[188.00,grow,top][][]"));
		
		JLabel lblNewLabel = new JLabel("Quistion Titil (optional) ");
		add(lblNewLabel, "cell 0 0,alignx trailing");
		
		JLabel lblQuistion = new JLabel("Quistion");
		add(lblQuistion, "cell 0 1,alignx right");
		
		JTextArea textArea = new JTextArea();
		add(textArea, "cell 1 1 2 1,grow");
		
		JLabel lblAnswer = new JLabel("Answer");
		add(lblAnswer, "cell 0 2");
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[Fill][grow][right]", "[grow,center]"));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		scrPane = new JScrollPane(panel);
		add(scrPane, "cell 1 2 2 1,grow");

		panel_1 = new JPanel();		panel_1.setLayout(new GridLayout(i, 0, 0, 5));
		panel_2 = new JPanel();		panel_2.setLayout(new GridLayout(i, 0, 0, 0));
		panel_3 = new JPanel();		panel_3.setLayout(new GridLayout(i, 0, 0, 0));
		
		JTextField text = new JTextField();
		
		
		rows = new Row(new JLabel(letters[i-1]), new JTextField(),  new JCheckBox("Delete"));
		
		answer.add(rows);
				
		setAnswerPanel(i);
		
		
		panel.add(panel_1);		panel.add(panel_2, "grow");		panel.add(panel_3);
		
		JButton addAnswer = new JButton("Add Answare");
		add(addAnswer, "cell 1 3,alignx right");
		
		JButton btnNewButton_1 = new JButton("Delete");
		add(btnNewButton_1, "cell 2 3");
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				i++;
				answer.add(new Row(new JLabel(letters[i-1]), new JTextField(),  new JCheckBox("Delete")));
				setAnswerPanel(i);				
				scrPane.revalidate();
			}
		});
		
		
		
		setVisible(true);
	}
	
	private void setAnswerPanel(int i) {
		
		panel_1.setLayout(new GridLayout(i, 0, 5, 5));
		panel_2.setLayout(new GridLayout(i, 0, 0, 0));
		panel_3.setLayout(new GridLayout(i, 0, 0, 0));
		
		
		for (int x = 0; x < i; x++) {
			
			panel_1.add(answer.get(x).getLabel());
			panel_2.add(answer.get(x).getTextField());
			panel_3.add(answer.get(x).getChkDelete());
			
		}		
	}

}
