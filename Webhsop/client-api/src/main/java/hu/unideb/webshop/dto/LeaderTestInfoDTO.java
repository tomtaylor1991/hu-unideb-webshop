package hu.unideb.webshop.dto;

import java.io.Serializable;
import java.util.List;

public class LeaderTestInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	OrderDTO order;
	List<Need> need;
	private double income;
	private boolean importReady = false;
	private boolean readyForTransport = true;

	public void calculate() {
		// calculate income
		for (Need n : need) {
			income += n.getProduct().getPrice()
					* n.getRegistry().getOriginalQuantity();
			// transport check
			if (!order.getStatus().equals("TRANSPORT")
					&& !order.getStatus().equals("READY")) {
				if (n.getNeed() > 0) {
					setReadyForTransport(false);
				}
			}
		}
		// calculate isTransport
		if (!order.getStatus().equals("TRANSPORT")
				&& !order.getStatus().equals("READY") && readyForTransport) {
			order.setStatus("READY FOR TRANSPORT");
		}
		// calculate ready?
		if (order.getStatus().equals("NEEDPRODUCT")
				|| order.getStatus().equals("NEW") && need != null) {
			for (Need n : need) {
				if (n.getNeed() > n.getInWHQuantity()) {
					setImportReady(false);
					return;
				}
			}
			setImportReady(true);
		}
	}

	public static class Need implements Serializable {
		private static final long serialVersionUID = 1L;

		private ProductDTO product;
		private RegistryDTO registry;
		private int originalQuantity;
		private int need;
		private int inWHQuantity;
		private int readyQuantity;

		public ProductDTO getProduct() {
			return product;
		}

		public void setProduct(ProductDTO product) {
			this.product = product;
		}

		public int getNeed() {
			return need;
		}

		public void setNeed(int need) {
			this.need = need;
		}

		public int getOriginalQuantity() {
			return originalQuantity;
		}

		public void setOriginalQuantity(int originalQuantity) {
			this.originalQuantity = originalQuantity;
		}

		public int getInWHQuantity() {
			return inWHQuantity;
		}

		public void setInWHQuantity(int inWHQuantity) {
			this.inWHQuantity = inWHQuantity;
		}

		@Override
		public String toString() {
			return "Need [product=" + product + ", originalQuantity="
					+ originalQuantity + ", need=" + need + ", inWHQuantity="
					+ inWHQuantity + ", readyQuantity=" + readyQuantity + "]";
		}

		public int getReadyQuantity() {
			return readyQuantity;
		}

		public void setReadyQuantity(int readyQuantity) {
			this.readyQuantity = readyQuantity;
		}

		public RegistryDTO getRegistry() {
			return registry;
		}

		public void setRegistry(RegistryDTO registry) {
			this.registry = registry;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((product == null) ? 0 : product.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Need other = (Need) obj;
			if (product == null) {
				if (other.product != null)
					return false;
			} else if (!product.equals(other.product))
				return false;
			return true;
		}

	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public List<Need> getNeed() {
		return need;
	}

	public void setNeed(List<Need> need) {
		this.need = need;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public boolean getImportReady() {
		return importReady;
	}

	public void setImportReady(boolean importReady) {
		this.importReady = importReady;
	}

	@Override
	public String toString() {
		return "LeaderTestInfoDTO [order=" + order + ", need=" + need
				+ ", income=" + income + ", importReady=" + importReady + "]";
	}

	public boolean getReadyForTransport() {
		return readyForTransport;
	}

	public void setReadyForTransport(boolean readyForTransport) {
		this.readyForTransport = readyForTransport;
	}

}
