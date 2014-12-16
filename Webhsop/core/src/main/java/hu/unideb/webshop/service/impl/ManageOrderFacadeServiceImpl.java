package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.RegistryStatus;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.OrderService;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageOrderFacadeService")
public class ManageOrderFacadeServiceImpl implements ManageOrderFacadeService {

	@Autowired
	OrderService orderService;

	@Override
	public List<OrderDTO> getOrderList(int page, int size) {
		return orderService.getOrderList(page, size);
	}

	@Override
	public void createOrder(OrderDTO order) {
		order.setId(orderService.createOrder(order));
	}

	@Override
	public void removeOrder(OrderDTO order) {
		orderService.removeOrder(order);
	}

	@Override
	public void updateOrder(OrderDTO order) {
		orderService.updateOrder(order);
	}

	@Override
	public List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		return orderService.getOrderList(page, size, sortField, sortOrder,
				filter, filterColumnName);
	}

	@Override
	public int getRowNumber() {
		
		return orderService.getRowNumber();
	}

	@Override
	public List<OrderDTO> getOrdersByStatus(String status) {
		
		return orderService.getOrdersByStatus(status); 
	}

	@Override

	public boolean isOrderReadyStatus(OrderDTO order) {
		
		return orderService.isOrderReadyStatus(order);
	}
	@Override
	public List<OrderDTO> getOrdersByPartner(PartnerDTO partner) {
		return orderService.getOrdersByPartner(partner);
	}

	@Override
	public List<Need> getPartnerOrderStatics(Date start, Date end) {
		
		return orderService.getPartnerOrderStatics(start, end);
	}

	@Override
	public List<Need> getOrderStatusStatics() {
		List<Need> ret = new LinkedList<Need>();
		for(String status:RegistryStatus.getStatuses()){
			ret.add(new Need(status, orderService.countByStatus(status)));
		}
		return ret;
	}
	
}
