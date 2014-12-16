package hu.unideb.webshop.dto;

import java.io.Serializable;

/**
 * Hozzávaló DTO osztálya, amely kiterjeszti a BaseDTO ősosztályt, mely tartalmazza
 * a hozzávaló id-ját, nevét és árát, valamint ezek getter és setter metódusait. *
 */
public class MaterialDTO extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * A hozzávaló id-ja.
	 */
	private Long id;
	/**
	 * A hozzávaló neve.
	 */
	private String materialName;
	/**
	 * A hozzávaló ára.
	 */
	private Double cost;
	
	/**
	 * Paraméter nélküli konstruktor.
	 */
	public MaterialDTO(){
		
	}

	/**
	 * Visszaadja a hozzávaló id-ját.
	 * @return a hozzávaló id-ja.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Beállítja a hozzávaló id-ját.
	 * @param id a hozzávaló id-ja.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Visszaadja a hozzávaló nevét.
	 * @return a hozzávaló neve.
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * Beállítja a hozzávaló nevét.
	 * @param materialName a hozzávaló neve.
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * Visszaadja a hozzávaló árát.
	 * @return a hozzávaló ára.
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * Beállítja a hozzávaló árát.
	 * @param cost a hozzávaló ára.
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return materialName;
	}
}
