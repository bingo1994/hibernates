package com.test.view;

import java.util.List;

import com.test.domain.Course;
import com.test.util.HibernateUtil;

public class TestMain2 {

	public static void main(String[] args) {

		//调用修改/删除
			String hql="update Student set sage=sage+1 where sdept=?";
			String parameters[]={"计算机系"};
			try {
				HibernateUtil.executeUpdate(hql, parameters);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// TODO: handle exception
			}
	}

	/**
	 * 测试工具类提供的添加方法
	 */
	public static void testAdd() {
		Course c=new Course();
		c.setCname("汉语");
		c.setCcredit(4);
		HibernateUtil.addObj(c);
	}

	/**
	 * 测试工具类提供的分页查询的方法
	 */
	public static void testQueryByPage() {
		//使用工具分页
			String hql="select sname,saddress from Student order by sage";
			String parameters[]=null;
			List<Object[]> list= HibernateUtil.executeQueryByPage(hql, parameters, 2, 3) ;
			for(Object[] s: list){
				System.out.println(s[0].toString()+" "+s[1].toString());
			}
	}

	/**
	 * 测试工具类的查询方法
	 */
	public static void testQuery() {
		//这里我们使用增强的HibernateUtil来完成curd.
		String hql="select sname,saddress from Student where sdept=? and sage>?";
		String parameters[]={"计算机系","3"};
		List<Object[]> list= HibernateUtil.executeQuery(hql,parameters);
		for(Object[] s: list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}
	}

}
