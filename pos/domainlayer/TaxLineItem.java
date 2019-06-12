package pos.domainlayer;

public class TaxLineItem {
	private String description;
	private int percentage;
	private Money amount;
	
	public TaxLineItem(String descriptrion, int percentage, Money amount) {
		this.description = description;
		this.percentage = percentage;
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}

	public int getPercentage() {
		return percentage;
	}

	public Money getAmount() {
		return amount;
	}
	
	
}
