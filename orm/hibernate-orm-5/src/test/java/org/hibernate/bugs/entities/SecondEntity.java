package org.hibernate.bugs.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "secondEntity")
public class SecondEntity extends AbstractEmbeddedIdEntity<SecondEntityId> {

	private static final long serialVersionUID = -9176028203802493181L;

	public SecondEntity() {
	}

	@Override
	protected SecondEntityId newId() {
		return new SecondEntityId();
	}

}