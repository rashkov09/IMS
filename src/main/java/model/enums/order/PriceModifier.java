package model.enums.order;

import java.math.BigDecimal;

public enum PriceModifier {
	ELECTRONICS(BigDecimal.valueOf(0.80)),
	FOOD_GROCERIES(BigDecimal.valueOf(0.60)),
	CLOTHING_APPAREL(BigDecimal.valueOf(0.75)),
	FURNITURE(BigDecimal.valueOf(0.85));

	private final BigDecimal priceModifier;

	PriceModifier(BigDecimal modifier) {
		this.priceModifier = modifier;
	}

	public BigDecimal getPriceModifier() {
		return priceModifier;
	}
}
