package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/purchase")
public class RelevantPurchaseResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPurchasebyUsername(@QueryParam("username") String username) {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getPurchasebyUsername(username));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
