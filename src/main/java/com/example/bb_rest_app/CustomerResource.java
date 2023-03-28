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
//        CustomerResource customerResource = new CustomerResource();
//        Customer newCustomer = new Customer();
//
//
//        newCustomer.setFirstName("John");
//        newCustomer.setLastName("Doe");
//        newCustomer.setUsername("johndoe");
//        newCustomer.setPassword("password123");
//        newCustomer.setNIC("1234567890");
//        newCustomer.setDOB("1990-01-01");
//
//        String response = customerResource.createCustomer(newCustomer);
//        System.out.println(response);
}

//    public CustomerResource() {
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getCustomers() {
//        Gson gson = new GsonBuilder().create();
//        try {
//            return gson.toJson(DBHelper.getCustomers());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String createCustomer(Customer customer) {
//        Gson gson = new GsonBuilder().create();
//        customer = gson.fromJson(gson.toJson(customer), Customer.class);
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date dob = new Date(sdf.parse(customer.getDOB()).getTime());
//            DBHelper.insertUser(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
//                    customer.getPassword(), customer.getNIC(), dob);
//            String message = "Customer created successfully";
//            return "inserted";
//        } catch (SQLException | ParseException e) {
//            String errorMessage = "Error creating customer: " + e.getMessage();
//            return "error";
//        }
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        Gson gson = new GsonBuilder().create();
        customer = gson.fromJson(gson.toJson(customer), Customer.class);
        try {
            DBHelper.insertUser(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
                    customer.getPassword(), customer.getNIC(), Date.valueOf(customer.getDOB().toString()));
            String message = "Customer created successfully";
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED);
            builder.entity(message);
            Response response = builder.build();
            return response;
           // return "inserted";
        } catch (SQLException e) {
            String errorMessage = "Error creating customer: " + e.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
           // return "error";
        }
    }

}









