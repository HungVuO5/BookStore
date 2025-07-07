/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authen;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nguye
 */
public class AuthenticationController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get action
        String action = request.getParameter("action") != null
                        ? request.getParameter("action")
                        :"";
        //dua theo action set url trang can den
        String url ;
        switch (action) {
            case "login":
                url = "view/authen/login.jsp";
                break;
            default:
                url = "home";
        }
        //chuyen trang
        request.getRequestDispatcher(url).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get action
        String action = request.getParameter("action") != null
                        ? request.getParameter("action")
                        :"";
        //dua theo action xu li request
        String url ;
        switch (action) {
            case "login":
                url = loginDoPost(request,response);
                break;
            default:
                url = "home";
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String loginDoPost(HttpServletRequest request, HttpServletResponse response) {
        //get ve ttin nguoi dung nhap
        
        //ktra ton tai 
        
        //true-> trang home(set account)
        
        //false-> login+ bao loi
    }

}
