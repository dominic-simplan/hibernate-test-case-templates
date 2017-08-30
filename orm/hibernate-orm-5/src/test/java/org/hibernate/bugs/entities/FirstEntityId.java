package org.hibernate.bugs.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FirstEntityId implements Serializable {
	private static final long serialVersionUID = -3906287313997919890L;
	@Column(name = "firstId")
	private String firstId;

	public FirstEntityId() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FirstEntityId)) {
			return false;
		}
		FirstEntityId other = (FirstEntityId) obj;
		return 
				Objects.equals(firstId, other.firstId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstId);
	}
}