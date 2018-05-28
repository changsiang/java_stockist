package team12.stockist.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import team12.stockist.model.UsageRecord;
import team12.stockist.model.UsageRecordDetail;
import team12.stockist.repository.UsageRecordDetailRepository;

@Service
public class UsageRecordDetailServiceImpl implements UsageRecordDetailService {

	@Resource
	UsageRecordDetailRepository usageRecordDetailRepository;

	@Override
	@Transactional
	public ArrayList<UsageRecordDetail> findAllUsageRecordDetail() {
		ArrayList<UsageRecordDetail> urdList = (ArrayList<UsageRecordDetail>) usageRecordDetailRepository.findAll();
		return urdList;
	}

	@Override
	@Transactional
	public UsageRecordDetail findUsageRecordDetailById(Integer id) {
		return usageRecordDetailRepository.findOne(id);
	}

	@Override
	@Transactional
	public UsageRecordDetail createUsageRecordDetail(UsageRecordDetail usageRecordDetail) {
		return usageRecordDetailRepository.saveAndFlush(usageRecordDetail);
	}

	@Override
	@Transactional
	public UsageRecordDetail updateUsageRecordDetail(UsageRecordDetail usageRecordDetail) {
		return usageRecordDetailRepository.saveAndFlush(usageRecordDetail);
	}

	@Transactional
	void deleteUsageRecordDetail(UsageRecordDetail usageRecordDetail) {
		usageRecordDetailRepository.delete(usageRecordDetail);
	}
	
	@Override
	@Transactional
	public ArrayList<UsageRecordDetail> addUsageRecordDetailList(ArrayList<UsageRecordDetail> usageRecordDetails) {
		for(UsageRecordDetail urDetail : usageRecordDetails) {
			createUsageRecordDetail(urDetail);
		}
		return usageRecordDetails;
	}
	
	// Support Product details page
	@Override
	@Transactional
	public ArrayList<UsageRecordDetail> findTransactionHistoryByProductId(int products_PartID) {
		return usageRecordDetailRepository.findTransactionHistoryByProductId(products_PartID);
	}

}
