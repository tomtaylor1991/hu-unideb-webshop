package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.BeerDao;
import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.ComponentDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RecipeDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO.Need;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.service.ComponentService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;
import hu.unideb.webshop.service.RecipeService;
import hu.unideb.webshop.service.RegistryService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("manageRegistryFacadeService")
public class ManageRegistryFacadeServiceImpl implements
        ManageRegistryFacadeService {

    @Autowired
    RegistryService registryService;

    @Autowired
    ComponentService componentService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    BeerDao beerDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    RegistryDao registryDao;

    @Override
    public double costOfMaterial(RegistryDTO registryDTO) {
        MaterialDTO materialDTO = registryDTO.getMaterial();
        return materialDTO.getCost() * registryDTO.getQuantity() * -1;
    }

    @Override
    public void saveRegistrys(List<RegistryDTO> registry) {
        registryService.saveRegistrys(registry);
    }

    @Override
    public List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse) {
        return registryService.getRegistrysByMaterial(warehouse);
    }

    @Override
    public List<RegistryDTO> findByMaterial(MaterialDTO material) {
        return registryService.findByMaterial(material);
    }

    @Override
    public List<RegistryDTO> findByOrder(OrderDTO order) {
        return registryService.findByOrder(order);
    }

    @Override
    public List<RegistryDTO> findByStatus(String status) {
        return registryService.findByStatus(status);
    }

    @Override
    public void updateRegistry(RegistryDTO registry) {
        registryService.updateRegistry(registry);
    }

    @Override
    public List<RegistryDTO> getRegistrySortedList(int page, int size,
            String sortField, int sortOrder, String filter,
            String filterColumnName) {
        return registryService.getRegistrySortedList(page, size, sortField,
                sortOrder, filter, filterColumnName);
    }

    @Override
    public LeaderTestInfoDTO isOrderCanBeServe(OrderDTO order) {
		// kell a component lista

		// lekerderzzuk az orderhez tartozo registry-ket, amik a rendelest
        // reprezentaljak
        LeaderTestInfoDTO ret = new LeaderTestInfoDTO();
        List<RegistryDTO> registryList = registryService.findByOrder(order);
        // veigimegyunk egyessevel a rendeleseken
        List<Need> needed = new LinkedList<LeaderTestInfoDTO.Need>();
        List<Need> all = new LinkedList<LeaderTestInfoDTO.Need>();

        for (RegistryDTO r : registryList) {
            if (r == null) {
                continue;
            }
            if ("READY".equals(r.getStatus())) {
                continue;
            }
            /**
             * 1. van elég késztermék?
             */
            List<Registry> freeSuccessBeers = registryService
                    .findByStatusAndBeerAndOrder("FREE",
                            beerDao.toEntity(r.getBeer(), null), null);
            //System.out.println("freeSuccessBeers " + freeSuccessBeers);
            if (freeSuccessBeers.size() > 0) {
                ret.setFreeSuccessBeers(true);
            }
            List<RegistryDTO> freeSuccessBeersDTO = new LinkedList<RegistryDTO>();
            for (Registry entity : freeSuccessBeers) {
                freeSuccessBeersDTO.add(registryDao.toDto(entity));
            }
            ret.setFreeSuccessBeers(freeSuccessBeersDTO);
            /**
             * 
             */

            RecipeDTO recipe = recipeService.getRecipeByBeer(r.getBeer());
            if (recipe == null) {
                continue;
            }
            List<ComponentDTO> components = componentService
                    .getComponentList(recipe);
            if (components == null || components.size() == 0) {
                return ret;
            } else {

				// vegig kell menni, hogy adott mennyisegben van -e adott
                // component
                // raktaron
                for (ComponentDTO c : components) {
                    /*System.out.println("test for component: "
                     + c.getMaterialDTO().getMaterialName() + " need: "
                     + c.getQuantity() * r.getQuantity());*/
                    int sum = registryService.sumQuantityByMaterial(c
                            .getMaterialDTO());
                    LeaderTestInfoDTO.Need n = new Need(c.getMaterialDTO(),
                            c.getQuantity() * r.getQuantity(), sum);
                    if (sum < c.getQuantity() * r.getQuantity()) {
                        //System.out.println("AADD:" + n);
                        needed.add(n);
                    }
                    all.add(n);
                }
                // /

            }
        }
        if (needed.size() > 0) {
            //System.out.println("NeeDeed: " + needed);
            ret.setNeeded(needed);
        }
        // /
        ret.setAllMaterial(all);

        return ret;
    }

    @Override
    public boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
            int quantity) {

        return registryService.keepMaterialForOrder(order, material, quantity);
    }

    @Override
    public boolean createBeerNeedForOrder(OrderDTO order, BeerDTO beer,
            int quantity) {

        return registryService.createBeerNeedForOrder(order, beer, quantity);
    }

    @Override
    public List<RegistryDTO> getRegistrysByBeer(WarehouseDTO warehouse) {

        return registryService.getRegistrysByBeer(warehouse);
    }

    @Override
    public void deleteRegistry(RegistryDTO registry) {
        registryService.deleteRegistry(registry);
    }

    @Override
    public List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order) {

        return registryService.findByStatusAndOrder(status, order);
    }

}
