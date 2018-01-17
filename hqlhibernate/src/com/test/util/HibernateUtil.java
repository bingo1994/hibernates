package com.test.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUtil {
	
	
	/**
	 * 统一的一个修改和删除(批量 hql) hql"delete upate ...??"
	 * @param hql
	 * @param parameters
	 */
	public static void executeUpdate(String hql,String[] parameters){

		Session session=null;
		Transaction transaction=null;
		try {
			session=MySessionFactory.getSession();
			transaction=session.beginTransaction();
			Query query=session.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&& parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	
	}
	
	/**
	 * 提供统一添加的方法
	 * @param obj
	 */
	public static void addObj(Object obj){
		Session session=null;
		Transaction transaction=null;
		try {
			session=MySessionFactory.getSession();
			transaction=session.beginTransaction();
			session.save(obj);
			transaction.commit();
		} catch (Exception e) {
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}

	/**
	 * 提供一个统一查询方法 hql 形式 from 类  where 条件=? ..
	 * @param hql  
	 * @param parameters  条件参数值
	 * @return 查询结果
	 */
	public static List executeQuery(String hql,String[] parameters){
		Session session=null;
		List list=null;
		try {
			session=MySessionFactory.getSession();
			Query query=session.createQuery(hql);
			if(parameters!=null&&parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	
	/**
	 * 提供一个统一的查询方法(带分页) hql 形式 from 类  where 条件=? ..
	 * @param hql
	 * @param parameters
	 * @param pageNumber 当前页
	 * @param pageSize  每页显示的数据条数
	 * @return
	 */
	public static List executeQueryByPage(String hql,String[] parameters,int pageNumber,int pageSize){

		Session session=null;
		List list=null;
		try {
			session=MySessionFactory.getSession();
			Query query=session.createQuery(hql);
			//先判断是否有参数要绑定
			if(parameters!=null&&parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					query.setString(i, parameters[i]);
				}
			}
			query.setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize);
			list=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return list;
	
	}
}
