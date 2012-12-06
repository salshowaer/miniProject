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

public class MissingWord extends JPanel {

	private JPanel panel, labelPanel, txtPanel;
	private JScrollPane scrText,scrPanel;
	private JTextField textField;;
	private JLabel label;

	private ArrayList<Row> rowList = new ArrayList<Row>();
	private String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			 			 "N", "O","P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	int i=0;

	
	public MissingWord() {

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
		
		JLabel lblAnswer = new JLabel("Answer");
		add(lblAnswer);
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		scrPanel = new JScrollPane(panel);
		add(scrPanel,"spanx, grow, wrap");
		
		rowList.add(new Row(letters[i]));
		
		panel.add(rowList.get(i),"growx, wrap");
	
		JButton saveAnswer = new JButton("Save & Add Question");
		add(saveAnswer);
		
		JButton addAnswer = new JButton("Add");
		add(addAnswer);
		
		JButton delAnswer = new JButton("Delete");
		add(delAnswer);
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				addAnswer(++i);
				scrPanel.updateUI();

			}
		});
		
		delAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(i>0)
				{
					delAnswer(i--);
					scrPanel.updateUI();
				}
				else
					JOptionPane.showMessageDialog(null, "You must have at least one Answer");				
			}
		});		
	}
	
	
	private void addAnswer(int i) {
		
		rowList.add(new Row(letters[i]));
		
		for (int x = 0; x <= i; x++) {
			
			panel.add(rowList.get(x),"growx, wrap");
		}	
	}	
	
	private void delAnswer(int i) {
		
		panel.remove(rowList.get(i));			

	}
		
	private class Row extends JPanel {
		
		private JTextField txt = new JTextField();
		
		public Row(String row){
			
			setLayout(new MigLayout("", "[][grow]","[]"));
			
			add(new JLabel(row));
			add(txt,"growx");		
				
		}

		public JTextField getTxt() {
			return txt;
		}

		public void setTxt(JTextField txt) {
			this.txt = txt;
		}
	}

}
