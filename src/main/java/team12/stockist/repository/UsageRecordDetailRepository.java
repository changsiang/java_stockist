package team12.stockist.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import team12.stockist.model.UsageRecordDetail;

public interface UsageRecordDetailRepository extends JpaRepository<UsageRecordDetail, Integer> {
	
	// Native querry to retrieve data from usagerecorddetails table based on
	// products partid and join conditions
	@Query(value = "SELECT * \r\n"
			+ "from products p,usagerecorddetails urd,usagerecords ur where p.PartID=?1 and p.PartID=urd.Products_PartID and urd.TransID=ur.TransID", nativeQuery = true)
	ArrayList<UsageRecordDetail> findTransactionHistoryByProductId(int products_PartID);
}
