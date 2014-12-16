package hu.unideb.webshop.dto;

import java.io.Serializable;

public class RegistryDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer quantity;
    private int originalQuantity;
    private BeerDTO beer;
    private WarehouseDTO warehouse;
    private MaterialDTO material;
    private OrderDTO order;
    private String status;

    public RegistryDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BeerDTO getBeer() {
        return beer;
    }

    public void setBeer(BeerDTO beer) {
        this.beer = beer;
    }

    public WarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(int originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    @Override
    public String toString() {
        return "RegistryDTO [id=" + id + ", quantity=" + quantity + ", beer="
                + beer + ", warehouse=" + warehouse + ", material=" + material
                + ", order=" + order + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beer == null) ? 0 : beer.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
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
        RegistryDTO other = (RegistryDTO) obj;
        if (beer == null) {
            if (other.beer != null) {
                return false;
            }
        } else if (!beer.equals(other.beer)) {
            return false;
        }
        if (order == null) {
            if (other.order != null) {
                return false;
            }
        } else if (!order.equals(other.order)) {
            return false;
        }
        return true;
    }

}
