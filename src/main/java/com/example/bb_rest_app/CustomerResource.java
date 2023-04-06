package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/customer")
@RequestScoped
public class CustomerResource {

    public static void main(String[] args) throws ParseException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        Gson gson = new GsonBuilder().create();
        customer = gson.fromJson(gson.toJson(customer), Customer.class);
        try {
            DBHelper.insertUser(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
                    customer.getPassword(), customer.getNIC(), customer.getDOB(), customer.getLoanAmount());
            String message = "Customer created successfully";
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED);
            builder.entity(message);
            Response response = builder.build();
            return response;
        } catch (SQLException e) {
            String errorMessage = "Error creating customer: " + e.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getCustomers());
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}









