package pos.domainlayer;

import java.util.List;
import java.util.Map;

public class Register {
	
	private ProductCatalog catalog;
	private Sale currentSale;
	
	public Register(ProductCatalog catalog){
		this.catalog = catalog;
	}
	
	public void endSale()//���� ��ü �Ϸ� ó��
	{
		currentSale.becomeComplete();
	}
	
	public void enterItem(ItemID id, int quantity)//������ ����
	{
		ProductDescription desc = catalog.getProductDescription(id);
		currentSale.makeLineItem(desc, quantity);
	}

	public Sale makeNewSale() {
		return currentSale = new Sale();
	}
	
	public Map getProductHashMap() {
		return this.catalog.getDescriptions();
	}//catalog���� �ؽ� ���� ���� ����
	
	public void makePayment(Money cashTendered)//���� ó�� ����
	{
		currentSale.makePayment(cashTendered);
	}
	
	public ProductDescription getCatalogProduct(ItemID id) {
		return catalog.getProductDescription(id);
	}
	
	public Money getBalance() {
		return currentSale.getBalance();
	}
	
}

