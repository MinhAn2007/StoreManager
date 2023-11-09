package vn.edu.iuh.fit.wwwlab02.frontend.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class CustomerModel {
    private final String apiEndpoint = "http://localhost:8080/api/customers"; // Điều chỉnh URL của API theo cơ sở của bạn

    public void insertCust(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        String customerData = "{\"name\":\"" + name + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone + "\",\"address\":\"" + address + "\"}";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiEndpoint);

        try {
            StringEntity entity = new StringEntity(customerData);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);


            resp.sendRedirect("/admin/customerListing.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
