/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import entity.Order;
import entity.OrderDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class PaymentController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("view/user/payment/cart.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");
        switch (action) {
            case "add-product":
                addProduct(request, response);
                break;
            case "change-quantity":
                changeQuantity(request,response);    
                break;
            default:
                throw new AssertionError();
        }
        response.sendRedirect("payment");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //get ve session
        HttpSession session = request.getSession();
        //get ve product id
        int id = Integer.parseInt(request.getParameter("id"));
        //get ve quiantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay ve cart o tren session
        Order cart = (Order) session.getAttribute("cart");
        if (cart == null) {
            cart = new Order();
        }
        OrderDetails od = new OrderDetails();
        od.setProductId(id);
        od.setQuantity(quantity);
        
        //them orderdetails vao trong cart
        addOrderDetailsToOrder(od, cart);
        //set cart moi len session
        session.setAttribute("cart", cart);
    }

    private void addOrderDetailsToOrder(OrderDetails od, Order cart) {
        boolean isAdd = false;
        for (OrderDetails obj : cart.getListOrderDetails()) {
            if (obj.getProductId() == od.getProductId()) {
                obj.setQuantity(obj.getQuantity() + od.getQuantity());
                isAdd = true;
            }
        }
        if (isAdd == false) {
            cart.getListOrderDetails().add(od);
        }
    }

    private void changeQuantity(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
        try {
            //get ve product id
            int id = Integer.parseInt(request.getParameter("id"));
            //get ve quantity
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            //lay ve cart
            
            Order cart = (Order) session.getAttribute("cart");
            //thay doi quantity
            for (OrderDetails obj : cart.getListOrderDetails()) {
                if (obj.getProductId() == id) {
                    obj.setQuantity(quantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}

   

}