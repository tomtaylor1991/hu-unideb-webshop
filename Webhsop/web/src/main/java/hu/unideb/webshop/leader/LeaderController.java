package hu.unideb.webshop.leader;

import hu.unideb.webshop.LocaleSwitcher;
import hu.unideb.webshop.Status;
import hu.unideb.webshop.dto.BeerDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO;
import hu.unideb.webshop.dto.OrderDTO;
import hu.unideb.webshop.dto.RegistryDTO;
import hu.unideb.webshop.dto.LeaderTestInfoDTO.BeerList;
import hu.unideb.webshop.dto.LeaderTestInfoDTO.Need;
import hu.unideb.webshop.service.ManageOrderFacadeService;
import hu.unideb.webshop.service.ManageRegistryFacadeService;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

/**
 * Controller for the leader site.
 */
@ViewScoped
@ManagedBean(name = "leaderController")
public class LeaderController implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The manage order facade service.
	 */
	@ManagedProperty(value = "#{manageOrderFacadeService}")
	private ManageOrderFacadeService manageOrderFacadeService;

	/**
	 * The manage registry facade service.
	 */
	@ManagedProperty(value = "#{manageRegistryFacadeService}")
	private ManageRegistryFacadeService manageRegistryFacadeService;

	/**
	 * The is ready and transport.
	 */
	private boolean isReadyAndTransport = false;

	/**
	 * The selected order.
	 */
	private OrderDTO selectedOrder;

	/**
	 * The registry list.
	 */
	List<RegistryDTO> registryList;

	/**
	 * The is serve.
	 */
	private LeaderTestInfoDTO isServe = new LeaderTestInfoDTO();

	/**
	 * MATERIAL?.
	 */
	private boolean isMaterialFull = false;

	/**
	 * BEER?.
	 */
	private boolean isHaveBeerSuccess = false;

	/**
	 * The is all beer success.
	 */
	private boolean isAllBeerSuccess = false;

	/**
	 * The free beers.
	 */
	List<FreeBeer> freeBeers;

	/**
	 * The original order list.
	 */
	List<BeerList> originalOrderList;

	/**
	 * The Class FreeBeer.
	 */
	public static class FreeBeer {

		/**
		 * The beer.
		 */
		private RegistryDTO beer;

		/**
		 * The quantity.
		 */
		private int quantity;

		/**
		 * Gets the beer.
		 *
		 * @return the beer
		 */
		public RegistryDTO getBeer() {
			return beer;
		}

		/**
		 * Sets the beer.
		 *
		 * @param beer
		 *            the new beer
		 */
		public void setBeer(RegistryDTO beer) {
			this.beer = beer;
		}

		/**
		 * Gets the quantity.
		 *
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * Sets the quantity.
		 *
		 * @param quantity
		 *            the new quantity
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		/**
		 * Instantiates a new free beer.
		 *
		 * @param beer
		 *            the beer
		 * @param quantity
		 *            the quantity
		 */
		public FreeBeer(RegistryDTO beer, int quantity) {
			super();
			this.beer = beer;
			this.quantity = quantity;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "freeBeer [beer=" + beer + ", quantity=" + quantity + "]";
		}

	}

	/**
	 * Gets the order status.
	 *
	 * @param key
	 *            the key
	 * @return the order status
	 */
	public String getOrderStatus(String key) {
		try {
			String s = Status.getByKey(key).toString();
			return s;
		} catch (NullPointerException e) {
			return "undefined status";
		}

	}

	/**
	 * Gets the manage order facade service.
	 *
	 * @return the manage order facade service
	 */
	public ManageOrderFacadeService getManageOrderFacadeService() {
		return manageOrderFacadeService;
	}

	/**
	 * Sets the manage order facade service.
	 *
	 * @param manageOrderFacadeService
	 *            the new manage order facade service
	 */
	public void setManageOrderFacadeService(
			ManageOrderFacadeService manageOrderFacadeService) {
		this.manageOrderFacadeService = manageOrderFacadeService;
	}

	/**
	 * Gets the selected order.
	 *
	 * @return the selected order
	 */
	public OrderDTO getSelectedOrder() {
		return selectedOrder;
	}

	/**
	 * Sets the selected order.
	 *
	 * @param selectedOrder
	 *            the new selected order
	 */
	public void setSelectedOrder(OrderDTO selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	/**
	 * Gets the manage registry facade service.
	 *
	 * @return the manage registry facade service
	 */
	public ManageRegistryFacadeService getManageRegistryFacadeService() {
		return manageRegistryFacadeService;
	}

	/**
	 * Sets the manage registry facade service.
	 *
	 * @param manageRegistryFacadeService
	 *            the new manage registry facade service
	 */
	public void setManageRegistryFacadeService(
			ManageRegistryFacadeService manageRegistryFacadeService) {
		this.manageRegistryFacadeService = manageRegistryFacadeService;
	}

	/**
	 * Gets the checks if is serve.
	 *
	 * @return the checks if is serve
	 */
	public LeaderTestInfoDTO getIsServe() {
		return isServe;
	}

	/**
	 * Sets the checks if is serve.
	 *
	 * @param isServe
	 *            the new checks if is serve
	 */
	public void setIsServe(LeaderTestInfoDTO isServe) {
		this.isServe = isServe;
	}

	/**
	 * Gets the checks if is have beer success.
	 *
	 * @return the checks if is have beer success
	 */
	public boolean getIsHaveBeerSuccess() {
		return isHaveBeerSuccess;
	}

	/**
	 * Gets the checks if is ready and transport.
	 *
	 * @return the checks if is ready and transport
	 */
	public boolean getIsReadyAndTransport() {
		return isReadyAndTransport;
	}

	/**
	 * Sets the ready and transport.
	 *
	 * @param isReadyAndTransport
	 *            the new ready and transport
	 */
	public void setReadyAndTransport(boolean isReadyAndTransport) {
		this.isReadyAndTransport = isReadyAndTransport;
	}

	/**
	 * Gets the checks if is material full.
	 *
	 * @return the checks if is material full
	 */
	public boolean getIsMaterialFull() {
		return isMaterialFull;
	}

	/**
	 * Sets the material full.
	 *
	 * @param isMaterialFull
	 *            the new material full
	 */
	public void setMaterialFull(boolean isMaterialFull) {
		this.isMaterialFull = isMaterialFull;
	}

	/**
	 * Sets the have beer success.
	 *
	 * @param isHaveBeerSuccess
	 *            the new have beer success
	 */
	public void setHaveBeerSuccess(boolean isHaveBeerSuccess) {
		this.isHaveBeerSuccess = isHaveBeerSuccess;
	}

	/**
	 * Gets the checks if is all beer success.
	 *
	 * @return the checks if is all beer success
	 */
	public boolean getIsAllBeerSuccess() {
		return isAllBeerSuccess;
	}

	/**
	 * Sets the all beer success.
	 *
	 * @param isAllBeerSuccess
	 *            the new all beer success
	 */
	public void setAllBeerSuccess(boolean isAllBeerSuccess) {
		this.isAllBeerSuccess = isAllBeerSuccess;
	}

	/**
	 * Gets the free beers.
	 *
	 * @return the free beers
	 */
	public List<FreeBeer> getFreeBeers() {
		return freeBeers;
	}

	/**
	 * Gets the registry list.
	 *
	 * @return the registry list
	 */
	public List<RegistryDTO> getRegistryList() {
		return registryList;
	}

	/**
	 * Sets the registry list.
	 *
	 * @param registryList
	 *            the new registry list
	 */
	public void setRegistryList(List<RegistryDTO> registryList) {
		this.registryList = registryList;
	}

	/**
	 * Sets the free beers.
	 *
	 * @param freeBeers
	 *            the new free beers
	 */
	public void setFreeBeers(List<FreeBeer> freeBeers) {
		this.freeBeers = freeBeers;
	}

	/**
	 * Test.
	 */
	public void test() {
		if (selectedOrder != null) {
			// System.out.println("!!! can? :_" + isServe);
		}
	}

	/**
	 * Ready for create.
	 */
	public void readyForCreate() {
		try {
			if (selectedOrder != null) {
				for (Need need : isServe.getAllMaterial()) {
					boolean status = manageRegistryFacadeService
							.keepMaterialForOrder(selectedOrder,
									need.getMaterial(), need.getNeedQuantity());
				}

				/**
             *
             */
				for (RegistryDTO currentRegistry : registryList) {
					if (currentRegistry.getQuantity() > 0) {
						manageRegistryFacadeService.createBeerNeedForOrder(
								selectedOrder, currentRegistry.getBeer(),
								currentRegistry.getQuantity());
					}
					currentRegistry.setQuantity(0);
					manageRegistryFacadeService.updateRegistry(currentRegistry);
				}
				/**
             *
             */
				selectedOrder.setStatus("READYFORCREATE");
				manageOrderFacadeService.updateOrder(selectedOrder);

				// selectedOrder = null;
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Checks if is transport.
	 */
	private void isTransport() {
		if (selectedOrder != null) {
			for (RegistryDTO currentRegistry : registryList) {
				int currentQuantity = currentRegistry.getQuantity();
				if (currentQuantity > 0) {

					isReadyAndTransport = false;
					return;
				}
			}
			isReadyAndTransport = true;
		}
	}

	/**
	 * Transport.
	 */
	public void transport() {
		if (selectedOrder != null) {
			selectedOrder.setStatus("TRANSPORT");
			manageOrderFacadeService.updateOrder(selectedOrder);
			// selectedOrder = null;
		}
	}

	/**
	 * Sets the need material.
	 */
	public void setNeedMaterial() {
		if (selectedOrder != null) {
			selectedOrder.setStatus("NEEDMATERIAL");
			manageOrderFacadeService.updateOrder(selectedOrder);
			// selectedOrder = null;
		}
	}

	/**
	 * Keep beer.
	 */
	public void keepBeer() {
		if (selectedOrder != null) {
			// leaderController.freeBeers
			for (RegistryDTO currentRegistry : registryList) {
				for (RegistryDTO freeRegistry : isServe.getFreeSuccessBeers()) {
					BeerDTO freeBeer = freeRegistry.getBeer();
					int freeQuantity = freeRegistry.getQuantity();
					// osszehasonlitas a szabad és rendelt sorok kozott
					if (freeBeer.getId() == currentRegistry.getBeer().getId()) {
						int currentQuantity = currentRegistry.getQuantity();
						if (currentQuantity == 0) {
							continue;
						}
						freeRegistry.setOrder(selectedOrder);
						freeRegistry.setStatus("READY");
						/**
                         *
                         */
						if (currentQuantity < freeQuantity) {
							// különvalasztunk
							// új registry a maradt materialnak
							RegistryDTO newRegistry = new RegistryDTO();
							newRegistry.setBeer(currentRegistry.getBeer());
							newRegistry.setStatus("FREE");
							newRegistry.setQuantity(freeQuantity
									- currentQuantity);
							// mentunk
							List<RegistryDTO> ret = new LinkedList<RegistryDTO>();
							ret.add(newRegistry);
							manageRegistryFacadeService.saveRegistrys(ret);
							//
							freeRegistry.setQuantity(currentQuantity);
							manageRegistryFacadeService
									.updateRegistry(freeRegistry);
							//
							currentQuantity = 0;
							currentRegistry.setQuantity(currentQuantity);
							manageRegistryFacadeService
									.updateRegistry(currentRegistry);
						} else {
							currentQuantity -= freeQuantity;
							currentRegistry.setQuantity(currentQuantity);
							manageRegistryFacadeService
									.updateRegistry(currentRegistry);
							manageRegistryFacadeService
									.updateRegistry(freeRegistry);
						}
					}
				}
			}
			// selectedOrder = null;
		}
	}

	/**
	 * Evaluation.
	 */
	public void evaluation() {
		if (selectedOrder == null) {
			return;
		}
		// init variables
		isHaveBeerSuccess = false;
		isMaterialFull = false;
		isAllBeerSuccess = false;
		/**
         *
         */
		originalOrderList = new LinkedList<LeaderTestInfoDTO.BeerList>();
		for (RegistryDTO r : manageRegistryFacadeService.findByStatusAndOrder(
				null, selectedOrder)) {
			boolean initState = "DONE".equals(selectedOrder.getStatus()) ? true
					: false;
			originalOrderList.add(new BeerList(r.getBeer().getId(), r.getBeer()
					.getName(), r.getOriginalQuantity(), r
					.getOriginalQuantity(), initState));
		}
		// lekerdezzuk az registry-t a kivalasztott orderhez
		// kiszurjuk azokat a registryket amik nem a rendeles adatait
		// tartalmazzak, statusz alapjan
		List<RegistryDTO> reg = manageRegistryFacadeService
				.findByOrder(selectedOrder);
		registryList = new LinkedList<RegistryDTO>();
		/**
         *
         */
		for (RegistryDTO r : reg) {
			if (r.getStatus() == "" || r.getStatus() == null) {
				registryList.add(r);
			} else {
				if (r.getBeer() != null && "READY".equals(r.getStatus())) {
					for (BeerList current : originalOrderList) {
						if (current.getId() == r.getBeer().getId()) {
							current.setInWhQuantity(current.getInWhQuantity()
									- r.getQuantity());
							if (current.getInWhQuantity() <= 0) {
								current.setReady(true);
							}
							continue;
						}
					}
				}
			}
		}

		// osszegyujtjuk milyen beereket rendeltek
		List<BeerDTO> orderBeers = new LinkedList<BeerDTO>();

		HashSet<Long> beerIDs = new HashSet<Long>();
		for (RegistryDTO r : registryList) {
			orderBeers.add(r.getBeer());
		}

		freeBeers = new LinkedList<LeaderController.FreeBeer>();
		// megnezzuk van -e szabad kesz sor?
		if (isServe.getIsFreeSuccessBeers()) {
			for (RegistryDTO r : isServe.getFreeSuccessBeers()) {
				BeerDTO freeBeer = r.getBeer();
				int freeQuantity = r.getQuantity();
				// osszehasonlitas a szabad és rendelt sorok kozott
				for (RegistryDTO currentRegistry : registryList) {
					if (freeBeer.getId() == currentRegistry.getBeer().getId()) {
						// feljegyezzuk a registry-t es hogy mennyit foglalnank
						// le
						isHaveBeerSuccess = true;
						if (!beerIDs.contains(r.getId())) {
							freeBeers.add(new FreeBeer(r, freeQuantity));
							beerIDs.add(r.getId());
						}
					}
				}
			}
		}
		if (isServe.getNeeded().size() == 0) {
			isMaterialFull = true;
		} else {
			isMaterialFull = false;
		}

		// /
		// originalOrderList = isServe.getBeerList();
		isTransport();
	}

	/**
	 * On row select.
	 *
	 * @param event
	 *            the event
	 */
	public void onRowSelect(SelectEvent event) {
		selectedOrder = (OrderDTO) event.getObject();

		if (selectedOrder != null) {
			isServe = manageRegistryFacadeService
					.isOrderCanBeServe(selectedOrder);
			isServe.optimize();
		}
		evaluation();
		// /////////////
		FacesContext.getCurrentInstance().addMessage(
				"createmsgs",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", String
						.format("%s %s!",
								LocaleSwitcher.getMessage("order_selected"),
								selectedOrder.getName())));
	}

	/**
	 * Gets the original order list.
	 *
	 * @return the original order list
	 */
	public List<BeerList> getOriginalOrderList() {
		return originalOrderList;
	}

	/**
	 * Sets the original order list.
	 *
	 * @param originalOrderList
	 *            the new original order list
	 */
	public void setOriginalOrderList(List<BeerList> originalOrderList) {
		this.originalOrderList = originalOrderList;
	}

}
