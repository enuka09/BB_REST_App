package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("/customer")
@RequestScoped
public class CustomerResource {

   public CustomerResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomers() {
        Gson gson = new GsonBuilder().create();
        try {
            return gson.toJson(DBHelper.getCustomers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }





