package assignment1;

import java.util.UUID;

import javax.swing.JFrame;

public class InventoryMain {

	public static void main(String[] args) {
		//model
		Inventory inv = new Inventory();
		
		//populate with a few test Parts
		inv.addPart(null, "01011", "1/2\" Bolt", "Sears", 100, 1);
		inv.addPart(null, "X3599", "1\" Laminate Edging (Hot Pink)", "Plastics R Us", 50, 2);
		inv.addPart(null, "02949", "1/4\" Plywood 10x10", "", 10, 3);

		//controller
		InventoryController invC = new InventoryController(inv);
		
		//views
		PartsListView pView = new PartsListView(invC, inv);
		pView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pView.setSize(400, 300);
		pView.setVisible(true);
		
		inv.registerObserver(pView);
	}

}
