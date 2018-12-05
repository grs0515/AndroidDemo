package com.grs.demo.java8;

interface Vehicle {

	static void blowHorn() {
		System.out.println("按喇叭!!!");
	}

	default void print() {
		System.out.println("我是一辆车!");
	}
}

interface FourWheeler {
	default void print() {
		System.out.println("我是一辆四轮车!");
	}
}


class CarTest implements Vehicle, FourWheeler {

	@Override
	public void print() {
		Vehicle.super.print();
		FourWheeler.super.print();
		Vehicle.blowHorn();
		System.out.println("我是一辆汽车!");
	}
}

public class Java8Def {
	public static void main(String args[]) {
		Vehicle vehicle = new CarTest();
		vehicle.print();
		//我是一辆车!
		//我是一辆四轮车!
		//按喇叭!!!
		//我是一辆汽车!
	}
}