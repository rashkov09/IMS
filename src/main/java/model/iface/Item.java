package model.iface;

import java.math.BigDecimal;

public interface Item {

	String getItemDetails();

	BigDecimal calculateItemValue();

	String displayItemDescription();

}
