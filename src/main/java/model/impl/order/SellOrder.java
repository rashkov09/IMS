package model.impl.order;

import model.enums.order.OrderType;
import model.iface.Processable;

public class SellOrder extends BaseOrder implements Processable {

	public SellOrder() {
		super();
		this.setOrderType(OrderType.SELL);
	}

	@Override
	public void process() {

	}
}
