package pos.domainlayer;

public class Money {

	int amount; //�󸶸� ��Ÿ���� �Ӽ� ��
	double amount2;
	int quantity;
	double MIN = 10000000;
	double MAX = -1;
	public Money(int amount) {
		this.amount = amount;
		
	}//�ݾ� ����
	
	public Money() {
		//this.amount = 0;
		this(0);//���ڰ� ���� �ٸ� �����ڸ� ���
		
	}//sale���� ����ϴ� �� ������
	
	public Money(double d) {
		// TODO Auto-generated constructor stub
		this.amount2 = d;
	}

	public int getAmount() {
		return amount;
	}//������ �ݾ� �ҷ�����
	
	public double getAmount2() {
		return Math.round(amount2);
	}
	
	public Money times(int quantity) {
		// TODO Auto-generated method stub
		//�Ӵ� ��ü�� ���� ����� ��ȯ
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
