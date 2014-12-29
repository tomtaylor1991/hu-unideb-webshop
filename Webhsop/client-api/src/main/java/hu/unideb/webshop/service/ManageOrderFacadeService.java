package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;

import java.sql.Date;
import java.util.List;

public interface ManageOrderFacadeService {

	List<OrderDTO> getOrderList(int page, int size);

	List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);
	
	List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName, List<String> includedStatuses);	

	void createOrder(OrderDTO order);

	void removeOrder(OrderDTO order);

	void updateOrder(OrderDTO order);

	int getRowNumber();

	List<OrderDTO> getOrdersByStatus(String status);

	List<OrderDTO> getOrdersByPartner(PartnerDTO partner);

	boolean isOrderReadyStatus(OrderDTO order);

	List<Need> getPartnerOrderStatics(Date start, Date end);
	
	List<Need> getOrderStatusStatics();
}
