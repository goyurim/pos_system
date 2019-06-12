package pos.domainlayer;

public class TaxMaster {
	//private int percentage;
	private Money taxtotal;
	private double calc;
	public Money calcTax(Sale s) {
		calc = Integer.parseInt(String.valueOf(s.getCurrentTotal()))*1.1;
		taxtotal = new Money(calc);
		return taxtotal;
	}
}
