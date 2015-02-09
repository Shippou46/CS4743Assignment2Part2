package assignment1;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Part> parts;
	private ArrayList<InventoryObserver> observers;
	
	public Inventory() {
		parts = new ArrayList<Part>();
		observers = new ArrayList<InventoryObserver>();
	}
	
	public void deletePart(Part p) {
		parts.remove(p);
		updateObservers();
		p.updateDeleted();//signal observing views that part has been deleted
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
	
	public int getNumParts() {
		return parts.size();
	}
	
	public boolean partNameExists(String pName, Part part) {
		for(Part p : parts) {
			if(pName.equalsIgnoreCase(p.getPartName()) && (p != part || part == null))
				return true;
		}
		return false;
	}
	
	public Part addPart(Part part, String pNum, String pName, String v, int q, int id) throws IllegalArgumentException {
		if(partNameExists(pName, part))
			throw new IllegalArgumentException("Part Name already exists!");
		Part p = new Part(pNum, pName, v, q, id);
		parts.add(p);
		updateObservers();
		return p;
	}
	
	public void registerObserver(InventoryObserver o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(InventoryObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch(Exception e) {
				//ignore for now
			}
		}
	}
}
