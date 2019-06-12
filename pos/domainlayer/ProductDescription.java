
package pos.domainlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDescription {

	private ItemID id;
	private Money price;
	private String description;

	public ProductDescription(ItemID id, Money price, String description) {
		this.id = id;
		this.price = price;
		this.description = description;
	}

	public ItemID getItemID() {
		return id;
	}

	public Money getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}
}

