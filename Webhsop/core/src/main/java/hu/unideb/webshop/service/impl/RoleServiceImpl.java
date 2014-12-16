package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.RoleDao;
import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.entity.Role;
import hu.unideb.webshop.service.RoleService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDAO;

    public Role getRole(Long id) {
        return roleDAO.findOne(id);
    }

    @Override
    public List<RoleDTO> getRoles() {
        List<RoleDTO> ret = new ArrayList<>();
        for (Role role : roleDAO.findAll()) {
            ret.add(roleDAO.toDto(role));
        }
        return ret;
    }

    @Override
    public RoleDTO getRoleByRole(String role) {
        return roleDAO.toDto(roleDAO.findRoleByRole(role));
    }

}
