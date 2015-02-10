package assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Part {
	private String partNumber;
	private String partName;
	private String vendor;
        private static int idNumber;
	private int quantity;

	static Random randomNumber = new Random();
	private static String qUnit = "Unknown";
	
	private ArrayList<PartObserver> observers;
	
	public Part(String pNum, String pName, int q, int id) {
		this(pNum, pName, "", q, id);
	}
	
	public Part(String pNum, String pName, String v, int q) {
		if(pNum == null || pNum.length() < 1)
			throw new IllegalArgumentException("Part # cannot be blank");
		if(pName == null || pName.length() < 1)
			throw new IllegalArgumentException("Part Name cannot be blank");
		if(q < 1)
			throw new IllegalArgumentException("Quantity cannot be < 1");
		partNumber = pNum;
		partName = pName;
		vendor = v;
		quantity = q;
		idNumber = 0;
		
		observers = new ArrayList<PartObserver>();
	}

	public static int getIDNumber(){
		idNumber = randomNumber.nextInt(1000000);
		return idNumber;
	}

	public void setIDNumber(int idNumber){
		this.idNumber = idNumber;
	
	public static String getQuantityUnit(){
		return qUnit;
	}

	public static void setQuantityUnit(String unit){
		qUnit = unit;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
		updateObservers();
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
		updateObservers();
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
		updateObservers();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateObservers();
	}
	
	public void registerObserver(PartObserver o) {
		observers.add(o);
	}
	
	public void setFields(String pNum, String pName, String v, int q, String unit) {
		setPartNumber(pNum);
		setPartName(pName);
		setVendor(v);
		setQuantity(q);
		setIDNumber(id);
		setQuantityUnit(unit);
		updateObservers();
	}
	
	public void updateDeleted() {
		for(PartObserver o : observers) {
			try {
				o.modelDeleted();
			} catch(Exception e) {
				//ignore
			}
		}
	}
	
	private void updateObservers() {
		for(PartObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch(Exception e) {
				//ignore
			}
		}
	}
}
