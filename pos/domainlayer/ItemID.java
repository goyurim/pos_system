package pos.domainlayer;

public class ItemID {
	String id;
	
	public ItemID(String id) {
		this.id = id;
	}
	
	public ItemID(int id) {
		this.id = String.valueOf(id);//임의의 유형을 변환 시켜줌
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}
	
	

}
