package org.hibernate.bugs.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SecondEntityId implements Serializable {
	
	private static final long serialVersionUID = -6222219927968609616L;
	@Column(name = "secondId")
	private String secondId;

	public SecondEntityId() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SecondEntityId)) {
			return false;
		}
		SecondEntityId other = (SecondEntityId) obj;
		return Objects.equals(secondId, other.secondId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(secondId);
	}
}