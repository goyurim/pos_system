package pos.domainlayer;

import java.util.Iterator;

public class CompositeBestForStorePricingStrategy extends CompositePricingStrategy{
	protected ISalePricingStrategy pdp = new PercentDiscountPricingStrategy();
	protected ISalePricingStrategy adp = new AbsoluteDiscountOverThresholdPricingStrategy();
	@Override
	public Money getTotal(Sale s) {
		// TODO Auto-generated method stub
		this.add(pdp);
		this.add(adp);
	
		Money highestTotal = new Money(Integer.MIN_VALUE);
		
		Iterator<ISalePricingStrategy> i = strategies.iterator();
		while(i.hasNext()) {
			ISalePricingStrategy strategy = i.next();
			
			Money total = strategy.getTotal(s);
			highestTotal = total.max(highestTotal);
		}
		return highestTotal;
	}

}
