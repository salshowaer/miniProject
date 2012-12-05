import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class Missing_Word extends JPanel {

	private JPanel panel, labelPanel, txtPanel;
	private JScrollPane scrPane;
	private JTextField textField;;
	private JLabel label;

	private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	private ArrayList<JTextField> fieldList = new ArrayList<JTextField>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	int i=1;

	
	public Missing_Word() {

		setLayout(new MigLayout("", "[right][grow][]", "[]10[188.00,grow,top][][]"));
		
		JLabel lblNewLabel = new JLabel("Quistion Titil (optional) ");
		add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblQuistion = new JLabel("Quistion");
		add(lblQuistion, "cell 0 1,alignx right");
		
		JTextArea textArea = new JTextArea();
		add(textArea, "cell 1 1 2 1,grow");
		
		JLabel lblAnswer = new JLabel("Answer");
		add(lblAnswer, "cell 0 2");
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[right][grow]", "[]"));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		scrPane = new JScrollPane(panel);
		add(scrPane,"cell 1 2 2 1,grow,wrap");
		
		labelPanel = new JPanel(new GridLayout(i,1,0,5));
		txtPanel = new JPanel(new GridLayout(i,1,0,5));

		
		labelList.add(new JLabel(letters[i-1]));
		fieldList.add(new JTextField());
		
		setAddAnswer(i);
		
		
		JButton addAnswer = new JButton("Add Answare");
		add(addAnswer, "cell 1 3,alignx right");
		
		JButton delAnswer = new JButton("Delete");
		add(delAnswer, "cell 2 3");
		
		panel.add(labelPanel);
		panel.add(txtPanel,"grow, wrap");
		setVisible(true);
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				i++;
				labelList.add(new JLabel(letters[i-1]));
				fieldList.add(new JTextField());
				setAddAnswer(i);				
				scrPane.revalidate();

			}
		});
		
		delAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(i>1)
				{
					i--;
					setDelAnswer(i);
					scrPane.revalidate();
				}
				else
					JOptionPane.showMessageDialog(null, "You must have at least one answer");
				
			}
		});

		
	}
	
	
	private void setAddAnswer(int i) {
		
		labelPanel.setLayout((new GridLayout(i,1,0,5)));
		txtPanel.setLayout((new GridLayout(i,1,0,5)));
				
		for (int x = 0; x < i; x++) {
			
			labelPanel.add(labelList.get(x));
			txtPanel.add(fieldList.get(x));
			
		}
	}	
	
	private void setDelAnswer(int i) {
		
		labelPanel.setLayout((new GridLayout(i,1,0,5)));
		txtPanel.setLayout((new GridLayout(i,1,0,5)));
							
		labelPanel.remove(labelList.get(i));
		txtPanel.remove(fieldList.get(i));			

	}	

}
