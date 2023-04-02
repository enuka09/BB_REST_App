package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/brands")
public class BrandResource {

    //View Brands
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrand() {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getBrand());
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBrand(Brand brand) {
        Gson gson = new GsonBuilder().create();
        brand = gson.fromJson(gson.toJson(brand), Brand.class);
        DBHelper dbHelper = new DBHelper();
        dbHelper.addBrand(brand.getId(), brand.getName());
        return Response.status(201).entity(brand).build();
    }


    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBrand(@PathParam("id") String id) {
        Brand brand = new Brand(id);
        try {
            DBHelper.deleteBrand(brand);
            return Response.status(204).build(); // 204 No Content indicates success
        } catch (SQLException e) {
            return Response.status(500).entity("Failed to delete Brand with ID " + id + ".").build(); // 500 Internal Server Error indicates failure
        }
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBrand(@PathParam("id") String BrandId, Brand brand) {
        DBHelper dbHelper = new DBHelper();
        brand.setId(BrandId);
        dbHelper.updateBrand(brand);

        return Response.status(Response.Status.OK).build();
    }
}
