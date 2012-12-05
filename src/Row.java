import javax.swing.*;


public class Row {
	
	private JLabel lab;
	private JTextField txt;
	private JCheckBox chk;
	
	public Row(){
			
	}

	public Row(JLabel lab, JTextField txt, JCheckBox chk) {
		super();
		this.lab = lab;
		this.txt = txt;
		this.chk = chk;
		
	}

	public JLabel getLabel() {
		return lab;
	}

	public void setLabel(JLabel label) {
		this.lab = label;
	}
	
	public JTextField getTextField() {
		return txt;
	}

	public void setTextField(JTextField textField) {
		this.txt = textField;
	}

	public JCheckBox getChkDelete() {
		return chk;
	}

	public void setChkDelete(JCheckBox chkDelete) {
		this.chk = chkDelete;
	}

}
