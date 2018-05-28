package team12.stockist.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team12.stockist.controller.Cart;
import team12.stockist.controller.CartItem;
import team12.stockist.model.Product;
import team12.stockist.model.UsageRecord;

import team12.stockist.model.User;

import team12.stockist.model.UsageRecordDetail;

import team12.stockist.repository.UsageRecordRepository;

@Service
public class UsageRecordServiceImpl implements UsageRecordService {

	@Resource
	UsageRecordRepository usageRecordRepository;

	@Autowired
	ProductService productService;

	@Override
	@Transactional
	public ArrayList<UsageRecord> findAllUsageRecord() {
		ArrayList<UsageRecord> usageRecords = (ArrayList<UsageRecord>) usageRecordRepository.findAll();
		return usageRecords;
	}

	@Override
	@Transactional
	public UsageRecord findUsageRecordById(String Id) {
		return usageRecordRepository.findOne(Id);
	}

	@Override
	@Transactional
	public UsageRecord createUsageRecord(UsageRecord usageRecord) {
		return usageRecordRepository.saveAndFlush(usageRecord);
	}

	@Override
	@Transactional
	public UsageRecord updateUsageRecord(UsageRecord usageRecord) {
		return usageRecordRepository.saveAndFlush(usageRecord);
	}

	@Override
	@Transactional
	public void deleteUsageRecord(UsageRecord usageRecord) {
		usageRecordRepository.delete(usageRecord);
	}

	@Override
	@Transactional
	public ArrayList<UsageRecord> findUsageRecordHistory(int products_PartID) {
		return usageRecordRepository.findUsageRecordHistory(products_PartID);
	}
	
	@Override
	@Transactional
	public ArrayList<UsageRecord> findUsageRecordHistoryByDate(int pid, String startdate, String enddate)
	{
		return usageRecordRepository.findTransactionHistoryByDateRange(pid, startdate, enddate);
	}


	


	@Override
	@Transactional
	public ArrayList<UsageRecord> findUsageRecordByUserId(int Id) {
		return usageRecordRepository.findUsageRecordByUserId(Id);
	}
	@Transactional
	public boolean usageRecordisNotDeletable(User user) {
		if (!findUsageRecordByUserId((user.getId())).isEmpty())
			return true;
		else
			return false;
	
	}

	

	@Transactional
	public int checkForReOrder(Product product) {
		int reOrderLevel;
		if (product.getUnitsInStock() < product.getReorderLevel()
				&& product.getUnitsOnOrder() < product.getMinReorderQty()) {

			if ((product.getReorderLevel() - product.getUnitsInStock()) >= product.getMinReorderQty()
					&& product.getUnitsOnOrder() < product.getMinReorderQty()) {
				reOrderLevel = (product.getReorderLevel() - product.getUnitsInStock());
			} else {
				reOrderLevel = product.getMinReorderQty();
			}
		} else {
			reOrderLevel = 0;
		}

		return reOrderLevel;
	}

	@Transactional
	public ArrayList<CartItem> checkStockAvailable(Cart cart) {
		ArrayList<CartItem> noStockList = new ArrayList<CartItem>();
		for (CartItem cartitem : cart.getCartItemList()) {
			Product product = productService.findProductById(cartitem.getProduct().getPartID());
			if (product.getUnitsInStock() < cartitem.getQuantity()) {
				noStockList.add(cartitem);
			}
		}
		return noStockList;
	}
	
	@Transactional
	public ArrayList<UsageRecordDetail> checkoutCartDetails (Cart cart) {
		ArrayList<UsageRecordDetail> usageRecordDetails = new ArrayList<UsageRecordDetail>();
		
		for (CartItem cartItem : cart.getCartItemList()) {
			UsageRecordDetail usageRecordDetail = new UsageRecordDetail();
			usageRecordDetail.setTransId(cart.getCartId());
			usageRecordDetail.setProductPartId(cartItem.getProduct().getPartID());
			usageRecordDetail.setUsedQty(cartItem.getQuantity());
			usageRecordDetails.add(usageRecordDetail);
			// Check for re-ordering here...
			Product product = productService.findProductById(cartItem.getProduct().getPartID());
			product.setUnitsInStock(product.getUnitsInStock() - cartItem.getQuantity());
			product.setUnitsOnOrder(product.getUnitsOnOrder() + checkForReOrder(product));
			productService.updateProduct(product);
		}
		return usageRecordDetails;
	}


}
