package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.News;
import com.laptrinhjavaweb.model.User;

import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtils;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns= {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = -915988021506484384L;
    
	@Inject
	private INewService newService;
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        ObjectMapper mapper=new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
 		News n=	HttpUtils.of(request.getReader()).toModel(News.class);
 		n.setCreateBy( ((User)SessionUtils.getInstance().getValue(request,"USERMODEL")).getUserName() );
 		n=newService.save(n);
        mapper.writeValue(response.getOutputStream(),n);
 	}
	protected void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		ObjectMapper mapper=new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
 		News n=	HttpUtils.of(request.getReader()).toModel(News.class);
 		n.setModifieBy( ((User)SessionUtils.getInstance().getValue(request,"USERMODEL")).getUserName() );
 		n=newService.update(n);
        mapper.writeValue(response.getOutputStream(),n);
	}
	protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		ObjectMapper mapper=new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
 		News n=	HttpUtils.of(request.getReader()).toModel(News.class);
 		
 		newService.delete(n.getIds());
        mapper.writeValue(response.getOutputStream(),"{}");
	}
}
