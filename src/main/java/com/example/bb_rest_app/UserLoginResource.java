package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;

@Path("/auth")
public class UserLoginResource {

    public static void main(String[] args) throws ParseException {
//        UserLoginResource userLoginResource = new UserLoginResource();
//        Customer newCustomer = new Customer();
//
//        newCustomer.setUsername("kamal@");
//        newCustomer.setPassword("Kamal123");
//
//        String response = userLoginResource.login(newCustomer.getUsername(), newCustomer.getPassword());
//        System.out.println(response);
    }


//    @POST
////    @Path("/login")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String login(@FormParam("username") String username, @FormParam("password") String password) {
//        boolean isValidUser = DBHelper.validateUser(username, password);
//
//        if (isValidUser) {
//            return "Login successful";
//        } else {
//            return "Invalid username or password";
//        }
//    }


    @POST
//    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Customer customer) {
        Gson gson = new GsonBuilder().create();
        customer = gson.fromJson(gson.toJson(customer), Customer.class);
        boolean isValidUser = DBHelper.validateUser(customer.getUsername(), customer.getPassword());

        if (isValidUser) {
            String responseMsg = "Login successful";
            return Response.ok(responseMsg).build();
        } else {
            String responseMsg = "Invalid username or password";
            return Response.status(Response.Status.UNAUTHORIZED).entity(responseMsg).build();
        }
    }
}
