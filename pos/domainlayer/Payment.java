package pos.domainlayer;
//종속성이 가장 낮음
public class Payment {
	private Money amount;

	public Payment(Money cachTendered) {
		amount = cachTendered;
	}

	public Money getAmount() {
		return amount;
	}
}

