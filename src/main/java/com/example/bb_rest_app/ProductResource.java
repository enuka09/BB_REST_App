package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.text.ParseException;

@Path("/products")

public class ProductResource {

    public static void main(String[] args) {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct() {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getProduct());
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertProduct(Product product) {
        Gson gson = new GsonBuilder().create();
        product = gson.fromJson(gson.toJson(product), Product.class);
        DBHelper dbHelper = new DBHelper();
        dbHelper.addProduct(product);
        return Response.status(201).entity(product).build();
      //  return "success";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") String id) {
        Product product = new Product(id);
        try {
            DBHelper.deleteProduct(product);
            return Response.status(204).build(); // 204 No Content indicates success
        } catch (SQLException e) {
            return Response.status(500).entity("Failed to delete Product with ID " + id + ".").build(); // 500 Internal Server Error indicates failure
        }
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") String productId, Product product) {
        DBHelper dbHelper = new DBHelper();
        product.setId(productId);
        dbHelper.updateProduct(product);
        return Response.status(Response.Status.OK).build();
    }
}

