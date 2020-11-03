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
import com.mmit.controller.service.ItemService;
import com.mmit.model.entity.Category;
import com.mmit.model.entity.Item;
@WebServlet(urlPatterns = {"/item-add","/items","/item-edit","/item-delete"},loadOnStartup = 1)
public class ItemController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CategoryService catservice;
	private ItemService itemservice;
	
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
		
		catservice = new CategoryService(EMF.createEntityManager());
		itemservice = new ItemService(EMF.createEntityManager());
	}
	 
	 @Override
	public void destroy() {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf!=null && emf.isOpen()) {
			emf.close();
		}
	}
	 
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		if("/item-add".equals(action)) {
			
			//get data fro req
			String itemId = req.getParameter("itemid");
			String catId = req.getParameter("categoryid");
			String name = req.getParameter("itemname");
			String price = req.getParameter("price");
			//System.out.println("Item ID: "+itemId);
			
			//create item-entity obj
			/* Item item = new Item(); */
			Item item = (itemId==null || itemId.equals("") ? new Item(): itemservice.findById(Integer.parseInt(itemId)));
			item.setName(name);
			item.setPrice(Integer.parseInt(price));
			item.setCategory(catservice.findById(Integer.parseInt(catId)));
			//insert into db
			itemservice.save(item);
			//redirect
			resp.sendRedirect(req.getContextPath().concat("/items"));
		}
	}
	 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if("/item-add".equals(path) || "/item-edit".equals(path)) {
			
			//get category list from db
			List<Category> catlist = catservice.findAll();
			//add list req
			req.setAttribute("categories", catlist);
			
			if("/item-edit".equals(path)) {
				//get item id form req
				String id = req.getParameter("itemid");
				System.out.println("Item Id for Edit:"+id);
				//find item to db
				Item itemobj = itemservice.findById(Integer.parseInt(id));
				//add item to req
				req.setAttribute("item", itemobj);
			}
			//forward page
			req.getServletContext().getRequestDispatcher("/item-add.jsp").forward(req, resp);
		}
		
		else if("/items".equals(path)) {
			//get item from db
			List<Item> list = itemservice.findAll();
			//add items to req
			req.setAttribute("items", list);
			//forward page
			getServletContext().getRequestDispatcher("/item.jsp").forward(req, resp);
		}
		
		else if ("/item-delete".equals(path)) {
			//get id from req data
			String itemid = req.getParameter("itemid");
			//remove from db
			itemservice.delete(Integer.parseInt(itemid));
			//redirect
			resp.sendRedirect(req.getContextPath().concat("/items"));
		}
	}
}
