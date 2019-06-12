package pos.domainlayer;

import java.util.List;
import java.util.Map;

public class Register {
	
	private ProductCatalog catalog;
	private Sale currentSale;
	
	public Register(ProductCatalog catalog){
		this.catalog = catalog;
	}
	
	public void endSale()//세일 객체 완료 처리
	{
		currentSale.becomeComplete();
	}
	
	public void enterItem(ItemID id, int quantity)//아이템 저장
	{
		ProductDescription desc = catalog.getProductDescription(id);
		currentSale.makeLineItem(desc, quantity);
	}

	public Sale makeNewSale() {
		return currentSale = new Sale();
	}
	
	public Map getProductHashMap() {
		return this.catalog.getDescriptions();
	}//catalog에서 해쉬 맵을 직접 받음
	
	public void makePayment(Money cashTendered)//지불 처리 실행
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

