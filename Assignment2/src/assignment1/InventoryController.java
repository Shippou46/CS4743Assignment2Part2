package assignment1;

public class InventoryController {
	private Inventory inv;
	
	public InventoryController(Inventory i) {
		inv = i;
	}
	
	public Part getPartByIndex(int index) {
		if(index < inv.getNumParts())
			return (Part) inv.getParts().get(index);
		else 
			return null;
	}
	
	public void deletePart(Part p) {
		//delete p from inv
		inv.deletePart(p);
	}

	public int getNumParts() {
		return inv.getNumParts();
	}
	
	public Part addPart(PartView view, Part p, String pNum, String pName, String v, int q) {
		//if p is null then create a new Part
		//but first validate pName does not already exist
		if(p == null) {
			try {
				return inv.addPart(p, pNum, pName, v, q);
			} catch(IllegalArgumentException e) {
				view.showError(e.getMessage());
			}
		} else {
			try {
				if(inv.partNameExists(pName, p)) {
					view.showError("Part Name already exists!");
					return null;
				}
				p.setFields(pNum, pName, v, q);
				inv.updateObservers();
				return p;
			} catch(IllegalArgumentException e) {
				view.showError(e.getMessage());
			}
		}
		return null;
	}
}
