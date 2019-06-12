package pos.domainlayer;

import java.util.ArrayList;
import java.util.List;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter {
	private List<TaxLineItem> taxlineItems = new ArrayList<TaxLineItem>();
	private Money amount;
	private GoodAsGoldTax gagt = new GoodAsGoldTax();
	@Override
	public List<TaxLineItem> getTaxes(Sale s) {
		// TODO Auto-generated method stub
		amount = gagt.getTax(s);
		taxlineItems.add(new TaxLineItem("goodAsGoldTaxPro",20,amount));
		return taxlineItems;
		
	}

}
