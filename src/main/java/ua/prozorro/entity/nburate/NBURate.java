package ua.prozorro.entity.nburate;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "NBU_rates")
@IdClass(NBURatePK.class)
public class NBURate implements Serializable {
	@Id
	@Column(name = "arcdate")
	private String date;
	
	@Id
	@Column
	private String currencyCode;
	
	@Column
	private String currencyCodeL;
	
	@Column
	private String timeSign;
	
	@Column
	private int units;
	@Column
	private double amount;
	

}
