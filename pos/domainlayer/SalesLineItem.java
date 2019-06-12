package pos.domainlayer;

public class SalesLineItem {

	private int quantity;
	private ProductDescription description;//saleLineItem�� description ��ü�� ���޹޾� ����, �����Ѵ�.
	
	public SalesLineItem(ProductDescription desc, int quantity)
	{
		this.description = desc;
		this.quantity= quantity;
	}
	
	public Money getSubtotal()//�κ��հ踦 MoneyŸ������ ��ȯ
	{	//MoneyŸ���� price�� ������ ���Ͽ��� �Ѵ�.
		//���ϱ� �޼��� �ʿ�.
		return description.getPrice().times(quantity);
	}
}

