package pos.domainlayer;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy{
	private float percentage = 10 ;
	
	@Override
	public Money getTotal(Sale s) {//10%할인된 금액 반환
		// TODO Auto-generated method stub
		//할인 되기 전의 금액을 getPreDisxountTotal 메소드로 받아서 퍼센테이지 계산 후 반화

		double total = s.getPreDiscountTotal().getAmount2() - (s.getPreDiscountTotal().getAmount2()*percentage/100);

		return new Money(total);
	}

}
