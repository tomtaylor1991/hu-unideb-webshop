package hu.unideb.webshop.service;

import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;

import java.sql.Date;
import java.util.List;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Gets the order list.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the order list
	 */
	List<OrderDTO> getOrderList(int page, int size);
	
	/**
	 * Gets the order list.
	 *
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filter the filter
	 * @param filterColumnName the filter column name
	 * @return the order list
	 */
	List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName);

	/**
	 * Creates the order.
	 *
	 * @param beer the beer
	 * @return the long
	 */
	long createOrder(OrderDTO beer);
	
	/**
	 * Update order.
	 *
	 * @param beer the beer
	 */
	void updateOrder(OrderDTO beer);

	/**
	 * Removes the order.
	 *
	 * @param beer the beer
	 */
	void removeOrder(OrderDTO beer);
	
	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	int getRowNumber();
	
	/**
	 * Gets the orders by status.
	 *
	 * @param status the status
	 * @return the orders by status
	 */
	List<OrderDTO> getOrdersByStatus(String status);
	
	/**
	 * Gets the orders by partner.
	 *
	 * @param partner the partner
	 * @return the orders by partner
	 */
	List<OrderDTO> getOrdersByPartner(PartnerDTO partner);

	/**
	 * Checks if is order ready status.
	 *
	 * @param order the order
	 * @return true, if is order ready status
	 */
	boolean isOrderReadyStatus(OrderDTO order);
	
	/**
	 * Gets the partner order statics.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the partner order statics
	 */
	List<Need> getPartnerOrderStatics(Date start, Date end);
	
	int countByStatus(String status);
}
