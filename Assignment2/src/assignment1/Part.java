package assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Part {
	private String partNumber;
	private String partName;
	private String vendor;
        private static int idNumber;
	private String exPartNum;
	private int quantity;
	private String location = "Unknown";

	static Random randomNumber = new Random();
	private static String qUnit = "Unknown";
	
	private ArrayList<PartObserver> observers;
	
	
	public Part(String pNum, String pName, int q, int id, String loc) {
		this(pNum, pName, "", q, id, loc);
	}
	
	public Part(String pNum, String pName, String v, int q, String loc) {
		if(pNum == null || pNum.length() < 1)
			throw new IllegalArgumentException("Part # cannot be blank");
		if(pName == null || pName.length() < 1)
			throw new IllegalArgumentException("Part Name cannot be blank");
		if(q < 1)
			throw new IllegalArgumentException("Quantity cannot be < 1");
		if(ex == null || ex.lenth() > 50){
			throw new IllegalArgumentException("External Part Number too long");
		partNumber = pNum;
		partName = pName;
		vendor = v;
		quantity = q;
		location = loc;
		idNumber = 0;
		exPartNum = ex;
		
		observers = new ArrayList<PartObserver>();
	}

	public String getExPartNumber(){
		return exPartNum;
	}

	public void setExPartNumber(String exPartNum){
		this.exPartNum = exPartNum;
	public String getLocation(){
		return location;
	}

	public void setLocation(String location){
		this.location = location;
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
	
	public void setFields(String pNum, String pName, String v, int q, String unit, String loc) {
		setPartNumber(pNum);
		setPartName(pName);
		setVendor(v);
		setQuantity(q);
		setIDNumber(id);
		setQuantityUnit(unit);
		setLocation(loc);
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
