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

	@Override
	public List<OrderDTO> getOrderList(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getOrderList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long createOrder(OrderDTO beer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateOrder(OrderDTO beer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOrder(OrderDTO beer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRowNumber() {
		// TODO Auto-generated method stub
		return 0;
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
