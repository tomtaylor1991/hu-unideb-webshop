package hu.unideb.webshop.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO extends BaseDTO implements Serializable,
		Comparable<OrderDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private LeaderTestInfoDTO info;

	private Date date;

	private String status;

	private PartnerDTO partnerDTO;

	private int costOfAll;

	public OrderDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OrderDTO other = (OrderDTO) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	public PartnerDTO getPartnerDTO() {
		return partnerDTO;
	}

	public void setPartnerDTO(PartnerDTO partnerDTO) {
		this.partnerDTO = partnerDTO;
	}

	public LeaderTestInfoDTO getInfo() {
		return info;
	}

	public void setInfo(LeaderTestInfoDTO info) {
		this.info = info;
	}

	public int getCostOfAll() {
		return costOfAll;
	}

	public void setCostOfAll(int costOfAll) {
		this.costOfAll = costOfAll;
	}

	@Override
	public int compareTo(OrderDTO other) {
		if (this.getId() > other.getId()) {
			return -1;
		} else if (this.getId() < other.getId()) {
			return 1;
		} else {
			return 0;
		}
	}

}
