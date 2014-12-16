package hu.unideb.webshop.service.impl;

import hu.unideb.webshop.dao.MaterialDao;
import hu.unideb.webshop.dao.OrderDao;
import hu.unideb.webshop.dao.RegistryDao;
import hu.unideb.webshop.dao.WarehouseDao;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.MaterialDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.WarehouseDTO;
import hu.unideb.webshop.entity.Beer;
import hu.unideb.webshop.entity.Registry;
import hu.unideb.webshop.service.RegistryService;
import hu.unideb.webshop.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("registryService")
@Transactional(propagation = Propagation.REQUIRED)
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    RegistryDao registryDao;

    @Autowired
    WarehouseDao warehouseDao;

    @Autowired
    MaterialDao materialDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserService userService;

    @Override
    public void saveRegistrys(List<RegistryDTO> registry) {
        for (RegistryDTO r : registry) {
            Authentication auth = SecurityContextHolder.getContext()
                    .getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                r.setRecUserId(userService.getUser(userDetails.getUsername())
                        .getId() + "");
                r.setRecDate(new Date());
                r.setModUserId(userService.getUser(userDetails.getUsername())
                        .getId() + "");
                r.setModDate(new Date());
            }
            registryDao.save(registryDao.toEntity(r, null));
        }
    }

    @Override
    public List<RegistryDTO> getRegistrysByMaterial(WarehouseDTO warehouse) {
        List<Registry> registry = registryDao
                .findByWarehouseAndMaterialNotNullAndStatus(
                        warehouseDao.toEntity(warehouse, null), "FREE");
        List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
        for (Registry r : registry) {
            ret.add(registryDao.toDto(r));
        }
        //System.out.println("Registry: " + ret.size());
        return ret;
    }

    @Override
    public List<RegistryDTO> findByMaterial(MaterialDTO material) {
        List<Registry> registry = registryDao.findByMaterial(materialDao
                .toEntity(material, null));
        List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
        for (Registry r : registry) {
            ret.add(registryDao.toDto(r));
        }
        return ret;
    }

    @Override
    public List<RegistryDTO> findByOrder(OrderDTO order) {

        List<Registry> registry = registryDao.findByOrder(orderDao.toEntity(
                order, null));
        List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
        for (Registry r : registry) {
            if (r.getQuantity() > 0 && r.getBeer() != null
                    && r.getBeer().getName() != null
                    && r.getBeer().getName() != "") {
                ret.add(registryDao.toDto(r));
            }
        }
        return ret;

    }

    @Override
    public List<RegistryDTO> findByStatus(String status) {
        List<Registry> registry = registryDao.findByStatus(status);
        List<RegistryDTO> ret = new LinkedList<>();
        for (Registry r : registry) {
            ret.add(registryDao.toDto(r));
        }
        return ret;
    }

    @Override
    public void updateRegistry(RegistryDTO r) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            r.setModUserId(userService.getUser(userDetails.getUsername())
                    .getId() + "");
            r.setModDate(new Date());
        }
        registryDao.save(registryDao.toEntity(r, null));
    }

    @Override
    public List<RegistryDTO> getRegistrySortedList(int page, int size,
            String sortField, int sortOrder, String filter,
            String filterColumnName) {
        Direction dir = sortOrder == 1 ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        PageRequest pageRequest = new PageRequest(page, size, new Sort(
                new Order(dir, sortField)));
        List<RegistryDTO> ret = new ArrayList<RegistryDTO>(size);
        Page<Registry> entities;
        if (filter.length() != 0 && filterColumnName.equals("status")) {
            entities = registryDao.findByStatusStartsWith(filter, pageRequest);
        } else {
            entities = registryDao.findAll(pageRequest);
        }

        if (entities != null && entities.getSize() > 0) {
            List<Registry> contents = entities.getContent();
            for (Registry m : contents) {
                ret.add(registryDao.toDto(m));
            }
        }
        return ret;
    }

    @Override
    public int sumQuantityByMaterial(MaterialDTO material) {
        if (material != null) {
            Integer sum = registryDao.sumByQuantity(material.getId());
            if (sum == null) {
                return 0;
            } else {
                return sum;
            }
        } else {
            return 0;
        }
    }

    @Override
    public List<Registry> findByStatusAndBeerAndOrder(String status, Beer beer,
            hu.unideb.webshop.entity.Order order) {
        return registryDao.findByStatusAndBeerAndOrder(status, beer, order);
    }

    @Override
    public boolean keepMaterialForOrder(OrderDTO order, MaterialDTO material,
            int quantity) {
        List<Registry> materialRegistrys = registryDao
                .findByStatusAndMaterialAndOrder("FREE",
                        materialDao.toEntity(material, null), null);
        int ok = 0;
        for (Registry currentEntity : materialRegistrys) {
            int currentQuantity = currentEntity.getQuantity();
            if (ok + currentQuantity <= quantity) {
                currentEntity.setStatus("READY");
                currentEntity.setOrder(orderDao.toEntity(order, null));
                registryDao.save(currentEntity);
            } else {
                RegistryDTO newRegistry = new RegistryDTO();
                newRegistry.setMaterial(material);
                //newRegistry.setOriginalQuantity(ok + currentQuantity - quantity);
                newRegistry.setQuantity(ok + currentQuantity - quantity);
                newRegistry.setStatus("FREE");
                newRegistry.setWarehouse(warehouseDao.toDto(currentEntity
                        .getWarehouse()));
                List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
                ret.add(newRegistry);
                saveRegistrys(ret);
                /**
                 *
                 */
                currentEntity.setStatus("READY");
                currentEntity.setQuantity(quantity - ok);
                currentEntity.setOrder(orderDao.toEntity(order, null));

                registryDao.save(currentEntity);
                /**
                 *
                 */
                return true;
            }
            ok += currentQuantity;
        }
        return false;
    }

    @Override
    public boolean createBeerNeedForOrder(OrderDTO order, BeerDTO beer,
            int quantity) {
        RegistryDTO newRegistry = new RegistryDTO();
        newRegistry.setBeer(beer);
        newRegistry.setQuantity(quantity);
        newRegistry.setOrder(order);
        newRegistry.setStatus("NEED");
        List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
        ret.add(newRegistry);
        saveRegistrys(ret);
        return true;
    }

    @Override
    public List<RegistryDTO> getRegistrysByBeer(WarehouseDTO warehouse) {
        List<Registry> registry = registryDao
                .findByWarehouseAndBeerNotNull(warehouseDao.toEntity(warehouse,
                                null));
        List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
        for (Registry r : registry) {
            ret.add(registryDao.toDto(r));
        }
        //System.out.println("Registry: " + ret.size());
        return ret;
    }

    @Override
    public void deleteRegistry(RegistryDTO registry) {
        registryDao.delete(registryDao.toEntity(registry, null));
    }

    @Override
    public List<RegistryDTO> findByStatusAndOrder(String status, OrderDTO order) {
        List<Registry> registry = registryDao.findByStatusAndOrder(status, orderDao.toEntity(order, null));
        List<RegistryDTO> ret = new LinkedList<>();
        for (Registry r : registry) {
            ret.add(registryDao.toDto(r));
        }
        return ret;
    }

}
