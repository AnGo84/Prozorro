package ua.prozorro.entity.prozorro;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contracts_json")
@IdClass(JSONPK.class)
@Data
public class ContractJSON {
	@Id
	@Column(name = "contract_id")
	private String id;
	
	@Id
	@Column(name = "dateModified")
	private String dateModified;
	
	@Lob
	@Column(name = "model")
	private String model;
	
}
