package team12.stockist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product 
{
	
	@Id
	@Column(name="PartID")
	private Integer partID;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="UnitPrice")
	private Double unitPrice;
	
	@Column(name="Color")
	private String color;
	
	@Column(name="Dimension")
	private String dimension;
	
	@Column(name="Manufacturer")
	private String manufacturer;
	
	@Column(name="ReorderLevel")
	private Integer reorderLevel;
	
	@Column(name="MinReorderQty")
	private Integer minReorderQty;
	
	@Column(name="ShelfLocation")
	private String shelfLocation;
	
	@Column(name="SupplierID")
	private String supplierID;
	
	@Column(name="UnitsInStock")
	private Integer unitsInStock;
	
	@Column(name="UnitsOnOrder")
	private Integer unitsOnOrder;
	
	@Column(name="Discontinued")
	private Integer discontinued;
	
	//Table relationship do we need to write?
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer partID, String description, Double unitPrice, String color, String dimension,
			String manufacturer, Integer reorderLevel, Integer minReorderLevel, String shelfLocation, String supplierID,
			Integer unitsInStock, Integer unitsOnOrder, Integer discontinued) {
		super();
		this.partID = partID;
		this.description = description;
		this.unitPrice = unitPrice;
		this.color = color;
		this.dimension = dimension;
		this.manufacturer = manufacturer;
		this.reorderLevel = reorderLevel;
		this.minReorderQty = minReorderLevel;
		this.shelfLocation = shelfLocation;
		this.supplierID = supplierID;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.discontinued = discontinued;
	}

	public Integer getPartID() {
		return partID;
	}

	public void setPartID(Integer partID) {
		this.partID = partID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getMinReorderQty() {
		return minReorderQty;
	}

	public void setMinReorderQty(Integer minReorderLevel) {
		this.minReorderQty = minReorderLevel;
	}

	public String getShelfLocation() {
		return shelfLocation;
	}

	public void setShelfLocation(String shelfLocation) {
		this.shelfLocation = shelfLocation;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Integer getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(Integer unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public Integer getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Integer discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + partID;
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
		Product other = (Product) obj;
		if (partID != other.partID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [PartID=" + partID + ", Description=" + description + ", UnitPrice=" + unitPrice + ", Color="
				+ color + ", Dimension=" + dimension + ", Manufacturer=" + manufacturer + ", ReorderLevel="
				+ reorderLevel + ", MinReorderLevel=" + minReorderQty + ", ShelfLocation=" + shelfLocation
				+ ", SupplierID=" + supplierID + ", UnitsInStock=" + unitsInStock + ", UnitsOnOrder=" + unitsOnOrder
				+ ", Discontinued=" + discontinued + "]";
	}
	
	
	

	
}
