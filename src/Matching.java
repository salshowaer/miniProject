import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;


public class Matching extends JPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;


	public Matching() {
		
		setLayout(new MigLayout("", "[][269.00,grow][57.00,center][]", "[][173.00,grow][92.00,grow][]"));
		
		JLabel lblNewLabel = new JLabel("Quistion Titil (optional)");
		add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 0 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblQuistion = new JLabel("Quistion");
		add(lblQuistion, "cell 0 1,alignx right,aligny top");
		
		JTextArea textArea = new JTextArea();
		add(textArea, "cell 1 1 3 1,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 2 3 1,grow");
		panel.setLayout(new MigLayout("", "[][227.00,grow][15.00,grow][142.00,grow][]", "[][][][]"));
		
		JLabel lblA = new JLabel("A");
		panel.add(lblA, "cell 0 0,alignx trailing");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 0,growx");
		textField_1.setColumns(10);
		
		JLabel lblA_1 = new JLabel("A");
		panel.add(lblA_1, "cell 2 0,alignx center");
		
		textField_4 = new JTextField();
		panel.add(textField_4, "cell 3 0,growx");
		textField_4.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Delete");
		panel.add(chckbxNewCheckBox, "cell 4 0");
		
		JLabel lblB = new JLabel("B");
		panel.add(lblB, "cell 0 1,alignx trailing");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 1 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblB_1 = new JLabel("B");
		panel.add(lblB_1, "cell 2 1,alignx center");
		
		textField_5 = new JTextField();
		panel.add(textField_5, "cell 3 1,growx");
		textField_5.setColumns(10);
		
		JCheckBox chckbxDelete = new JCheckBox("Delete");
		panel.add(chckbxDelete, "cell 4 1");
		
		JLabel lblC = new JLabel("C");
		panel.add(lblC, "cell 0 2,alignx trailing");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 2,growx");
		textField_3.setColumns(10);
		
		JLabel lblC_1 = new JLabel("C");
		panel.add(lblC_1, "cell 2 2,alignx center");
		
		textField_6 = new JTextField();
		panel.add(textField_6, "cell 3 2,growx");
		textField_6.setColumns(10);
		
		JCheckBox chckbxDelete_1 = new JCheckBox("Delete");
		panel.add(chckbxDelete_1, "cell 4 2");
		
		JButton btnNewButton = new JButton("Add Matching");
		add(btnNewButton, "cell 2 3");
		
		JButton btnNewButton_1 = new JButton("Delete checked");
		add(btnNewButton_1, "cell 3 3");
	}

}
