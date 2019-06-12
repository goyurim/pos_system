package pos.domainlayer;

import java.util.ArrayList;
import java.util.List;

public class TaxMasterAdapter implements ITaxCalculatorAdapter {
	private List<TaxLineItem> taxlineItems = new ArrayList<TaxLineItem>();
	private Money amount;
	private TaxMaster taxmaster = new TaxMaster();
	@Override
	public List<TaxLineItem> getTaxes(Sale s) {
		// TODO Auto-generated method stub
		amount = taxmaster.calcTax(s);
		taxlineItems.add(new TaxLineItem("taxmaster",10,amount));
		
		return taxlineItems;
	}
	
	

}
