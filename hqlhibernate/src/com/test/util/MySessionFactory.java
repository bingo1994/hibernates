package com.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

final public class MySessionFactory {

	private static SessionFactory sessionFactory=null;
	private static ThreadLocal threadLocal=new ThreadLocal();
	
	static{
		try{
			sessionFactory=new Configuration().configure().buildSessionFactory();
		}catch(Exception e){
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}
	
	/**
	 * 获取新的session
	 * @return
	 */
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static Session getThreadLocalSession(){
		Session session=(Session)threadLocal.get();
		if(session==null){
			session=sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void closeThreadLocalSession(){
		Session session=(Session)threadLocal.get();
		if(session!=null&&session.isOpen()){
			session.close();
			threadLocal.set(null);
		}
	}
}
