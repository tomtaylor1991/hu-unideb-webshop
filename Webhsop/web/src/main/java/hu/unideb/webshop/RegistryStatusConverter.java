package hu.unideb.webshop;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * 
 */
@FacesConverter(value = "registryStatusConverter")
public class RegistryStatusConverter extends EnumConverter {

    public RegistryStatusConverter() {
        super(RegistryStatus.class);
    }

}
