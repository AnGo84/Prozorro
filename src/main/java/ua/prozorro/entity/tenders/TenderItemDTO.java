package ua.prozorro.entity.tenders;

import ua.prozorro.entity.tenders.pk.TenderItemPK;

import javax.persistence.*;

@Entity
@Table(name = "Tenders_Items")
@IdClass(TenderItemPK.class)
public class TenderItemDTO {
	
	@Id
	@Column(name = "tender_id")
	private String tenderId;
	
	@Id
	@Column(name = "item_id")
	private String itemId;
	
	public TenderItemDTO() {
	}
	
	public TenderItemDTO(String tenderId, String itemId) {
		this.tenderId = tenderId;
		this.itemId = itemId;
	}
	
	public String getTenderId() {
		return tenderId;
	}
	
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		TenderItemDTO that = (TenderItemDTO) o;
		
		if (tenderId != null ? !tenderId.equals(that.tenderId) : that.tenderId != null)
			return false;
		return itemId != null ? itemId.equals(that.itemId) : that.itemId == null;
	}
	
	@Override
	public int hashCode() {
		int result = tenderId != null ? tenderId.hashCode() : 0;
		result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TenderItemDTO{");
		sb.append("tenderId='").append(tenderId).append('\'');
		sb.append(", itemId='").append(itemId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

