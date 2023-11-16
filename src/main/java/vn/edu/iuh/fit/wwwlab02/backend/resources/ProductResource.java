package vn.edu.iuh.fit.wwwlab02.backend.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductAndImageDTO;
import vn.edu.iuh.fit.wwwlab02.backend.dto.ProductInfoDTO;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductImage;
import vn.edu.iuh.fit.wwwlab02.backend.entities.ProductPrice;
import vn.edu.iuh.fit.wwwlab02.backend.services.ProductService;
import vn.edu.iuh.fit.wwwlab02.backend.entities.Product;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        List<Product> list = productService.getAllProduct();
        return Response.ok(list).build();
    }

    @GET
    @Path("/active")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActive() {
        List<ProductInfoDTO> list = productService.getActiveProductInfo();
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
    public Response insert(ProductAndImageDTO dto) {
        try {
            System.out.println(dto);
            Product product = new Product();
            ProductImage productImage = new ProductImage();
            ProductPrice productPrice = new ProductPrice();
            product.setName(dto.getProductName());
            product.setDescription(dto.getDescription());
            product.setManufacturer(dto.getManufacturer());
            product.setUnit(dto.getUnit());
            product.setStatus(dto.getStatus());
            productImage.setProduct(product);
            productImage.setPath(dto.getPath());
            productImage.setAlternative(dto.getAlternative());
            productPrice.setProduct(product);
            productPrice.setPrice(dto.getPrice());
            productPrice.setNote("gia dau tien");
            LocalDateTime ldt = LocalDateTime.now();
            productPrice.setPrice_date_time(ldt);
            boolean success = productService.insertProduct(product, productImage,productPrice);
            if (success) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, ProductAndImageDTO dto) {
        try {
            Optional<Product> optionalProduct = productService.findProduct(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                List<ProductImage> productImages = product.getProductImageList();
                if (!productImages.isEmpty()) {
                    ProductImage productImage = productImages.get(0);
                    productImage.setPath(dto.getPath());
                    productImage.setAlternative(dto.getAlternative());
                }
                product.setName(dto.getProductName());
                product.setDescription(dto.getDescription());
                product.setManufacturer(dto.getManufacturer());
                product.setUnit(dto.getUnit());
                product.setStatus(dto.getStatus());

                boolean success = productService.updateProduct(product,productImages.get(0));

                if (success) {
                    return Response.ok().build();
                } else {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
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