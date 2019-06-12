package pos.domainlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
	
	//private Map<ItemID, ProductDescription> descriptions = new HashMap<ItemID, ProductDescription>();
	private Map<String, ProductDescription> descriptions = new HashMap<String, ProductDescription>();
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResultSet;
	
	private String itemId, description;
	private int price;
	public ProductCatalog() {
		
	}
	public ProductCatalog(String dbFileName) {
		//db¿¡ ¿¬°á
	      try {
	    	  
	         //connect to database
	         myConnection = DriverManager.getConnection(
	               "jdbc:ucanaccess://"+dbFileName);
	         
	         //create Statement for executing SQL
	         myStatement = myConnection.createStatement();
	      }catch(SQLException exception) {
	         exception.printStackTrace();
	      }
	      
	      ProductSetting();
	}
	public void ProductSetting() {
		ProductDescription desc;
		try {
			myResultSet = myStatement.executeQuery(
					"SELECT itemId," + "description, price FROM ProductDescriptions ");
			while(myResultSet.next()) {
				itemId = myResultSet.getString("itemId");
				description = myResultSet.getString("description");
				price = myResultSet.getInt("price");
				
				ItemID id = new ItemID(itemId);
				Money mprice = new Money(price);
				
				desc = new ProductDescription(id, mprice, description);
				getDescriptions().put(id.toString(), desc);
			}
			myResultSet.close();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	public ProductDescription getProductDescription(ItemID id){
		return getDescriptions().get(id.toString());
	}
	
	public Map<String, ProductDescription> getDescriptions() {
		return descriptions;
	}

}

