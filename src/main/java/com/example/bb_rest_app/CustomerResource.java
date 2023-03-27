package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
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

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(String content) {
        Gson gson = new GsonBuilder().create();
        Customer customer = gson.fromJson(content, Customer.class);

        if (st.getId() == null) {
        return Response.status(Response.Status.BAD_REQUEST).build();
        }


        DBHelper.insertUser(String firstName, String lastName, String username, String password, String dob, String nic);

        return Response.status(Response.Status.CREATED).build();
    } */


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String nicNumber = request.getParameter("nicNumber");
        String dob = request.getParameter("dob");

        // Validate form data
        if (firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty()
                || username == null || username.isEmpty()
                || password == null || password.isEmpty()
                || confirmPassword == null || confirmPassword.isEmpty()
                || nicNumber == null || nicNumber.isEmpty()
                || dob == null || dob.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        }
    }







