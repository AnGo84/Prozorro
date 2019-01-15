package ua.prozorro.entity.plans;

import org.hibernate.annotations.Cascade;
import ua.prozorro.entity.tenders.IdentifierDTO;
import ua.prozorro.prozorro.model.tenders.Identifier;

import javax.persistence.*;

@Entity
@Table(name = "ProcuringEntities")
public class ProcuringEntityDTO {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identifier_id")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private IdentifierDTO identifier;
	@Column
	private String name;
	@Id
	@Column
	private int id;

	public IdentifierDTO getIdentifier() {
		return identifier;
	}

	public void setIdentifier(IdentifierDTO identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ProcuringEntityDTO that = (ProcuringEntityDTO) o;

		if (id != that.id) return false;
		if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = identifier != null ? identifier.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + id;
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ProcuringEntityDTO{");
		sb.append("identifier=").append(identifier);
		sb.append(", name='").append(name).append('\'');
		sb.append(", id=").append(id);
		sb.append('}');
		return sb.toString();
	}
}
