package com.mmit.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.controller.service.CategoryService;
import com.mmit.model.entity.Category;

@WebServlet(urlPatterns = {"/categories","/category-add","/category-edit","/category-delete"},loadOnStartup = 1)
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CategoryService catService;
	 @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		EntityManagerFactory EMF = null;
		
		Object obj = getServletContext().getAttribute("emf");//application scope
		if (obj==null) {
			EMF = Persistence.createEntityManagerFactory("jsp-jpa-crud");
			getServletContext().setAttribute("emf", EMF);
		}
		else {
			EMF = (EntityManagerFactory) obj;
		}
		
		catService = new CategoryService(EMF.createEntityManager());
	}
	 
	 @Override
	public void destroy() {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf!=null && emf.isOpen()) {
			emf.close();
		}
	}
	
	 //get All Categories Data from Database
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		if("/categories".equals(action)) {
			//get data from db
			List<Category> list = catService.findAll(); // findAll() is own method
			//set data to req
			req.setAttribute("categories", list);
			//forward page
			getServletContext().getRequestDispatcher("/category.jsp").forward(req, resp);
		}
		else if ("/category-edit".equals(action)) {
			//get id from req
			String catId = req.getParameter("categoryId");
			// get data from db
			Category cat = catService.findById(Integer.parseInt(catId));
			//set data to req
			req.setAttribute("category", cat);
			//forward page
			getServletContext().getRequestDispatcher("/category-add.jsp").forward(req, resp);
		}
		
		else if("/category-delete".equals(action)) {
			//get id from req
			String catId = req.getParameter("categoryid");
			//remove data from db
			catService.delete(Integer.parseInt(catId));
			//forward
			resp.sendRedirect(req.getContextPath().concat("/categories"));
		}
	}
	
	//Adding new data into Category
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		if("/category-add".equals(action)) {
			//get data from req
			String name = req.getParameter("categoryname");
			String id = req.getParameter("categoryid");
			//create entity obj
			Category c = (id==null || id.equals("") ? new Category() : catService.findById(Integer.parseInt(id)));
			c.setName(name); 
			//insert into db
			catService.save(c);
			//do redirect page ->to avoid double submit
			resp.sendRedirect(req.getContextPath().concat("/categories"));
		}
		
	}

}
