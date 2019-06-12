package pos.domainlayer;

import java.util.Map;

public class DescriptionIndirection {
	private ProductCatalog catalog ;
	public DescriptionIndirection(ProductCatalog catalog) {
		this.catalog = catalog;
	}
	public ProductCatalog getCatalog() {
		return this.catalog;
	}
	
	public Map ProductHashMap() {
		return catalog.getDescriptions();
	}

	
}
