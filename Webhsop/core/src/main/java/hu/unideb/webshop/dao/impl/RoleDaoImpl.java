package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.entity.Role;

public class RoleDaoImpl implements BaseConvertDao<Role, RoleDTO> {

    @Override
    public Role toEntity(RoleDTO dto, Role entity) {
        Role ret = entity;
        if (dto.getId() == null || entity == null) {
            ret = new Role();
            ret.setId(dto.getId());
            ret.setRecDate(dto.getRecDate());
            ret.setRecUserId(dto.getRecUserId());
        }
        ret.setModDate(dto.getModDate());
        ret.setModUserId(dto.getModUserId());
        ret.setRole(dto.getRole());
        return ret;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO ret = new RoleDTO();
        if (entity == null) {
            return null;
        }
        ret.setRole(entity.getRole());
        ret.setId(entity.getId());
        ret.setModDate(entity.getModDate());
        ret.setModUserId(entity.getModUserId());
        ret.setRecDate(entity.getRecDate());
        ret.setRecUserId(entity.getRecUserId());
        return ret;
    }

}
