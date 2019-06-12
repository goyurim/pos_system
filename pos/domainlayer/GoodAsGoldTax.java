package pos.domainlayer;

public class GoodAsGoldTax {
	
	private Money taxtotal;
	private double calc;
	
	public Money getTax(Sale s) {
		calc = Integer.parseInt(String.valueOf(s.getCurrentTotal()))*1.2;
		taxtotal = new Money(calc);
		return taxtotal;
	}
}
