package ua.prozorro.entity.prozorro;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tenders_JSON")
@IdClass(JSONPK.class)
@Data
public class TenderJSON {
	
	@Id
	@Column(name = "tender_id")
	private String id;
	@Id
	@Column(name = "dateModified")
	private String dateModified;
	
	@Lob
	@Column(name = "model")
	private String model;
	
}
