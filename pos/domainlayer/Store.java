package pos.domainlayer;

public class Store {
	private ProductCatalog catalog = new ProductCatalog("POS.mdb");
	private Register register = new Register(catalog);
	
	public Register getRegister() { return register; }
}

