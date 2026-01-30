package com.betacom.objects;

import interfaces.SampleInterface1;

public class ClassInterface1 implements SampleInterface1 {

	@Override
	public void test1(String t1) {
		System.out.println("execute test 1 da ClasseInterface1:" + t1);
		
	}

	@Override
	public void test2(String t2) {
		System.out.println("execute test 2 da ClasseInterface1:" + t2);
		
	}

}
