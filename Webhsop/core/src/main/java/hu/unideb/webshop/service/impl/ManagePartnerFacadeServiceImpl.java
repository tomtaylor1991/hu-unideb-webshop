package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dto.PartnerDTO;
import hu.unideb.webshop.dto.UserDTO;
import hu.unideb.webshop.service.ManagePartnerFacadeService;
import hu.unideb.webshop.service.PartnerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managePartnerFacadeService")
public class ManagePartnerFacadeServiceImpl implements ManagePartnerFacadeService {
	
	@Autowired
	PartnerService partnerService;
	
	@Override
	public List<PartnerDTO> getPartnerList(int page, int size) {
		return partnerService.getPartnerList(page, size);
	}
	
	@Override
	public void createPartner(PartnerDTO partner) {
		partnerService.createPartner(partner);
	}
	
	@Override
	public void removePartner(PartnerDTO partner) {
		partnerService.removePartner(partner);
	}
	
	@Override
	public void updatePartner(PartnerDTO partner) {
		partnerService.updatePartner(partner);
	}

	@Override
	public PartnerDTO getPartnerByName(String name) {
		return partnerService.getPartnerByName(name);
	}

	@Override
	public PartnerDTO findPartnerByUser(UserDTO user) {	
		return partnerService.findPartnerByUser(user);
	}

}
