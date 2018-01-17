package com.test.view;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.domain.Student;
import com.test.util.MySessionFactory;

public class TestMain {

	public static void main(String[] args) {

		queryTest();
	}

	/**
	 * hql练习
	 */
	public static void queryTest() {
		Session session=null;
		Transaction transaction=null;
		try{
			session=MySessionFactory.getThreadLocalSession();
			transaction=session.beginTransaction();
			//显示年龄在20~22之间的学生
			//List<Student>list=session.createQuery("from Student where sage between 20 and 22").list();

			//查询计算机系和外语系的学生信息
			//List<Student>list =session.createQuery("from Student where sdept in('计算机系','外语系')").list();
			/*for(Student s:list){
				System.out.println(s.getSid()+"  "+s.getSname()+" "+s.getSage()+" "+s.getSdept());
			}*/
			
			//显示各个系的学生的平均年龄
			//List list=session.createQuery("select avg(sage),sdept from Student group by sdept").list();
			/*for(int i=0;i<list.size();i++){
				Object[] objs=(Object[]) list.get(i);
				System.out.println(objs[0]+"  "+objs[1]);
			}*/
			
			//having的使用
			//1.对分组查询后的结果，进行筛选:比如请显示人数大于3的系名称
			//a. 查询各个系分别有多少学生.
			List list=session.createQuery("select count(*),sdept from Student group by sdept having count(*)>=2").list();
			for(int i=0;i<list.size();i++){
				Object[] objs=(Object[]) list.get(i);
				System.out.println(objs[0]+"  "+objs[1]);
			}
			
			//2查询女生少于200人的系
			//a.查询各个系的女生有多个个
		/*	List<Object[]> list=session.
			createQuery("select count(*) as c1,sdept from  Student where ssex='F' group by sdept").list();
			//取出1. for 增强
			for(Object[] obj:list){
				System.out.println(obj[0].toString()+" "+obj[1].toString());
			}*/
			
			//1.查询计算机系共多少人?->如果我们返回的是一列数据
				//这时我们的取法是直接取出list->object 而不是 list->Object[]
		/*		List<Object[]> list=session.
				createQuery("select sage from  Student where sdept='计算机系'").list();
				//取出1. for 增强
				for(Object obj:list){
					System.out.println(obj.toString());
				}*/
			//2.查询总学分是多少?
	/*			List<Object[]> list=session.
				createQuery("select sum(grade) from Studcourse").list();
				//取出1. for 增强
				for(Object obj:list){
					System.out.println(obj.toString());
				}*/
			//3.查询选修11号课程的最高分和最低分.
	/*			List<Object[]> list=session.
				createQuery("select 11,max(grade),min(grade) from Studcourse where course.cid=11").list();
				//取出1. for 增强
				for(Object[] obj:list){
					System.out.println(obj[0].toString()+" max="+obj[1].toString()+" min="+obj[2].toString());
				}*/
			//4.显示各科考试不及格学生的名字(Student-student)，科目(Course-course)和分数(Studcourse-studcourse)
				
			/*	List<Object[]> list=session.
				createQuery("select student.sname,course.cname,grade from Studcourse where grade>=60").list();
				//取出1. for 增强
				for(Object[] obj:list){
					System.out.println(obj[0].toString()+" "+obj[1].toString()+" "+obj[2].toString());
				}*/
				
				//计算各个科目不及格的学生数量.(学生练习!)
				
				/*List<Object[]> list=session.
				createQuery("select count(*),student.sdept from Studcourse where grade<60 group by student.sdept").list();
				//取出1. for 增强
				for(Object[] obj:list){
					System.out.println(obj[0].toString()+" "+obj[1].toString());
				}*/
				
				//请按照学生的年龄从小->大，取出第3到第5个学生
			/*	List<Student> list=session.createQuery
				("from Student  order by sage").setFirstResult(2)
				.setMaxResults(3).list();
				for(Student s: list){
					System.out.println(s.getSname()+" "+s.getSage());
				}*/
			
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
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
	 * 分页查询所有
	 */
	public static void queryByFenYe() {
		int pageNumber=1;//当前页
		int pageSize=2;//一页显示的数据条数
		int pageCount=0;//总页数
		Session session=null;
		Transaction transaction=null;
		try{
			session=MySessionFactory.getThreadLocalSession();
			transaction=session.beginTransaction();
			//明确知道最多只有一个对象可以使用uniqueResult()，一般查询得到的都是对象
			int rowCount=Integer.parseInt(session.createQuery("select count(*) from Student").uniqueResult().toString());
			
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=(rowCount-1)/pageSize+1;
			}
			
			for(int i=1;i<=pageCount;i++){
				System.out.println("**********第"+i+"页**********");
				List<Student> list=session.createQuery("from Student").setFirstResult((i-1)*pageSize).setMaxResults(pageSize).list();
				for(Student s:list){
					System.out.println(s.getSid()+"  "+s.getSname());
				}
			}
			
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
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
	 *查询部分属性 
	 */
	public static void query1() {
		Session session=null;
		Transaction transaction=null;
		try{
			session=MySessionFactory.getThreadLocalSession();
			transaction=session.beginTransaction();
			
			//检索的学生名字和所在系
			//原则: 在讲解jdbc我们曾说过， 要查询什么字段就查询什么字段，不要select * from 表
			//但是在hibernate ,我们其实可以不遵循这个规则,建议我们把整个对象的属性都查询
			//这里我们还是要讲解如何取出部分属性
			//只查询部分属性，不会封装成Student对象，得到的list集合中封装的是object数组对象
			//如果只查询一个属性，直接用object对象
			
			/*List list=session.createQuery("select sname from Student").list();
			for(int i=0;i<list.size();i++){
				Object objs=(Object) list.get(i);
				System.out.println(objs);
			}*/
			
			List list=session.createQuery("select sname,sdept from Student").list();
			for(int i=0;i<list.size();i++){
				Object[] objs=(Object[]) list.get(i);
				System.out.println(objs[0]+"  "+objs[1]);
			}
			System.out.println("****************");

			//使用iterator
			Iterator it=list.iterator();
			while(it.hasNext()){
				Object [] objs=(Object[]) it.next();
				System.out.println(objs[0].toString()+" "+objs[1].toString());
			}
			//事务提交
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			
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
	 * 学习hql的使用
	 * 查询所有属性
	 */
	public static void queryList() {
		Session session=null;
		Transaction transaction=null;
		try{
			//session=MySessionFactory.getSession();
			session=MySessionFactory.getThreadLocalSession();
			transaction=session.beginTransaction();
			Query query=session.createQuery("from Student");
			List<Student>list=query.list();
			
			//for循环
			for(Student s:list){
				System.out.println(s.getSid()+"  "+s.getSname());
			}
			System.out.println("************************************");
			
			//迭代器循环
			Iterator iterator=list.iterator();
			while(iterator.hasNext()){
				Student s=(Student) iterator.next();
				System.out.println(s.getSname());
			}
			
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			
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

}
