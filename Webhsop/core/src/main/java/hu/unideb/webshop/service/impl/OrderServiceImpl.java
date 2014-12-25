package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.PartnerDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.service.OrderService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	RegistryDao registryDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	PartnerDao partnerDao;

	@Autowired
	UserService userService;

	@Override
	public List<OrderDTO> getOrderList(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;

		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));

		List<OrderDTO> ret = new ArrayList<OrderDTO>(size);
		Page<Order> entities;
		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = orderDao.findByNameStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("status")) {
			entities = orderDao.findByStatusStartsWith(filter, pageRequest);
		} else {
			entities = orderDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Order> contents = entities.getContent();
			for (Order c : contents) {
				ret.add(orderDao.toDto(c));
			}
		}
		return ret;
	}

	@Override
	public long createOrder(OrderDTO order) {
		Order entity = orderDao.toEntity(order, null);
		orderDao.save(entity);
		return entity.getId();
	}

	@Override
	public void updateOrder(OrderDTO order) {
		orderDao.save(orderDao.toEntity(order, null));
	}

	@Override
	public void removeOrder(OrderDTO order) {
		orderDao.delete(orderDao.toEntity(order, null));
	}

	@Override
	public int getRowNumber() {
		return orderDao.countRowNum();
	}

	@Override
	public List<OrderDTO> getOrdersByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getOrdersByPartner(PartnerDTO partner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOrderReadyStatus(OrderDTO order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Need> getPartnerOrderStatics(java.sql.Date start,
			java.sql.Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByStatus(String status) {
		// TODO Auto-generated method stub
		return 0;
	}

}
