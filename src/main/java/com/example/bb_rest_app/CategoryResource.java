package com.example.bb_rest_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.SQLException;
import java.text.ParseException;

@Path("/categories")
public class CategoryResource {

    public static void main(String[] args) throws ParseException {

//      Insert Category
//        CategoryResource categoryResource = new CategoryResource();
//        Category newCategory = new Category();
//
//        newCategory.setId("C002");
//        newCategory.setName("Computer Accessories");
//
//        String response = categoryResource.insertCategory(newCategory);
//        System.out.println(response);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCategory(Category category){
        Gson gson = new GsonBuilder().create();
        category = gson.fromJson(gson.toJson(category), Category.class);
        DBHelper dbHelper = new DBHelper();
        dbHelper.addCategory(category.getId(),category.getName());
        return Response.status(201).entity(category).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory() {
        Gson gson = new GsonBuilder().create();
        try {
            String json = gson.toJson(DBHelper.getCategory());
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCategory(@PathParam("id") String id) {
        Category category = new Category(id);
        try {
            DBHelper.deleteCategory(category);
            return "Category with ID " + id + " has been deleted.";
        } catch (SQLException e) {
            return "Failed to delete category with ID " + id + ".";
        }
    }

}


