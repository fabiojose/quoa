package net.sourceforge.quoa.example.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vehicle extends Opaque {
	private static final long serialVersionUID = 1L;

	private float horsePower;
	private int seat;
	private int door;
	private int wheel;
	
	@AttributeOverride(name = "code", column = @Column(name = "FUEL_CODE"))
	private Couple<String, String> fuel;
	
	public float getHorsePower() {
		return horsePower;
	}
	public void setHorsePower(float horsePower) {
		this.horsePower = horsePower;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getDoor() {
		return door;
	}
	public void setDoor(int door) {
		this.door = door;
	}
	public int getWheel() {
		return wheel;
	}
	public void setWheel(int wheel) {
		this.wheel = wheel;
	}
	public Couple<String, String> getFuel() {
		if(null== fuel){
			fuel = new Couple<String, String>();
		}
		return fuel;
	}
	public void setFuel(Couple<String, String> fuel) {
		this.fuel = fuel;
	}
	
}
