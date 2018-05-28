package team12.stockist.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team12.stockist.model.UsageRecord;

public interface UsageRecordRepository extends JpaRepository<UsageRecord, String> {

	// Native querry to retrieve data from usagerecords table based on passed
	// parameter products partId and join conditions

	@Query(value = "SELECT * from usagerecords ur,products p,usagerecorddetails urd  where p.PartID=?1 and p.PartID=urd.Products_PartID and urd.TransID=ur.TransID", nativeQuery = true)
	ArrayList<UsageRecord> findUsageRecordHistory(int products_PartID);

	@Query("SELECT ur from UsageRecord ur WHERE ur.userId=:uid")
	ArrayList<UsageRecord> findUsageRecordByUserId(@Param("uid") int userID);

	@Query(value = "SELECT *  from products p,usagerecorddetails urd,usagerecords ur where p.PartID = :pid and \r\n"
			+ "p.PartID=urd.Products_PartID and urd.TransID=ur.TransID and\r\n"
			+ " ur.DateUsed between :stDate and :edDate", nativeQuery = true)
	ArrayList<UsageRecord> findTransactionHistoryByDateRange(@Param("pid") int pid, @Param("stDate") String stDate,
			@Param("edDate") String edDate);

	// Using this will work: '2017-09-12' and '2017-11-12'

}
