package pos.domainlayer;

public class Money {

	int amount; //얼마를 나타내는 속성 값
	double amount2;
	int quantity;
	double MIN = 10000000;
	double MAX = -1;
	public Money(int amount) {
		this.amount = amount;
		
	}//금액 설정
	
	public Money() {
		//this.amount = 0;
		this(0);//인자가 같은 다른 생성자를 명시
		
	}//sale에서 사용하는 빈 생성자
	
	public Money(double d) {
		// TODO Auto-generated constructor stub
		this.amount2 = d;
	}

	public int getAmount() {
		return amount;
	}//설정된 금액 불러오기
	
	public double getAmount2() {
		return Math.round(amount2);
	}
	
	public Money times(int quantity) {
		// TODO Auto-generated method stub
		//머니 객체를 새로 만들어 반환
		return new Money (amount * quantity);
	}

	public void add(Money subtotal) {
		// TODO Auto-generated method stub
		amount += subtotal.getAmount();
	}

	public Money minus(Money total) {
		// TODO Auto-generated method stub
		return new Money(amount-total.getAmount2());
	}
	
	@Override
	public String toString() {
		return String.valueOf(amount);
	}

	public Money min(Money lowestTotal) {
		// TODO Auto-generated method stub
		if(this.getAmount2() < lowestTotal.getAmount()) {
			lowestTotal = this;
		}
		return lowestTotal;
	}

	public Money max(Money highestTotal) {
		// TODO Auto-generated method stub
		if(this.getAmount2() > highestTotal.getAmount()) {
			highestTotal = this;
		}
		return highestTotal;
	}

}
