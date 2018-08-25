package de.homedev.springboot.jpa.config;

public class MyClass {
	private String calledFrom;

	public MyClass() {
		super();
	}

	public MyClass(String calledFrom) {
		super();
		this.calledFrom = calledFrom;
	}

	public String getCalledFrom() {
		return calledFrom;
	}

	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}

}
