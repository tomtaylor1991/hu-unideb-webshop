package hu.unideb.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of Role.
 */
@Entity
@Table(name = "Role")
public class Role extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The role. */
	private String role;

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }
   
}
