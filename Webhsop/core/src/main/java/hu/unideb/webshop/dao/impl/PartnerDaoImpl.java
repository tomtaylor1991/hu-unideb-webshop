package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.PartnerDao;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.entity.Partner;

import org.springframework.beans.factory.annotation.Autowired;

public class PartnerDaoImpl implements BaseConvertDao<Partner, PartnerDTO> {

    @Autowired
    PartnerDao partnerDao;

    @Override
    public PartnerDTO toDto(Partner entity) {
        PartnerDTO ret = new PartnerDTO();

        ret.setId(entity.getId());
        ret.setModDate(entity.getModDate());
        ret.setModUserId(entity.getModUserId());
        ret.setRecDate(entity.getRecDate());
        ret.setRecUserId(entity.getRecUserId());
        ret.setName(entity.getName());
        ret.setAddress(entity.getAddress());
        ret.setType(entity.getType());
        return ret;
    }

    @Override
    public Partner toEntity(PartnerDTO dto, Partner entity) {
        Partner ret = entity;

        if (dto.getId() != null) {
            ret = partnerDao.findOne(dto.getId());
        } else if (dto.getId() == null || entity == null) {
            ret = new Partner();
            ret.setId(dto.getId());
            ret.setRecDate(dto.getRecDate());
            ret.setRecUserId(dto.getRecUserId());
        }

        ret.setModDate(dto.getModDate());
        ret.setModUserId(dto.getModUserId());
        ret.setName(dto.getName());
        ret.setAddress(dto.getAddress());
        ret.setType(dto.getType());
        return ret;
    }

}
