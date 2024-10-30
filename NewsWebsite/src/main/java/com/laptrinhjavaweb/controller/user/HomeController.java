package com.laptrinhjavaweb.controller.user;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.News;
import com.laptrinhjavaweb.model.User;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtils;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns= {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet{
    @Inject
	private ICategoryService categoryService;
    
    @Inject
	private INewService newService;
    
    @Inject
    private IUserService userService;
    
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    
	private static final long serialVersionUID = 7023912832621000370L;
	
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		String action=request.getParameter("action");
		if(action !=null && action.equals("login"))
		{
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd=request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}else if(action !=null && action.equals("logout")) {
			SessionUtils.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd=request.getRequestDispatcher("/views/user/home.jsp");
			rd.forward(request, response);
		}
	}*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}else if (action != null && action.equals("logout")) {
			SessionUtils.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		} else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/user/home.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		String action=request.getParameter("action");
		if(action !=null && action.equals("login"))
		{
			
				User model=FormUtils.toModel(User.class, request);
				model=userService.findByUserNameAndPasswordAndStatus(model.getUserName(),model.getPassword(),1);
				if (model!=null) {
					
					SessionUtils.getInstance().putValue(request, "USERMODEL", model);
					if (model.getRole().getCode().equals("USER")) {
						response.sendRedirect(request.getContextPath()+"/trang-chu");
					} else if (model.getRole().getCode().equals("ADMIN")) {
						response.sendRedirect(request.getContextPath()+"/admin-trang-chu");
					}
				} else {
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
				}
	
		}
	}
	
}
