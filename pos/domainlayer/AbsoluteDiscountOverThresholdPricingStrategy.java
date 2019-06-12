package pos.domainlayer;

public class AbsoluteDiscountOverThresholdPricingStrategy implements ISalePricingStrategy {
	private Money discount;
	
	private Money threshold = new Money(20000);
	
	@Override
	public Money getTotal(Sale s) {//20000만원 초과시 할인 가능
		// TODO Auto-generated method stub
		if(s.getPreDiscountTotal().getAmount2() > threshold.getAmount()) {
			discount = new Money(s.getPreDiscountTotal().getAmount2()*0.2);
			Money total = new Money(s.getPreDiscountTotal().getAmount2() - discount.getAmount2());
			return total;
		}else {
			return s.getPreDiscountTotal();
		}
	}

}
