package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

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
}
