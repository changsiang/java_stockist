package team12.stockist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team12.stockist.model.Supplier;
import team12.stockist.model.User;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
	
}
