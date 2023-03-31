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
//        AdminLoginResource adminLoginResource = new AdminLoginResource();
//        Admin newAdmin = new Admin();
//
//        newAdmin.setUsername("enuka@09");
//        newAdmin.setPassword("Enuka_@2002");
//
//        String response = adminLoginResource.adminlogin(newAdmin.getUsername(), newAdmin.getPassword());
//        System.out.println(response);
    }


//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String adminlogin(@FormParam("username") String username, @FormParam("password") String password) {
//        boolean isValidAdmin = DBHelper.validateAdmin(username, password);
//
//        if (isValidAdmin) {
//            return "Login successful";
//        } else {
//            return "Invalid username or password";
//        }
//    }


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



