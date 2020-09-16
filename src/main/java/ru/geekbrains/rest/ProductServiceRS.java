package ru.geekbrains.rest;

import ru.geekbrains.persist.Category;
import ru.geekbrains.service.CategoryRepr;
import ru.geekbrains.service.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/product")
public interface ProductServiceRS {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr productRepr);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr productRepr);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(@PathParam("id") long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findByIdRS(@PathParam("id") long id);

    @POST
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    void addCategory(CategoryRepr category);

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findByCategoryIdRs(@PathParam("id") Long id);
}
