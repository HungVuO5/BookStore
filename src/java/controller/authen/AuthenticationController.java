/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authen;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import implement.*;
import constant.*;
/**
 *
 * @author nguye
 */
public class AuthenticationController extends HttpServlet {
    AccountDAO accountDAO = new AccountDAO();

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
            case "log-out":
                url = logOut(request,response);
                break;
            case "sign-up":
                url = "view/authen/register.jsp";
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
            case "sign-up":
                url = signUp(request,response);
                break;
            
            default:
                url = "home";
        }
            request.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String loginDoPost(HttpServletRequest request, HttpServletResponse response) {
        String url = null;
        //get ve ttin nguoi dung nhap
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //ktra ton tai 
        Account account = Account.builder()
                .username(username)
                .password(password)
                .build();
        Account accFoundByUsernameAndPass = accountDAO.findByUsernameAndPass(account);
        
        //true-> trang home(set account)
        if (accFoundByUsernameAndPass != null) {
            request.getSession().setAttribute(CommonConst.SESSION_ACCOUNT,
                    accFoundByUsernameAndPass);
            url = "home";
            //false-> login+ bao loi
        }else{
            request.setAttribute("error", "Username or password incorrect !");
            url = "view/authen/login.jsp";
        }
        return url;
        
    }

    private String logOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(CommonConst.SESSION_ACCOUNT);
        return "home";
    }

    private String signUp(HttpServletRequest request, HttpServletResponse response) {
        String url ;
        //get inf
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //check exist
        Account account = Account.builder()
                        .username(username)
                        .password(password)
                        .build();
        boolean isExistUsername = accountDAO.checkUsernameExist(account);
        //true->back
        if (isExistUsername) {
            request.setAttribute("error", "Username exist !!");
            url = "view/authen/register.jsp";

        }else{
        //false-> create+ back
            accountDAO.insert(account);
            url = "home";
        }
        return url;
    }

}
