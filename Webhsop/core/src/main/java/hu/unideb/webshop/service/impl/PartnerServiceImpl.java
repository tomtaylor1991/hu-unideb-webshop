package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.PartnerDao;
import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.entity.Partner;
import hu.unideb.webshop.service.PartnerService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	PartnerDao partnerDao;

	@Autowired
	UserService userService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<PartnerDTO> getPartnerList(int page, int size) {

		List<PartnerDTO> ret = new ArrayList<PartnerDTO>(size);
		Page<Partner> entities = partnerDao
				.findAll(new PageRequest(page, size));

		if (entities != null && entities.getSize() > 0) {
			List<Partner> content = entities.getContent();

			for (Partner partner : content) {
				ret.add(partnerDao.toDto(partner));
			}
		}

		return ret;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createPartner(PartnerDTO partner) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			partner.setRecUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			partner.setRecDate(new Date());
			partner.setModUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			partner.setModDate(new Date());
		}
		Partner partnerentity = partnerDao.toEntity(partner, null);
		partnerentity = partnerDao.save(partnerentity);
		partner.setId(partnerDao.toDto(partnerentity).getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void removePartner(PartnerDTO partner) {
		partnerDao.delete(partnerDao.toEntity(partner, null));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePartner(PartnerDTO partner) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			partner.setModUserId(userService.getUser(userDetails.getUsername())
					.getId() + "");
			partner.setModDate(new Date());
		}
		partnerDao.save(partnerDao.toEntity(partner, null));
	}

	@Override
	public PartnerDTO getPartnerByName(String name) {
		Page<Partner> entities = partnerDao.findAll(new PageRequest(0, 100));
		if (entities != null && entities.getSize() > 0) {
			List<Partner> contents = entities.getContent();
			for (Partner partner : contents) {
				if (partner.getName().equals(name)) {
					return partnerDao.toDto(partner);
				}
			}
		}
		return null;
	}

}
