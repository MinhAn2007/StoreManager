package vn.edu.iuh.fit.wwwlab02.frontend;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.wwwlab02.frontend.model.CustomerModel;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            CustomerModel customerModel = new CustomerModel();
            customerModel.insertCust(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
