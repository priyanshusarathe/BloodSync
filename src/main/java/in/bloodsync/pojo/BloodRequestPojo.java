package in.bloodsync.pojo;

import java.util.Date;

public class BloodRequestPojo {
	private int requestId;
	private String hospitalName;
	private String bloodType;
	private String urgency;
	private String status;
	private Date requestDate;
	private int requestedUnits;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getRequestedUnits() {
		return requestedUnits;
	}
	public void setRequestedUnits(int requestedUnits) {
		this.requestedUnits = requestedUnits;
	}
	

}
