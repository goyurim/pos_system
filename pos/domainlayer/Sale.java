package pos.domainlayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
	private List<SalesLineItem> lineItems =  new ArrayList<SalesLineItem>();
	
	ITaxCalculatorAdapter taxCalculatorAdapter;
	ISalePricingStrategy princingStrategy;
	List<TaxLineItem> items = new ArrayList<TaxLineItem>();
	private Date date = new Date();//�Ǹ� ��¥
	private boolean isComplete = false;//�Ǹ� �Ϸ� Ȯ��
	private Payment payment;//���� ó���� ���� ����
	private Money taxTotal;
	private Money total;
	public Money getBalance()	{
		return payment.getAmount().minus(getTotal());//�ܾ� ��ȯ�� ���� minus
	}
	
	public void becomeComplete(){ 
		isComplete = true; 
	}
	
	public boolean isComplete() {  
		return isComplete; 
	}
	
	public void makeLineItem(ProductDescription desc, int quantity)	{
		lineItems.add( new SalesLineItem(desc, quantity));//�÷��ǿ� �߰�
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
	public Money getCurrentTotal() {//�հ豸�ϱ�
		Money currenttotal = new Money();//���� �������� ��� �κ����� ��� ���Ѵ�.
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
