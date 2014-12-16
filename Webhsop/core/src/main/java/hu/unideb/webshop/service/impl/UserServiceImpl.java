package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.UserDao;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.entity.User;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
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

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDTO getUser(String login) {
        return userDao.toDto(userDao.findUserByLogin(login));
    }

    @Override
    public void save(UserDTO user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth);
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();

            user.setRecUserId(getUser(userDetails.getUsername()).getId() + "");
            user.setRecDate(new Date());
            user.setModUserId(getUser(userDetails.getUsername()).getId() + "");
            user.setModDate(new Date());
        } else {
            user.setRecDate(new Date());
            user.setRecUserId("self");
            user.setModDate(new Date());
            user.setModUserId("self");
        }
        userDao.save(userDao.toEntity(user, null));

    }

    @Override
    public void update(UserDTO user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth);
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            user.setModUserId(getUser(userDetails.getUsername()).getId() + "");
            user.setModDate(new Date());
        } else {
            user.setModDate(new Date());
            user.setModUserId("self");
        }
        userDao.save(userDao.toEntity(user, null));

    }

    @Override
    public List<UserDTO> getUserList(int page, int size, String sortField,
            int sortOrder, String filter, String filterColumnName) {
        Direction dir = sortOrder == 1 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                new org.springframework.data.domain.Sort.Order(dir, sortField)));
        List<UserDTO> ret = new ArrayList<UserDTO>(size);
        Page<User> entities;

        if (filter.length() != 0 && filterColumnName.equals("login")) {
            entities = userDao.findByLoginStartsWith(filter, pageRequest);
        } else {
            entities = userDao.findAll(pageRequest);
        }

        if (entities != null && entities.getSize() > 0) {
            List<User> contents = entities.getContent();
            for (User m : contents) {
                ret.add(userDao.toDto(m));
            }
        }
        return ret;
    }

    @Override
    public int getRowNumber() {
        return (int) userDao.count();
    }

}
