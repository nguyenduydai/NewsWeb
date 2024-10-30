package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.News;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.FormUtils;

import paging.PageRequest;
import paging.Pageble;

@WebServlet(urlPatterns= {"/admin-new"})
public class NewsController extends HttpServlet{

	private static final long serialVersionUID = -8954396074072052061L;
	   
	@Inject
	private INewService newService;
	@Inject
	private ICategoryService categoryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		News model=FormUtils.toModel(News.class, request);
		String view="";
		if(model.getType().equals("list")) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),model.getSortName(), model.getSortBy());	
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.findAll().size());
			model.setTotalPage((int)Math.ceil( (double)model.getTotalItem()/model.getMaxPageItem() ));
			view="/views/admin/listnew.jsp";
		}else if(model.getType().equals("edit")) {
			if(model.getId() != null){
				model=newService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			
			view="/views/admin/edit.jsp";
		}
		request.setAttribute("model", model); 
		RequestDispatcher rd=request.getRequestDispatcher(view);
		rd.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		
	}
}