package com.iwc.test;

public class TestClass {

	public static void main(String[] args) {

		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("1");
		}

		try {
			System.out.println("2");
		} catch (Exception e) {
			System.out.println("3");
		}

		System.out.println("4");

	}

}
