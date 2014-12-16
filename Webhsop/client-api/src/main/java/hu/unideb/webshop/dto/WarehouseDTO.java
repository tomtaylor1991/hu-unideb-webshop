package hu.unideb.webshop.dto;

import java.io.Serializable;

public class WarehouseDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String address;

    public WarehouseDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WarehouseDTO{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }

}
