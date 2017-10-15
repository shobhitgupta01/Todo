package com.shobhit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Path("/app")
public class ApiController {

	/*
	 * Method for creating new  employees
	 */
	@POST
	@Path("/employee")
	@Produces(MediaType.TEXT_HTML)
	public InputStream newEmployee(@FormParam("name") String name,@FormParam("password") String password) throws IOException
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setPassword(password);
		session.persist(employee);
		transaction.commit();
		session.close();
		
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\index.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		
	}
	
	/*
	 * Method for making new Todo
	 */
	@POST
	@Path("/todo")
	@Produces(MediaType.TEXT_HTML)
	public InputStream newTodo(@FormParam("content") String content)
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Todo todo = new Todo();
		todo.setContent(content);
		todo.setStatus("incomplete");
		session.persist(todo);
		transaction.commit();
		session.close();
		
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\index.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}
	
	/*
	 * Method for returning list of Todos
	 */
	@GET
	@Path("/getTodo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Todo> getTodos()
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		List<Todo> list = session.createQuery("from Todo").list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("inside getTodo");
		
		if(list.size()>0)
		{
			return list;
		}
		else
		{
			Todo t = new Todo();
			t.setId(0);
			t.setStatus("no");
			t.setContent("No Todo");
			
			List<Todo> emptyList = new ArrayList<Todo>();
			emptyList.add(t);
			
			return emptyList;
		}
	
	}
	
	/*
	 * Method to update todo status
	 */
	
	@POST
	@Path("{id}/{newStatus}")
	public void changeStatus(@PathParam("id") int id, @PathParam("newStatus") String newStatus/*,@PathParam('sender') String sender*/)
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		
		String qString="update Todo set status = :status where id=:id";
		Query query = session.createQuery(qString);
		query.setParameter("status",newStatus);
		query.setParameter("id",id);
		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Status updated "+result);
		session.close();
		
				
	}
	
	/*
	 * Method for updating todo content
	 */
	@POST
	@Path("/updateTodo")
	public InputStream updateContent(@FormParam("content") String content,@FormParam("id") int id)
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		
		String qString="update Todo set content = :content where id=:id";
		Query query = session.createQuery(qString);
		query.setParameter("content",content);
		query.setParameter("id",id);
		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Content updated "+result);
		session.close();
		
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\index.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}
	
	/*
	 * Method to delete a todo
	 */
	@POST
	@Path("/deleteTodo/{deleteId}")
	@Produces(MediaType.TEXT_HTML)
	public InputStream deleteTodo(@PathParam("deleteId") int id)
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		
		String qString="delete from Todo where id=:id";
		Query query = session.createQuery(qString);
		query.setParameter("id",id);
		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Todo deleted "+result);
		session.close();
		
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\index.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}
	
	/*
	 * Get request for returning employee List
	 */
	@GET
	@Path("/empList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployee()
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		List<Employee> list = session.createQuery("from Employee").list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("inside getEmployee");
		
		return list;
	}
	
	
	/*
	 * Service to delete employee
	 */
	@GET
	@Path("/deleteEmp/{deleteId}")
	public void deleteEmp(@PathParam("deleteId") int id)
	{
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		
		String qString="delete from Employee where id=:id";
		Query query = session.createQuery(qString);
		query.setParameter("id",id);
		int result = query.executeUpdate();
		transaction.commit();
		System.out.println("Employee deleted "+result);
		session.close();
		
	}//end of deleteEmp
	
	/*
	 * Method to return index.html(admin page)
	 */
	@GET
	@Path("/returnAdmin")
	@Produces(MediaType.TEXT_HTML)
	public InputStream sendAdmin()
	{
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\index.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}
	
	/*
	 * Method to return employee.html(Employee Page)
	 */
	@GET
	@Path("/returnEmp")
	@Produces(MediaType.TEXT_HTML)
	public InputStream sendEmp()
	{
		try{
			File f = new File("C:\\Users\\USER\\workspace\\Todo\\WebContent\\employee.html");
			return new FileInputStream(f);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
	}
	
	/*
	 * Method for Admin Login
	 */
	@POST
	@Path("/adminLogin")
	public void adminLogin(@FormParam("name") String name, @FormParam("password") String password,@Context HttpServletResponse servletResponse)throws IOException
	{
		if(name.equals("admin") && password.equals("shobhit")){
			servletResponse.sendRedirect("http://localhost:8080/Todo/rest/app/returnAdmin");
		}
		else
		{
			servletResponse.sendRedirect("http://localhost:8080/Todo/homepage.html");
		}
	}
	
	/*
	 * Method for Employee Login
	 */
	@POST
	@Path("/empLogin")
	public void empLogin(@FormParam("name") String name, @FormParam("password") String password,@Context HttpServletResponse servletResponse)throws IOException
	{
		//getting list of employees
		Configuration cfg = new Configuration();
		cfg.configure("/resources/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		List<Employee> list = session.createQuery("from Employee").list();
		session.getTransaction().commit();
		session.close();
		
		//iterating
		for(Employee emp : list)
		{
			if(emp.getName().equals(name) && emp.getPassword().equals(password))
			{
				servletResponse.sendRedirect("http://localhost:8080/Todo/rest/app/returnEmp");
			}
		}
		
		servletResponse.sendRedirect("http://localhost:8080/Todo/homepage.html");
		
	}
}
