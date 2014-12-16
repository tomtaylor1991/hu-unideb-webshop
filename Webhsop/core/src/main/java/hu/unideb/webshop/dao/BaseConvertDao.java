package hu.unideb.webshop.dao;

import hu.unideb.webshop.entity.BaseEntity;

import java.io.Serializable;

public interface BaseConvertDao<E extends BaseEntity, D extends Serializable> {

	/**
	 * Convert @param dto to entity.
	 * 
	 * @param dto
	 * @param entity
	 *            exiting entity for example update
	 * @return entity instance
	 */
	E toEntity(D dto, E entity);

	/**
	 * Conver @param entity to dto
	 * 
	 * @param entity
	 * @return dto instance
	 */
	D toDto(E entity);

}
