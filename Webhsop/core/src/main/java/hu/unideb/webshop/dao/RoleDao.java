package hu.unideb.webshop.dao;

import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.entity.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long>, BaseConvertDao<Role, RoleDTO> {

    Role findRoleByRole(String role);
}
