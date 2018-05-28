package team12.stockist.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import team12.stockist.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	//-------------------- For Report Generation --------------------------//
	@Query("SELECT p from Product p WHERE p.supplierID = :sId")
	ArrayList<Product> findProductBySupplier(@Param("sId") String supplierID);
	
	
	
	
	//-------------------- For Semi-Advanced Search --------------------------//
	@Query("SELECT p FROM Product p WHERE manufacturer = :mfr")
	ArrayList<Product> findAllByFilter(@Param("mfr") String mfr);
		
		
		
		
	//-------------------- Querying a specific column [For General Search] --------------------------//
	@Query(value = "SELECT * FROM stockist.products WHERE PartID LIKE %:pid%", nativeQuery = true)
	ArrayList<Product> searchAllPartID(@Param("pid") String pid);
	
	@Query("SELECT p FROM Product p WHERE description LIKE %:desc%")
	ArrayList<Product> searchAllDescription(@Param("desc") String desc);
	
	@Query("SELECT p FROM Product p WHERE color LIKE %:clr%")
	ArrayList<Product> searchAllColor(@Param("clr") String clr);
	
	@Query("SELECT p FROM Product p WHERE manufacturer LIKE %:mfr%")
	ArrayList<Product> searchAllManufacturer(@Param("mfr") String mfr);
	
	@Query("SELECT p FROM Product p WHERE shelfLocation LIKE %:slc%")
	ArrayList<Product> searchAllShelfLocation(@Param("slc") String slc);
	
	@Query(value = "SELECT p.PartID, p.Description, p.UnitPrice, p.Color, p.Dimension, p.Manufacturer, p.ReorderLevel, p.MinReorderQty,\r\n" + 
			"p.ShelfLocation, p.SupplierID, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\r\n" + 
			"FROM stockist.products p, stockist.suppliers s \r\n" + 
			"WHERE p.SupplierID = s.SupplierID AND s.CompanyName LIKE %:supN%", nativeQuery = true)
	ArrayList<Product> searchSupplierName(@Param("supN") String supN);
		// Gotta perform a join since 'CompanyName' is in another table (Supplier).
	
	
	
	
	
	
	
	//-------------------- Querying a specific column [For Advanced Search] --------------------------//
	@Query(value = "SELECT * FROM stockist.products WHERE PartID LIKE %:pid% AND Manufacturer = :mfr", nativeQuery = true)
	ArrayList<Product> searchAllPartIDByFilterManufacturer(@Param("pid") String pid, @Param("mfr") String mfr);
	
	@Query("SELECT p FROM Product p WHERE description LIKE %:desc% AND manufacturer = :mfr")
	ArrayList<Product> searchAllDescriptionByFilterManufacturer(@Param("desc") String desc, @Param("mfr") String mfr);
	
	@Query("SELECT p FROM Product p WHERE color LIKE %:clr% AND manufacturer = :mfr")
	ArrayList<Product> searchAllColorByFilterManufacturer(@Param("clr") String clr, @Param("mfr") String mfr);
	
	@Query("SELECT p FROM Product p WHERE shelfLocation LIKE %:slc% AND manufacturer = :mfr")
	ArrayList<Product> searchAllShelfLocationByFilterManufacturer(@Param("slc") String slc, @Param("mfr") String mfr);
	
	@Query(value = "SELECT p.PartID, p.Description, p.UnitPrice, p.Color, p.Dimension, p.Manufacturer, p.ReorderLevel, p.MinReorderQty,\r\n" + 
			"p.ShelfLocation, p.SupplierID, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\r\n" + 
			"FROM stockist.products p, stockist.suppliers s \r\n" + 
			"WHERE p.SupplierID = s.SupplierID AND s.CompanyName LIKE %:supN% AND Manufacturer = :mfr", nativeQuery = true)
	ArrayList<Product> searchSupplierNameByFilterManufacturer(@Param("supN") String supN, @Param("mfr") String mfr);

}
