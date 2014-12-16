package hu.unideb.webshop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeaderTestInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean isOk = false;
	private String status;
	private List<Need> needed;
	private List<Need> allMaterial;
	private boolean isFreeSuccessBeers = false;
	List<RegistryDTO> freeSuccessBeers;
	List<BeerList> beerList;

	public void optimize() {
		if (needed != null) {
			Map<Long, Need> m = new HashMap<Long, LeaderTestInfoDTO.Need>();
			for (Need currentNeed : needed) {
				if (m.keySet().contains(currentNeed.getMaterial().getId())) {
					Need tmp = new Need(
							m.get(currentNeed.getMaterial().getId()));
					tmp.setNeedQuantity(tmp.getNeedQuantity()
							+ currentNeed.getNeedQuantity());
					m.put(currentNeed.getMaterial().getId(), tmp);
				} else {
					m.put(currentNeed.getMaterial().getId(), currentNeed);
				}
			}
			needed = new LinkedList<LeaderTestInfoDTO.Need>();
			for (Long key : m.keySet()) {
				needed.add(m.get(key));
			}
		}
		if (allMaterial != null) {
			Map<Long, Need> m = new HashMap<Long, LeaderTestInfoDTO.Need>();
			for (Need currentNeed : allMaterial) {
				if (m.keySet().contains(currentNeed.getMaterial().getId())) {
					Need tmp = new Need(
							m.get(currentNeed.getMaterial().getId()));
					tmp.setNeedQuantity(tmp.getNeedQuantity()
							+ currentNeed.getNeedQuantity());
					m.put(currentNeed.getMaterial().getId(), tmp);
				} else {
					m.put(currentNeed.getMaterial().getId(), currentNeed);
				}
			}
			allMaterial = new LinkedList<LeaderTestInfoDTO.Need>();
			for (Long key : m.keySet()) {
				allMaterial.add(m.get(key));
			}
		}
	}
	public static class BeerList{
		private String name;
		private Long id;
		private int needQuantity;
		private int inWhQuantity;
		private boolean isReady;

		public BeerList(Long id, String name, int needQuantity,
				int inWhQuantity, boolean isReady) {
			this.id = id;
			this.name = name;
			this.needQuantity = needQuantity;
			this.inWhQuantity = inWhQuantity;
			this.isReady = isReady;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getNeedQuantity() {
			return needQuantity;
		}
		public void setNeedQuantity(int needQuantity) {
			this.needQuantity = needQuantity;
		}
		public int getInWhQuantity() {
			return inWhQuantity;
		}
		public void setInWhQuantity(int inWhQuantity) {
			this.inWhQuantity = inWhQuantity;
		}
		public boolean getIsReady() {
			return isReady;
		}
		public void setReady(boolean isReady) {
			this.isReady = isReady;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
	}
	public static class Need {
		private MaterialDTO material;
		private int needQuantity;
		private int inWhQuantity;

		public MaterialDTO getMaterial() {
			return material;
		}

		public Need(Need other) {
			this.setMaterial(other.getMaterial());
			this.setNeedQuantity(other.getNeedQuantity());
			this.setInWhQuantity(other.getInWhQuantity());
		}

		public Need(MaterialDTO material, int needQuantity, int inWhQuantity) {
			super();
			this.material = material;
			this.needQuantity = needQuantity;
			this.inWhQuantity = inWhQuantity;
		}

		public void setMaterial(MaterialDTO material) {
			this.material = material;
		}

		public int getNeedQuantity() {
			return needQuantity;
		}

		public void setNeedQuantity(int needQuantity) {
			this.needQuantity = needQuantity;
		}

		public int getInWhQuantity() {
			return inWhQuantity;
		}

		public void setInWhQuantity(int inWhQuantity) {
			this.inWhQuantity = inWhQuantity;
		}

		@Override
		public String toString() {
			return "Need [material=" + material + ", needQuantity="
					+ needQuantity + ", inWhQuantity=" + inWhQuantity + "]";
		}

	}

	public LeaderTestInfoDTO() {
		isOk = false;
		needed = new ArrayList<LeaderTestInfoDTO.Need>();
	}

	public LeaderTestInfoDTO(boolean isOk2) {
		isOk = isOk2;
		needed = new ArrayList<LeaderTestInfoDTO.Need>();
	}

	public LeaderTestInfoDTO(boolean isOk2, List<Need> n) {
		isOk = isOk2;
		needed = n;
	}

	public void add(MaterialDTO material, int needQuantity, int inWhQuantity) {
		needed.add(new Need(material, needQuantity, inWhQuantity));
	}

	public boolean getIsOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public List<Need> getNeeded() {
		return needed;
	}

	public List<Need> getAllMaterial() {
		return allMaterial;
	}

	public void setAllMaterial(List<Need> allMaterial) {
		this.allMaterial = allMaterial;
	}

	public void setNeeded(List<Need> needed) {
		this.needed = needed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RegistryDTO> getFreeSuccessBeers() {
		return freeSuccessBeers;
	}

	public void setFreeSuccessBeers(List<RegistryDTO> freeSuccessBeers) {
		this.freeSuccessBeers = freeSuccessBeers;
	}

	public boolean getIsFreeSuccessBeers() {
		return isFreeSuccessBeers;
	}

	public void setFreeSuccessBeers(boolean isFreeSuccessBeers) {
		this.isFreeSuccessBeers = isFreeSuccessBeers;
	}

	@Override
	public String toString() {
		return "LeaderTestInfoDTO [isOk=" + isOk + ", status=" + status
				+ ", needed=" + needed + ", isFreeSuccessBeers="
				+ isFreeSuccessBeers + ", freeSuccessBeers=" + freeSuccessBeers
				+ "]";
	}

	public List<BeerList> getBeerList() {
		return beerList;
	}

	public void setBeerList(List<BeerList> beerList) {
		this.beerList = beerList;
	}

}
