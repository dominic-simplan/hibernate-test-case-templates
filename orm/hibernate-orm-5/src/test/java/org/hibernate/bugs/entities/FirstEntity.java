package org.hibernate.bugs.entities;

import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "firstEntity")
public class FirstEntity extends AbstractEmbeddedIdEntity<FirstEntityId> {

	private static final long serialVersionUID = -5771103361843301446L;

	public FirstEntity() {
	}

	@Override
	protected FirstEntityId newId() {
		return new FirstEntityId();
	}

}