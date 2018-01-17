package com.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

final public class MySessionFactory {

	private static SessionFactory sessionFactory=null;
	private static Configuration configuration=null;
	static {
		//configure();默认读取hibernate.cfg.xml文件或属性文件，如果改名称则configure("..xml");
		configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
	}
	
	/**
	 * 获取SessionFactory
	 * @return
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
