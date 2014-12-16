package hu.unideb.webshop.dao.impl;

import hu.unideb.webshop.dao.BaseConvertDao;
import hu.unideb.webshop.dao.RoleDao;
import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.entity.User;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements BaseConvertDao<User, UserDTO> {

	@Autowired
	private RoleDao roleDao;

	@Override
	public User toEntity(UserDTO dto, User entity) {
		User ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new User();
			ret.setId(dto.getId());
			ret.setRecDate(dto.getRecDate());
			ret.setRecUserId(dto.getRecUserId());
		}
		ret.setId(dto.getId());
		ret.setModDate(dto.getModDate());
		ret.setModUserId(dto.getModUserId());
		ret.setLogin(dto.getLogin());
		ret.setPassword(dto.getPassword());
		if (ret.getRoleIds() == null) {
			ret.setRoleIds(new ArrayList<Long>());
		}
		for (RoleDTO roleDTO : dto.getRoles()) {
			ret.getRoleIds().add(roleDTO.getId());
		}
		return ret;
	}

	@Override
	public UserDTO toDto(User entity) {
		UserDTO ret = new UserDTO();
		if (entity == null) {
			return null;
		}
		ret.setId(entity.getId());
		ret.setLogin(entity.getLogin());
		ret.setPassword(entity.getPassword());
		ret.setId(entity.getId());
		ret.setModDate(entity.getModDate());
		ret.setModUserId(entity.getModUserId());
		ret.setRecDate(entity.getRecDate());
		ret.setRecUserId(entity.getRecUserId());
		for (Long id : entity.getRoleIds()) {
			ret.getRoles().add(roleDao.toDto(roleDao.findOne(id)));
		}
		return ret;
	}

}
