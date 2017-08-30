package org.hibernate.bugs.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "secondEntity")
public class SecondEntity extends AbstractEmbeddedIdEntity<SecondEntityId> {

	public SecondEntity() {
	}

	@Override
	protected SecondEntityId newId() {
		return new SecondEntityId();
	}

}