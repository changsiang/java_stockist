package team12.stockist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "usagerecorddetails")
public class UsageRecordDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "TransDetailID")
	private int transDetailId;
	@Column (name = "TransID")
	private String transId;
	@Column (name = "Products_PartID")
	private int productPartId;
	@Column (name = "UsedQty")
	private int usedQty;
	
	//Constructors
	public UsageRecordDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsageRecordDetail(int transDetailId, String transId, int productPartId, int usedQty) {
		super();
		this.transDetailId = transDetailId;
		this.transId = transId;
		this.productPartId = productPartId;
		this.usedQty = usedQty;
	}
	
	//Getters and Setters
	public int getTransDetailId() {
		return transDetailId;
	}
	public void setTransDetailId(int transDetailId) {
		this.transDetailId = transDetailId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public int getProductPartId() {
		return productPartId;
	}
	public void setProductPartId(int productPartId) {
		this.productPartId = productPartId;
	}
	public int getUsedQty() {
		return usedQty;
	}
	public void setUsedQty(int usedQty) {
		this.usedQty = usedQty;
	}
	
	//hashCode and equal for transDetail Pk only
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transDetailId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsageRecordDetail other = (UsageRecordDetail) obj;
		if (transDetailId != other.transDetailId)
			return false;
		return true;
	}
	
	

}
