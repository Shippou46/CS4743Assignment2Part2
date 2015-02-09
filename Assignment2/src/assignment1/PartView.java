package assignment1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PartView extends JFrame implements PartObserver {
	private JTextField tfPartNum;
	private JTextField tfPartName;
	private JTextField tfVendor;
	private JTextField tfQty;
	
	private Part part;
	private InventoryController invC;
	
	public PartView(InventoryController i, Part p) {
		part = p;
		invC = i;
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		panel.add(new JLabel("Part #"));
		tfPartNum = new JTextField();
		panel.add(tfPartNum);
		
		panel.add(new JLabel("Part Name"));
		tfPartName = new JTextField();
		panel.add(tfPartName);

		panel.add(new JLabel("Vendor"));
		tfVendor = new JTextField();
		panel.add(tfVendor);

		panel.add(new JLabel("Quantity"));
		tfQty = new JTextField();
		panel.add(tfQty);

		this.add(panel, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(tfQty.getText().trim());
				} catch(Exception err) {
					PartView.this.showError("Invalid Quantity!");
					return;
				}
				Part p = invC.addPart(PartView.this, part, tfPartNum.getText(), tfPartName.getText(), tfVendor.getText(), Integer.parseInt(tfQty.getText().trim()));
				if(p != null) {
					if(part == null) {
						part = p;
						part.registerObserver(PartView.this);
						PartView.this.setTitle("Editing " + part.getPartName());
					} else
						part = p;
				}
			}
		});
		panel.add(button);
		
		this.add(panel, BorderLayout.SOUTH);

		//set for edit mode
		if(p != null) {
			tfPartNum.setText(part.getPartNumber());
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfQty.setText(Integer.toString(part.getQuantity()));
			this.setTitle("Editing " + p.getPartName());
		} else
			this.setTitle("Adding new part");
	}
	
	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void updateObserver(Part part) {
		if(part != null) {
			tfPartNum.setText(part.getPartNumber());
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfQty.setText(Integer.toString(part.getQuantity()));
			this.setTitle("Editing " + part.getPartName());
		}
	}

	@Override
	public void modelDeleted() {
		this.setVisible(false);
		
	}

}
