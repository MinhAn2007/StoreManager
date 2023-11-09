package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.services.ProductPriceService;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductPrice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/productPrices")
public class ProductPriceResource {
    private final ProductPriceService productPriceService = new ProductPriceService();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public ProductPriceResource() {
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ProductPrice> list = productPriceService.getAllProductPrice();
        return Response.ok(list).build();
    }
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIDNotDate(@PathParam("id") long id) {
        if (productPriceService.findByIdNotDate(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productPriceService.findByIdNotDate(id)).build();
    }
    @GET
    @Path("/last/{id}")
    public Response findLatestProductPrice(@PathParam("id") long id) {
        // Kiểm tra xem giá sản phẩm có ngày gần nhất tồn tại hay không
        Double latestPrice = productPriceService.findLatestProductPrice(id);
        if (latestPrice == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Trả về giá sản phẩm có ngày gần nhất
        return Response.ok(latestPrice).build();
    }
    @GET
    @Path("/{id}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id, @PathParam("date") String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        if (productPriceService.findProductPrice(id, dateTime).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productPriceService.findProductPrice(id, dateTime).get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ProductPrice productPrice) {
        productPriceService.insertProductPrice(productPrice);
        return Response.ok(productPrice).build();
    }

    @PUT
    @Path("/{id}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, @PathParam("date") String date, ProductPrice productPrice) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        if (productPriceService.findProductPrice(id, dateTime).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = productPriceService.updateProductPrice(productPrice);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productPrice).build();
    }

    @DELETE
    @Path("/{id}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id, @PathParam("date") String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        boolean delete = productPriceService.deleteProductPrice(id, dateTime);
        if (!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}