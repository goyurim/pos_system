package pos.domainlayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
	private List<SalesLineItem> lineItems =  new ArrayList<SalesLineItem>();
	
	ITaxCalculatorAdapter taxCalculatorAdapter;
	ISalePricingStrategy princingStrategy;
	List<TaxLineItem> items = new ArrayList<TaxLineItem>();
	private Date date = new Date();//판매 날짜
	private boolean isComplete = false;//판매 완료 확인
	private Payment payment;//지불 처리에 대한 내용
	private Money taxTotal;
	private Money total;
	public Money getBalance()	{
		return payment.getAmount().minus(getTotal());//잔액 반환을 위해 minus
	}
	
	public void becomeComplete(){ 
		isComplete = true; 
	}
	
	public boolean isComplete() {  
		return isComplete; 
	}
	
	public void makeLineItem(ProductDescription desc, int quantity)	{
		lineItems.add( new SalesLineItem(desc, quantity));//컬렉션에 추가
	}
	
	public void taxinitial() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		taxCalculatorAdapter = ServiceFactory.getInstance().getTaxCalculatorAdapter();
		items = taxCalculatorAdapter.getTaxes(this);
	}
	public Money getTaxTotal() {
		for(TaxLineItem tax : items) {
			taxTotal = tax.getAmount();
		}
		return taxTotal;
	}
	
	public Money getTotal() {
		try {
			princingStrategy = PricingStrategyFactory.getInstance().getSalePrincingStrategy();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return princingStrategy.getTotal(this);
	}
	public Money getPreDiscountTotal() {
		return taxTotal; //Money Type
	}
	public Money getCurrentTotal() {//합계구하기
		Money currenttotal = new Money();//라인 아이템의 모든 부분합을 모아 더한다.
		Money subtotal = null;
		
		for(SalesLineItem lineItem : lineItems)
		{
			subtotal = lineItem.getSubtotal();
			currenttotal.add(subtotal);
		}
		return currenttotal;
	}
	
	public void makePayment(Money cashTendered) {
		payment = new Payment(cashTendered);
	}
}
