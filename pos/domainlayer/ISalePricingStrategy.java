package pos.domainlayer;

public interface ISalePricingStrategy {
	Money getTotal(Sale s);
}
