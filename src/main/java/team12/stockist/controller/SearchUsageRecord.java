package team12.stockist.controller;

import java.util.ArrayList;
import java.util.Date;

import team12.stockist.model.UsageRecord;
import team12.stockist.model.UsageRecordDetail;

public class SearchUsageRecord {

	private Date startDate;
	private Date endDate;
	private ArrayList<UsageRecord> usageRecords;
	private ArrayList<UsageRecordDetail> usageRecordDetails;

	public SearchUsageRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchUsageRecord(Date startDate, Date endDate, ArrayList<UsageRecord> usageRecords,
			ArrayList<UsageRecordDetail> usageRecordDetails) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.usageRecords = usageRecords;
		this.usageRecordDetails = usageRecordDetails;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<UsageRecord> getUsageRecords() {
		return usageRecords;
	}

	public void setUsageRecords(ArrayList<UsageRecord> usageRecords) {
		this.usageRecords = usageRecords;
	}

	public ArrayList<UsageRecordDetail> getUsageRecordDetails() {
		return usageRecordDetails;
	}

	public void setUsageRecordDetails(ArrayList<UsageRecordDetail> usageRecordDetails) {
		this.usageRecordDetails = usageRecordDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		SearchUsageRecord other = (SearchUsageRecord) obj;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
