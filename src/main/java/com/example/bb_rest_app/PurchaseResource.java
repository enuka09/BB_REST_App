package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/purchases")
public class PurchaseResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPurchase(Purchases purchases) {
        Gson gson = new GsonBuilder().create();
        purchases = gson.fromJson(gson.toJson(purchases), Purchases.class);
        DBHelper dbHelper = new DBHelper();
        dbHelper.addPurchase(purchases);
        return Response.status(201).entity(purchases).build();
        //  return "success";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchase() {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getPurchase());
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
