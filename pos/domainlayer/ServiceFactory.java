package pos.domainlayer;

public class ServiceFactory {
	private static ServiceFactory instance;
	private ITaxCalculatorAdapter taxCalculatorAdapter;
	public static synchronized ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	public ITaxCalculatorAdapter getTaxCalculatorAdapter() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String className = System.getProperty("taxcalculator.class.name");
		taxCalculatorAdapter = (ITaxCalculatorAdapter)Class.forName(className).newInstance();
		return taxCalculatorAdapter;
	}
}
