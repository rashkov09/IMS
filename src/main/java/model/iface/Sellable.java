package model.iface;

import java.math.BigDecimal;

public interface Sellable {

	BigDecimal setItemPrice(BigDecimal price);

	BigDecimal getItemPrice();

}
