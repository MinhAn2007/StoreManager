package vn.edu.iuh.fit.wwwlab02.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.entities.Product;
import vn.edu.iuh.fit.wwwlab02.services.ProductService;

import java.util.List;

@Path("/products")
public class ProductResource {
    private final ProductService productService= new ProductService();

    public ProductResource() {
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Product> list = productService.getAllProducts();
        return Response.ok(list).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActive() {
        List<Product> list = productService.getActiveProduct();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        if(productService.findProduct(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productService.findProduct(id).get()).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Product product){
        productService.insertProduct(product);
        return Response.ok(product).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id,Product product){
        if(productService.findProduct(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update= productService.updateProduct(product);
        if(!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(product).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){
        boolean delete=productService.deleteProduct(id);
        if(!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}