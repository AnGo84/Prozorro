package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.ContractItemPK;

import javax.persistence.*;

@Entity
@Table(name = "Contracts_Items")
@IdClass(ContractItemPK.class)
public class ContractItemDTO {
	@Id
	@Column(name = "contract_id")
	private String contractId;
	
	@Id
	@Column(name = "item_id")
	private String itemId;
	
	public ContractItemDTO() {
	}
	
	public ContractItemDTO(String contractId, String itemId) {
		this.contractId = contractId;
		this.itemId = itemId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		ContractItemDTO that = (ContractItemDTO) o;
		
		if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null)
			return false;
		return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
	}
	
	@Override
	public int hashCode() {
		int result = contractId != null ? contractId.hashCode() : 0;
		result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ContractItemDTO{");
		sb.append("contractId='").append(contractId).append('\'');
		sb.append(", itemId='").append(itemId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
