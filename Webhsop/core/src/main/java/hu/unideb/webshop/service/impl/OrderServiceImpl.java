package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.PartnerDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dto.Need;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.entity.Order;
import hu.unideb.webshop.entity.Partner;
import hu.unideb.webshop.service.OrderService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(propagation = Propagation.REQUIRED)
	public List<OrderDTO> getOrderList(int page, int size) {
		List<OrderDTO> ret = new ArrayList<OrderDTO>(size);
		Page<Order> entities = orderDao.findAll(new PageRequest(page, size));
		if (entities != null && entities.getSize() > 0) {
			List<Order> contents = entities.getContent();
			for (Order order : contents) {
				ret.add(orderDao.toDto(order));
			}
		}
		return ret;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long createOrder(OrderDTO order) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			order.setRecUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			order.setRecDate(new Date());
			order.setModUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			order.setModDate(new Date());
		}
		Order entity = orderDao.toEntity(order, null);
		orderDao.save(entity);
		order = orderDao.toDto(entity);
		return orderDao.toDto(entity).getId();
	}

	@Override
	public void updateOrder(OrderDTO order) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			order.setModUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			order.setModDate(new Date());
		}
		orderDao.save(orderDao.toEntity(order, null));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removeOrder(OrderDTO order) {
		orderDao.delete(orderDao.toEntity(order, null));
	}

	@Override
	public List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Sort.Order(dir, sortField)));
		List<OrderDTO> ret = new ArrayList<OrderDTO>(size);
		Page<Order> entities;
		if (filter.length() != 0 && filterColumnName.equals("status")) {
			entities = orderDao.findByStatusStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = orderDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = orderDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Order> contents = entities.getContent();
			for (Order m : contents) {
				ret.add(orderDao.toDto(m));
			}
		}
		return ret;
	}

	@Override
	public int getRowNumber() {
		return orderDao.countById();
	}

	@Override
	public List<OrderDTO> getOrdersByStatus(String status) {
		List<Order> entities = orderDao.findByStatus(status);
		List<OrderDTO> ret = new LinkedList<OrderDTO>();
		if (entities != null && entities.size() > 0) {
			for (Order o : entities) {
				ret.add(orderDao.toDto(o));
			}
		}
		return ret;
	}

	@Override
	public boolean isOrderReadyStatus(OrderDTO order) {
		boolean a = registryDao.isOrderReadyByBeers(order.getId()) > 0 ? false
				: true;
		boolean b = registryDao.isOrderReady(order.getId()) > 0 ? false : true;
		return a && b;
	}

	@Override
	public List<OrderDTO> getOrdersByPartner(PartnerDTO partner) {

		List<Order> orders = orderDao.findByPartnerId(partner.getId());
		List<OrderDTO> ret = new LinkedList<OrderDTO>();

		for (Order ord : orders) {
			ret.add(orderDao.toDto(ord));
		}
		return ret;

	}

	@Override
	public List<Need> getPartnerOrderStatics(java.sql.Date start,
			java.sql.Date end) {
		List<Need> ret = new LinkedList<Need>();
		Iterable<Partner> partners = partnerDao.findAll();
		for (Partner p : partners) {
			Integer tmp = registryDao.getOrderNumByPartner(p.getId(), start,
					end);
			int n = tmp == null ? 0 : tmp;
			ret.add(new Need(p.getName(), n));
		}

		return ret;
	}

	@Override
	public int countByStatus(String status) {
		Integer ret = orderDao.countByStatus(status);
		return ret == null ? 0 : ret;
	}
}
