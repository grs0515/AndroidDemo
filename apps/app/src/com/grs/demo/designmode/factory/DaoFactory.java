package com.grs.demo.designmode.factory;

/**
 * 单例工厂
 * Created by gaoruishan on 16/4/4.
 */
public class DaoFactory {


	private static DaoFactory mInstance;

	//不能实例话
	private DaoFactory() {
//		throw new IllegalArgumentException("can't get a instance");
	}

	public static DaoFactory getInstance() {
		if (mInstance == null) {//同步，防止多个线程同时
			synchronized (DaoFactory.class) {
				if (mInstance == null) {
					mInstance = new DaoFactory();
				}
			}
		}
		return mInstance;
	}

	public IDao getIDaoWithType(Class<?> cls) {
		if (cls.equals(User.class)) {
			return new DaoImpUser();
		} else if (cls.equals(Video.class)) {
			return new DaoImpVideo();
		}

		return null;
	}

}
