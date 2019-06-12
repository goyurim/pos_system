package pos.domainlayer;

import java.util.List;

public interface ITaxCalculatorAdapter {
	List<TaxLineItem> getTaxes(Sale s);
}
