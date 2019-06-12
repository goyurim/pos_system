package pos.domainlayer;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy{
	private float percentage = 10 ;
	
	@Override
	public Money getTotal(Sale s) {//10%���ε� �ݾ� ��ȯ
		// TODO Auto-generated method stub
		//���� �Ǳ� ���� �ݾ��� getPreDisxountTotal �޼ҵ�� �޾Ƽ� �ۼ������� ��� �� ��ȭ

		double total = s.getPreDiscountTotal().getAmount2() - (s.getPreDiscountTotal().getAmount2()*percentage/100);

		return new Money(total);
	}

}
