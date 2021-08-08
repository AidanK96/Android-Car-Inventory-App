package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import models.Vehicle;
import models.VehicleDAO;

public class APIServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    VehicleDAO dao = new VehicleDAO();
    Gson gson = new Gson();
    //Doget is used in order to retrieve all of the vehicles from the database and then display them using the restful web services
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream()));

            ArrayList<Vehicle> vehicles = dao.getAllVehicles();
            resp.setContentType("application/json");
            String json = gson.toJson(vehicles);
            out.write(json);
            out.close();

            }catch(Exception e) {
                e.printStackTrace();
                }
            }
    //do post is used to insert vehicles into the database using the restful webservices
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
       
        resp.setContentType("text/html");
        //read the parameters into the vehicle class
        Vehicle v = new Gson().fromJson(req.getReader(), Vehicle.class);
        System.out.println(v);
        //variables are taken from the vehicle class
        int id = Integer.valueOf(v.getVehicle_id());
        String make = (String) v.getMake();
        String model = (String) v.getModel();
        int year = Integer.valueOf(v.getYear());
        int price = Integer.valueOf(v.getPrice());
        String license = (String) v.getLicense_number();
        String colour = (String) v.getColour();
        int doors = Integer.valueOf(v.getNumber_doors());
        String transmission = (String) v.getTransmission();
        int mileage = Integer.valueOf(v.getMileage());
        String fuel = (String) v.getFuel_type();
        int engine = Integer.valueOf(v.getEngine_size());
        String body = (String) v.getBody_style();
        String condition = (String) v.getCondition();
        String notes = (String) v.getNotes();
       
        //creating a record from the parameters
        Vehicle c = new Vehicle (id, make, model,
                year,  price,  license, colour,
                doors,  transmission, mileage, fuel,
                engine, body, condition, notes);
        //String json = req.getParameter("json");
       
        //Vehicle newCon =gson.fromJson(json, Vehicle.class);
         boolean insert = false;
            try {
                dao.insertVehicle(c);
                insert = true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            if(insert) {
                out.write("New car added");
            } else {
                out.write("error");
            }
            out.close();
            }
    //The do put method is used for updating the vehicle records using the restful web service
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//creates a new gson object for the vehicle v
        Vehicle v = new Gson().fromJson(req.getReader(), Vehicle.class);
        System.out.println(v);
        int vehicle_id = Integer.valueOf(v.getVehicle_id());
        String make = (String) v.getMake();
        String model = (String) v.getModel();
        int year = Integer.valueOf(v.getYear());
        int price = Integer.valueOf(v.getPrice());
        String license = (String) v.getLicense_number();
        String colour = (String) v.getColour();
        int doors = Integer.valueOf(v.getNumber_doors());
        String transmission = (String) v.getTransmission();
        int mileage = Integer.valueOf(v.getMileage());
        String fuel = (String) v.getFuel_type();
        int engine = Integer.valueOf(v.getEngine_size());
        String body = (String) v.getBody_style();
        String condition = (String) v.getCondition();
        String notes = (String) v.getNotes();


        Vehicle c = new Vehicle (vehicle_id, make, model,
                year,  price,  license, colour,
                doors,  transmission, mileage, fuel,
                engine, body, condition, notes);
        String json = req.getParameter("json");

        Vehicle newCon =gson.fromJson(json, Vehicle.class);
         boolean inserted = false;
            try {
            	//Executes the vechicledao update vehicle function 
                dao.updateVehicle( c);
                inserted = true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } }
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //sets boolean deleted to false
        
        
        String vehicle_id = req.getParameter("vehicleid");
        Writer out = resp.getWriter();
        boolean deleted = false;
        System.out.println(vehicle_id);
        try {
            
            //passes into vehicle id to the delete dao
            deleted = dao.delVehicle(Integer.parseInt(vehicle_id));
            
        }catch(Exception h) {
            //catches exceptions
            h.printStackTrace();
        }
        //displays  message if done
        if(deleted) {
            out.write("Vehicle deleted");
        }else {
            //displays negative message if not done
            out.write("Vehicle ID not found");
        }
    }
    }