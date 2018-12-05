package com.grs.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author:gaoruishan
 * @date:2018/12/5/13:40
 * @email:grs0515@163.com
 */
public class Predicater {

	public static void main(String args[]) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

		// Predicate<Integer> predicate = n -> true
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// n 如果存在则 test 方法返回 true

		System.out.println("输出所有数据:");

		// 传递参数 n
		eval(list, n -> true);
		eval(list, new java.util.function.Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return false;
			}
		});

		// Predicate<Integer> predicate1 = n -> n%2 == 0
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n%2 为 0 test 方法返回 true

		System.out.println("输出所有偶数:");
		eval(list, n -> n % 2 == 0);

		// Predicate<Integer> predicate2 = n -> n > 3
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n 大于 3 test 方法返回 true

		System.out.println("输出大于 3 的所有数字:");
		eval(list, n -> n > 3);
	}

	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.println(n + " ");
			}
		}
	}

	/**
	 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
	 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
	 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
	 */
	private static void eval2(List<Integer> list, Predicate<Integer> predicate) {
		list.stream().filter(predicate).forEach(System.out::println);
	}
}
