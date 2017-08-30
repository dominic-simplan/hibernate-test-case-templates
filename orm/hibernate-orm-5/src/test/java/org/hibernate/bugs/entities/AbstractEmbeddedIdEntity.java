package org.hibernate.bugs.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class for all entities with a custom (embedded) entity key.
 */
@MappedSuperclass
public abstract class AbstractEmbeddedIdEntity<ID extends Serializable> {

	@EmbeddedId
	private ID id;

	public AbstractEmbeddedIdEntity() {
		id = newId();
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	protected abstract ID newId();
}