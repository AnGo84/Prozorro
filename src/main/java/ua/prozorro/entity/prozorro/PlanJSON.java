package ua.prozorro.entity.prozorro;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "plans_JSON")
@IdClass(JSONPK.class)
@Data
public class PlanJSON {
	
	@Id
	@Column(name = "plan_id")
	private String id;
	@Id
	@Column(name = "dateModified")
	private String dateModified;
	
	@Lob
	@Column(name = "model")
	private String model;
	
}
