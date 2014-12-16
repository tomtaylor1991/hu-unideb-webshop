package hu.unideb.webshop.login;

import hu.unideb.webshop.dto.RoleDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManageUserFacadeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ManageUserFacadeService manageUserFacadeService;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {

		UserDTO domainUser = manageUserFacadeService.getUser(login);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(
				domainUser.getLogin(), domainUser.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(domainUser.getRoles()));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(
			List<RoleDTO> roles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
		return authList;
	}

	public List<String> getRoles(List<RoleDTO> roleDTOs) {

		List<String> roles = new ArrayList<String>();
		for (RoleDTO roleDTO : roleDTOs) {
			roles.add(roleDTO.getRole());
		}
		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}