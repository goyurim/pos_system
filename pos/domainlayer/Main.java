package pos.domainlayer;

public class Main {
	public static void main(String[] args) {
		
		Store store = new Store();
		Register register = store.getRegister();
		
		Sale sale = register.makeNewSale();
		register.enterItem(new ItemID(100), 3);
		register.enterItem(new ItemID(200), 2);
		//register.enterItem(new ItemID(100), 3);
		
		register.endSale();
		
		//System.out.println("Total = "+ sale.getCurrentTotal().toString() );//레지스터로 getTotal하면 응집력이 떨어짐
		
		register.makePayment(new Money(10000));
		//System.out.println("Balance = "+sale.getBalance().toString());
	}

}
