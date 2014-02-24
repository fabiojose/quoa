package net.sourceforge.quoa.example.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public final class Unit extends Abstract {
	private static final long serialVersionUID = -8506952689820013110L;
	
	@ManyToOne(optional = false)
	private Artifact artifact;

	@AttributeOverride(name = "code", column = @Column(name = "SYMBOL_CODE"))
	private Couple<String, String> symbol;
	
	@AttributeOverride(name = "code", column = @Column(name = "QUANTITY_CODE"))
	private Couple<String, String> quantity;
	
	private double value;
	private double factor;
	
	public Artifact getArtifact() {
		return artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	public Couple<String, String> getSymbol() {
		if(null== symbol){
			symbol = new Couple<String, String>();
		}
		return symbol;
	}

	public void setSymbol(Couple<String, String> symbol) {
		this.symbol = symbol;
	}

	public Couple<String, String> getQuantity() {
		if(null== quantity){
			quantity = new Couple<String, String>();
		}
		return quantity;
	}

	public void setQuantity(Couple<String, String> quantity) {
		this.quantity = quantity;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public static void main(String...args){
		final Unit _metre = new Unit();
		_metre.getSymbol().setCode("m");
		_metre.getSymbol().setContent("metre");
		_metre.getQuantity().setCode("l");
		_metre.getQuantity().setContent("lenght");
		_metre.setValue(20);
		_metre.setFactor(1E0);
		
		System.out.println("1E3 = " + 1E3);
		System.out.println("1E2 = " + 1E2);
		System.out.println("1E1 = " + 1E1);
		
		System.out.println("1E0 = " + 1E0);
		
		System.out.println("1E-1 = " + 1E-1);
		System.out.println("1E-2 = " + 1E-2);
		System.out.println("1E-3 = " + 1E-3);
		
	}
}

