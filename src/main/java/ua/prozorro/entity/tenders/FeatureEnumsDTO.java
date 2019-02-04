package ua.prozorro.entity.tenders;

import javax.persistence.*;

@Entity
@Table(name = "Features_Enums")
public class FeatureEnumsDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id")
	private FeatureDTO featureDTO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enum_id")
	private FeatureEnumDTO enumDTO;
	
	public FeatureEnumsDTO() {
	}
	
	public FeatureEnumsDTO(FeatureDTO featureDTO, FeatureEnumDTO enumDTO) {
		this.featureDTO = featureDTO;
		this.enumDTO = enumDTO;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public FeatureDTO getFeatureDTO() {
		return featureDTO;
	}
	
	public void setFeatureDTO(FeatureDTO featureDTO) {
		this.featureDTO = featureDTO;
	}
	
	public FeatureEnumDTO getEnumDTO() {
		return enumDTO;
	}
	
	public void setEnumDTO(FeatureEnumDTO enumDTO) {
		this.enumDTO = enumDTO;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		FeatureEnumsDTO that = (FeatureEnumsDTO) o;
		
		if (id != that.id)
			return false;
		if (featureDTO != null ? !featureDTO.equals(that.featureDTO) : that.featureDTO != null)
			return false;
		return enumDTO != null ? enumDTO.equals(that.enumDTO) : that.enumDTO == null;
	}
	
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (featureDTO != null ? featureDTO.hashCode() : 0);
		result = 31 * result + (enumDTO != null ? enumDTO.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FeatureEnumsDTO{");
		sb.append("id=").append(id);
		sb.append(", featureDTO=").append(featureDTO);
		sb.append(", enumDTO=").append(enumDTO);
		sb.append('}');
		return sb.toString();
	}
}
