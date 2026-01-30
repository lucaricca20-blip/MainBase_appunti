package com.betacom.objects;

public abstract class Car {  //Nessuno potrà creare un oggetto Car, è una classe astratta che però può essere ereditata con extends
	private String model;
	private String color;
	private Integer maxSpeed;
	
	public abstract void frena(); //metodo ASTRATTO: verrà implementato da chi eredita
	public abstract void accelera();
	
	
	
	
	
	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	

}
