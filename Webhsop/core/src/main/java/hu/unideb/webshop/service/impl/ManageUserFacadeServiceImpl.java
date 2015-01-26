package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageUserFacadeService;
import hu.unideb.webshop.service.RoleService;
import hu.unideb.webshop.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageUserFacadeService")
public class ManageUserFacadeServiceImpl implements ManageUserFacadeService{

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@Override
	public UserDTO getUser(String login) {
		return userService.getUser(login);
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		return userService.save(user);
		
	}
	
	@Override
	public List<RoleDTO> getRoles(){
		return roleService.getRoles();
	}
	
	@Override
	public int getRowNumber() {
		
		return userService.getRowNumber();
	}
	
	@Override
	public RoleDTO getRole(String role) {
		
		return roleService.getRoleByRole(role);
	}

	@Override
	public void updateUser(UserDTO user) {
		userService.save(user);
		
	}

	@Override
	public List<UserDTO> getUserList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		return userService.getUserList(page, size, sortField, sortOrder,
				filter, filterColumnName);
	}
}
