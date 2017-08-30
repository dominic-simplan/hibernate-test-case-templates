package org.hibernate.bugs.entities;

import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "firstEntity")
public class FirstEntity extends AbstractEmbeddedIdEntity<FirstEntityId> {

	public FirstEntity() {
	}

	@Override
	protected FirstEntityId newId() {
		return new FirstEntityId();
	}

}