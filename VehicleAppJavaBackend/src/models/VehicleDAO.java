package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDAO {

	/**
	 * The purpose of this class is to act as the Database access object point
	 * This class is where the majority of the methods are for the database that interact with the database
	 * Through the methods here different operations can be made
	 * 	 *@author Aidan Kirkby
	 *@version 1.0
	 */
//The connection method is used to get a connection to the database, this is needed to allow other methods to interact with the database
	private static Connection getDBConnection() 
	{
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String URL = "jdbc:sqlite:vehicles.sqlite";
            conn = DriverManager.getConnection(URL);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
      }
// getAllVehicles is used in order to fetch all vehicle records from the database, this method is an arraylist which contains all the vehicles
public ArrayList<Vehicle> getAllVehicles() throws SQLException
	{
		System.out.println("Retrieving all vehicles");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		//the SQL query selects everything from the vehicles table
		String query = "SELECT * FROM vehicles";
		Vehicle temp = null;
		
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query);
			while(result.next()) {
				
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
			    int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String Notes = result.getString("Notes");
				
				vehicleList.add(new Vehicle(vehicle_id, make, model, year,
								price, license_number, colour, number_doors, transmission,
								mileage, fuel_type, engine_size, body_style, condition, Notes));
				//Whilst the loop is running through the different results it is placing the different information stored into different variables
			}	
		} finally {
			if (result !=null) {result.close();}
			if (statement !=null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}	
			//After all the vehicles have been added close everything
		}
		return vehicleList;	
	}
//getVehicle is used in order to fetch a vehicle from the database with a specific id, the only place it differs from get all vehicles is the SQL query
public ArrayList<Vehicle> getVehicle(int vehicleid) throws SQLException
	{
	
		System.out.println("Retreiving vehicle id" + vehicleid);
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		//This SQL query fetches everything where the vehicle_id is equal to what the user enters
		String query = "SELECT * FROM vehicles WHERE vehicle_id =" + vehicleid +";";
		Vehicle temp = null;
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		try {
			//open connection
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query);
			while(result.next())
			{
			
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String Notes = result.getString("Notes");
				
					vehicleList.add(new Vehicle(vehicle_id, make, model, year,
							price, license_number, colour, number_doors, transmission,
							mileage, fuel_type, engine_size, body_style, condition, Notes));
			}	
			//close the connections
		} finally {
			if (result !=null) {result.close();}
			if (statement !=null) {statement.close();}
			if (dbConnection != null) {dbConnection.close();}	
	   }
	return vehicleList;	
}
//This is the method that the program calls to delete a vehicle record from the database
public boolean delVehicle(int vehicleid) throws SQLException
		{	//
			Connection dbConnection = null;
			Statement statement = null;
			boolean bool = false;
			try {
				dbConnection = getDBConnection();
				dbConnection.setAutoCommit(false);
				System.out.println("Delte operation operating");
		
				statement = dbConnection.createStatement();
				String query = "DELETE FROM vehicles WHERE vehicle_id = " + vehicleid +";";
				//This SQL query is used to delete records from vehicles where the ID matches what the user enters, if it is not correct a record will not be deleted
				statement.executeUpdate(query);
				dbConnection.commit();
				statement.close();
				
				}
				finally {
					}
					if( statement!=null) {
						statement.close();
					}
					if(dbConnection!=null) {
						dbConnection.close();
					}
				return bool;		
}
//The method for inserting a vehicle is used to insert a new record into the database
public boolean insertVehicle(Vehicle i) throws SQLException{
	
	System.out.println("Insert Vehicle");
	Connection dbConnection = null;
	Statement statement = null;
	ResultSet result = null;
	//This SQL query inserts values into the vehicle table that the user has entered
	String sql = "INSERT INTO vehicles VALUES("+ i.getVehicle_id() + ",'" + i.getMake() + "','" + i.getModel() + "'," + i.getYear() + "," +  i.getPrice() + ",'"+  i.getLicense_number() + "','"+  i.getColour() + "',"+  i.getNumber_doors() + ",'"+  i.getTransmission() + "',"+  i.getMileage() + ",'" + i.getFuel_type() + "'," +i.getEngine_size() + ",'"+i.getBody_style() + "','"+i.getCondition() + "','"+i.getNotes()+ "');";
	boolean bool = false;
	try {
		dbConnection = getDBConnection();
		statement = dbConnection.createStatement();
		System.out.println("Query = " + sql);
		statement.executeUpdate(sql);	
	}
	finally {
		if( result!= null) {
			result.close();	
		}
		if( statement!=null) {
			statement.close();
		}
		if(dbConnection!=null) {
			dbConnection.close();
		}	
	}
	return bool;		
}
//This method is called in order to update a record that already exists, if it does not then nothing wwill happen
public boolean updateVehicle(Vehicle i) throws SQLException {
    System.out.println("Retriveing all vehicles...");
    Connection dbConnection = null;
    Statement statement = null;
    ResultSet result = null;
    //This SQL query updates a vehicle record when a matching ID is entered
    String query =  "UPDATE vehicles SET vehicle_id ='"+ i.getVehicle_id() +"', make ='"+i.getMake()+"', model ='"+i.getModel()+"', year ="+i.getYear()+", price ="+i.getPrice()+", license_number ='"+i.getLicense_number()+"', colour ='"+i.getColour()+"', number_doors ="+i.getNumber_doors()+", transmission ='"+i.getTransmission() +"', mileage ="+i.getMileage()+", fuel_type='"+i.getFuel_type()+"', engine_size = "+i.getEngine_size()+", body_style ='"+i.getBody_style()+"', condition ='"+i.getCondition()+"', notes ='"+i.getNotes()+"'  WHERE vehicle_id = "+ i.getVehicle_id() +";"; 

    boolean bool = false;
    try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();
        System.out.println("DBQuery = " + query);
        statement.executeUpdate(query); 
       
    }
    finally {
        if (result != null) {
            result.close(); }

        if (statement != null) {
            statement.close();
            }
            if (dbConnection != null) {
            dbConnection.close();
            }
        }
    return bool; 
}
}