package pos.domainlayer;

public class SalesLineItem {

	private int quantity;
	private ProductDescription description;//saleLineItem은 description 객체를 전달받아 참조, 유지한다.
	
	public SalesLineItem(ProductDescription desc, int quantity)
	{
		this.description = desc;
		this.quantity= quantity;
	}
	
	public Money getSubtotal()//부분합계를 Money타입으로 반환
	{	//Money타입의 price에 수량을 곱하여야 한다.
		//곱하기 메서드 필요.
		return description.getPrice().times(quantity);
	}
}

