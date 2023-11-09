package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.services.ProductImageService;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;

import java.util.List;

@Path("/productImages")
public class ProductImageResource {
    private final ProductImageService productImageService= new ProductImageService();

    public ProductImageResource() {
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ProductImage> list = productImageService.getAllProductImages();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") long id) {
        if (productImageService.findProductImage(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productImageService.findProductImage(id).get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(ProductImage productImage) {
        productImageService.insertProductImage(productImage);
        return Response.ok(productImage).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, ProductImage productImage) {
        if (productImageService.findProductImage(id).isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        boolean update = productImageService.updateProductImage(productImage);
        if (!update)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(productImage).build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        boolean delete = productImageService.deleteProductImage(id);
        if (!delete)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(id).build();
    }
}