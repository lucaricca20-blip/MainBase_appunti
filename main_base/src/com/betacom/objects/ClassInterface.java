package com.betacom.objects;

import interfaces.SampleInterface;
import interfaces.SampleInterface1;

public class ClassInterface implements SampleInterface, SampleInterface1 {

	@Override
	public void interface1(String p) {
		System.out.println("implementation interface 1 p da ClassInterface: " +p);
		
	}

	@Override
	public void interface2(String p) {
		System.out.println("implementation interface 2 p da ClassInterface: " +p);
		
	}

	@Override
	public void test1(String t1) {
		// TODO Auto-generated method stub
		System.out.println("implementation test 1 p da ClassInterface: " +t1);		
	}

	@Override
	public void test2(String t2) {
		System.out.println("implementation test 2 p da ClassInterface: " +t2);
		
	}

}
