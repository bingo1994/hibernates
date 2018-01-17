package com.test.view;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.test.domain.Employee;
import com.test.util.HibernateUtil;
import com.test.util.MySessionFactory;

public class TestMain  {

	public static void main(String[] args) {
		queryList();
	}

	/**
	 * 测试用query查询
	 */
	public static void queryList() {
		//使用Query 接口(PreparedStatement)，完成hql(sql)语句查询
		//hql入门案例
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getThreadLocalSession();
			transaction=session.beginTransaction();
			//这里大家一定要注意, hql语句是针对 类，面向对象的查询语句
			Query query=session.createQuery("from Employee where name='李雪'");// PreparedStatement ps
			//查询结果直接封装，不像jdbc查询的结果要进行二次封装
			List<Employee>list=query.list();// ResultSet rs=ps.executeQuery();rs->hibernte->list
			for(Employee emp:list){
				System.out.println(emp.getName()+"------"+emp.getId());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			if(transaction!=null){
				transaction.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}

	/**
	 * 改进后的代码，可事务回滚
	 * 较全可以当做一个模板
	 */
	public static void getValue() {
		Session session=MySessionFactory.getSessionFactory().openSession();
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			Employee emp=(Employee)session.load(Employee.class, 3);
			emp.setName("刘备");
			// 异常int i=9/0;
			transaction.commit();
		}catch(Exception e){
			//回滚
			if(transaction!=null){
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭session
					if(session!=null&&session.isOpen()){
						session.clear();
					}
		}
	}

	//根据id查询
	public static void getEmpById() {
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		//查询可以不使用事务
		Employee emp=(Employee)session.load(Employee.class, 3);
		System.out.println(emp.getId()+","+emp.getName());
		session.close();
	}

	//删除
	public static void delEmp() {
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//删除一个雇员,先得到，再删除
		Employee emp=(Employee)session.load(Employee.class, 1);//select employee0_.id as id0_0_, employee0_.name as name0_0_, employee0_.sex as sex0_0_, employee0_.phone as phone0_0_ from employee employee0_ where employee0_.id=?
		session.delete(emp);//delete from employee where id=?
		transaction.commit();
	}

	//修改
	public static void updateEmp() {
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//修改一个雇员,先得到，再修改
		//load方法是用于获取 指定 主键的对象（记录.）
		Employee emp=(Employee)session.load(Employee.class, 1);
		emp.setName("小雪");//update employee set name=?, sex=?, phone=? where id=?
		transaction.commit();
	}

	//添加
	public static void addEmp() {
		//1.得到Configuration
		//2.得到SessionFactory(会话工厂，这是一个重量级的类，因此要保证在一个应用程序中只能有一个)
		SessionFactory sessionFactiry=MySessionFactory.getSessionFactory();
		//3. 从SessionFactory中取出一个Session对象(它表示 和数据库的出一次会话)
		Session session=sessionFactiry.openSession();
		//4. 开始一个事务
		Transaction transaction=session.beginTransaction();
		//保存一个对象到数据库(持久化一个对象)
		//不要给id,因为它是自增的
		Employee emp=new Employee();
		emp.setName("李雪");
		emp.setSex("女");
		emp.setPhone("543342234");
		session.save(emp);//insert into employee (name, sex, phone, id) values (?, ?, ?, ?)
		//事务提交
		transaction.commit();
	}

}
