package org.hibernate.bugs.entities;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class for all entities.
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {


	private static final long serialVersionUID = -3785421719397617211L;

	public abstract ID getId();

	public abstract void setId(ID id);
}