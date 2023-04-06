package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.text.ParseException;

@Path("/authadmin")
public class AdminLoginResource {

    public static void main(String[] args) throws ParseException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adminlogin(Admin admin) {
        Gson gson = new GsonBuilder().create();
        admin = gson.fromJson(gson.toJson(admin), Admin.class);
        boolean isValidAdmin = DBHelper.validateAdmin(admin.getUsername(), admin.getPassword());

        if (isValidAdmin) {
            String responseMsg = "Login successful";
            return Response.ok(responseMsg).build();
        } else {
            String responseMsg = "Invalid username or password";
            return Response.status(Response.Status.UNAUTHORIZED).entity(responseMsg).build();
        }
    }
}



