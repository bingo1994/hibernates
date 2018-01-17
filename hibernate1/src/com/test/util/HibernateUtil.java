package com.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//原先MySessionFactory类的升级版
//这是一个工具类,可以返回全新的session
//可以可以返回和线程绑定session
public class HibernateUtil {

	private static SessionFactory sessionFactory=null;
	
	//有些程序员得到和线程绑定的session,不希望和hibernate.cfg.xml关联,
	//于是他使用 ThreadLocal (线程局部模式)
	private static ThreadLocal threadLocal=new ThreadLocal();
	
	static{
		try{
			sessionFactory=new Configuration().configure().buildSessionFactory();
		}catch(Exception e){
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}
	
	//返回全新session
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	//返回一个和线程绑定的session
	public static Session getThreadLocalSession(){
		//return sessionFactory.getCurrentSession();
		//先从 threadLocat取出对象
		Session session=(Session)threadLocal.get();
		//如果取不出来,就创建一个新的,
		//如果取的出来，则直接返回,
		if(session==null){
			session=sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	
	//关闭和线程绑定的session
	public static void closeThreadLocalSession(){
		Session session=(Session)threadLocal.get();
		if(session!=null&&session.isOpen()){
			session.close();
			threadLocal.set(null);
		}
	}
}
