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

@Path("/customer")
@RequestScoped
public class CustomerResource {


    public static void main(String[] args) throws ParseException {

        /* Insert Customer */
//        CustomerResource customerResource = new CustomerResource();
//        Customer newCustomer = new Customer();
//
//        newCustomer.setFirstName("Namal");
//        newCustomer.setLastName("Perera");
//        newCustomer.setUsername("namal@gmail.com");
//        newCustomer.setPassword("Namal_@1990");
//        newCustomer.setNIC("19901830040");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date utilDate = dateFormat.parse("1990-10-21");
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        newCustomer.setDOB(sqlDate);
//
//        String response = customerResource.createCustomer(newCustomer);
//        System.out.println(response);
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        Gson gson = new GsonBuilder().create();
        customer = gson.fromJson(gson.toJson(customer), Customer.class);
        try {
            DBHelper.insertUser(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
                    customer.getPassword(), customer.getNIC(), customer.getDOB());
            String message = "Customer created successfully";
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED);
            builder.entity(message);
            Response response = builder.build();
            return response;
           // return "inserted";
        } catch (SQLException e) {
            String errorMessage = "Error creating customer: " + e.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
//            return "error";
        }
    }

}









