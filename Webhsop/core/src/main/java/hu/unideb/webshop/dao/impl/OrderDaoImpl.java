package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.PartnerDao;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl implements BaseConvertDao<Order, OrderDTO> {

    @Autowired
    PartnerDao partnerDao;

    @Override
    public Order toEntity(OrderDTO dto, Order entity) {
        Order ret = entity;
        if (dto.getId() == null || entity == null) {
            ret = new Order();
            ret.setId(dto.getId());
            ret.setRecDate(dto.getRecDate());
            ret.setRecUserId(dto.getRecUserId());
        }
        ret.setId(dto.getId());
        ret.setModDate(dto.getModDate());
        ret.setModUserId(dto.getModUserId());
        ret.setDate(dto.getDate());
        ret.setName(dto.getName());
        ret.setStatus(dto.getStatus());
        ret.setPartnerId(dto.getPartnerDTO().getId());
        ret.setCostOfAll(dto.getCostOfAll());
        return ret;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO ret = new OrderDTO();
        ret.setId(entity.getId());
        ret.setDate(entity.getDate());
        ret.setName(entity.getName());

        ret.setStatus(entity.getStatus());
        if (entity.getPartnerId() != null && entity.getPartnerId() != 0) {
            ret.setPartnerDTO(partnerDao.toDto(partnerDao.findOne(entity.getPartnerId())));
        }
        ret.setModDate(entity.getModDate());
        ret.setRecDate(entity.getRecDate());
        ret.setRecUserId(entity.getRecUserId());
        ret.setModUserId(entity.getModUserId());
        ret.setId(entity.getId());
        ret.setStatus(entity.getStatus());
        ret.setCostOfAll(entity.getCostOfAll());
        return ret;
    }

}
