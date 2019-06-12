package pos.domainlayer;

public class PricingStrategyFactory {
	
	private static PricingStrategyFactory instance ;
	private ISalePricingStrategy strategy;
	public static synchronized PricingStrategyFactory getInstance() {
		if(instance == null) {
			instance = new PricingStrategyFactory();
		}
		return instance;
	}
	
	public ISalePricingStrategy getSalePrincingStrategy() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String className = System.getProperty("salepricingstrategy.class.name");
		strategy = (ISalePricingStrategy)Class.forName(className).newInstance();
		return strategy;
	}

}
