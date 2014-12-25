package hu.unideb.webshop.dto;

import java.io.Serializable;
import java.util.List;

public class LeaderTestInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	OrderDTO order;
	List<Need> need;

	public static class Need implements Serializable {
		private static final long serialVersionUID = 1L;

		private ProductDTO product;
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

	@Override
	public String toString() {
		return "LeaderTestInfoDTO [order=" + order + ", need=" + need + "]";
	}

}
