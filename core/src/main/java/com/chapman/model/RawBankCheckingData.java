/**
 * 
 */
package com.chapman.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * @author or0189783
 *
 */
@Entity
@Table(name = "raw_data")
@Indexed
public class RawBankCheckingData extends BaseObject implements Serializable {

	private static final long serialVersionUID = 2221751117227020663L;

	private Long id;
	private Date transactionDate; //Transaction_Date	
	private String transactionId; //Transaction_ID	
	private String transDesc; //TranDesc	
	private String extDesc; //ExtDesc							
	private String description; //Description							
	private Double fee; //Fee		
	private Double amount; //Amount	
	private Double otherCharges; //Other_Charges	
	private Double balance; //Balance	
	private Date postDate; //Post_Date	
	private Long checkNumber; //Check_Number
	
	
	public RawBankCheckingData() {
		// TODO Auto-generated constructor stub
	}
	
	public RawBankCheckingData(Date transactionDate, String transactionId, String transDesc, String extDesc,
			String description, Double fee, Double amount, Double otherCharges, Double balance, Date postDate,
			Long checkNumber) {
		this.transactionDate = transactionDate;
		this.transactionId = transactionId;
		this.transDesc = transDesc;
		this.extDesc = extDesc;
		this.description = description;
		this.fee = fee;
		this.amount = amount;
		this.otherCharges = otherCharges;
		this.balance = balance;
		this.postDate = postDate;
		this.checkNumber = checkNumber;
	}



	/**
	 * @return the id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the transactionDate
	 */
	@Column(name = "trans_date")
	@Temporal(TemporalType.DATE)
	@Field
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the transactionId
	 */
    @Column(name = "trans_id", nullable = false, length = 12, unique = true)
    @Field
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the transDesc
	 */
    @Column(name = "trans_desc", length = 50)
    @Field
	public String getTransDesc() {
		return transDesc;
	}

	/**
	 * @param transDesc the transDesc to set
	 */
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}

	/**
	 * @return the extDesc
	 */
    @Column(name = "ext_desc", length = 100)
    @Field
	public String getExtDesc() {
		return extDesc;
	}

	/**
	 * @param extDesc the extDesc to set
	 */
	public void setExtDesc(String extDesc) {
		this.extDesc = extDesc;
	}

	/**
	 * @return the description
	 */
    @Column(name = "description", length = 100)
    @Field
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fee
	 */
	@Column (name="fee", precision = 8, scale = 2 )
	public Double getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * @return the amount
	 */
	@Column (name="amount", precision = 8, scale = 2 )
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the otherCharges
	 */
    @Column(name = "other_charges", precision = 8, scale = 2 )
	public Double getOtherCharges() {
		return otherCharges;
	}

	/**
	 * @param otherCharges the otherCharges to set
	 */
	public void setOtherCharges(Double otherCharges) {
		this.otherCharges = otherCharges;
	}

	/**
	 * @return the balance
	 */
	@Column ( name="balance", precision = 8, scale = 2 )
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the postDate
	 */
    @Column(name = "post_date")
    @Temporal(TemporalType.DATE)
    @Field
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return the checkNumber
	 */
    @Column(name = "check_number")
    @Field
	public Long getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(Long checkNumber) {
		this.checkNumber = checkNumber;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result
				+ ((checkNumber == null) ? 0 : checkNumber.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((extDesc == null) ? 0 : extDesc.hashCode());
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result
				+ ((otherCharges == null) ? 0 : otherCharges.hashCode());
		result = prime * result
				+ ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result
				+ ((transDesc == null) ? 0 : transDesc.hashCode());
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawBankCheckingData other = (RawBankCheckingData) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (checkNumber == null) {
			if (other.checkNumber != null)
				return false;
		} else if (!checkNumber.equals(other.checkNumber))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (extDesc == null) {
			if (other.extDesc != null)
				return false;
		} else if (!extDesc.equals(other.extDesc))
			return false;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
			return false;
		if (otherCharges == null) {
			if (other.otherCharges != null)
				return false;
		} else if (!otherCharges.equals(other.otherCharges))
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (transDesc == null) {
			if (other.transDesc != null)
				return false;
		} else if (!transDesc.equals(other.transDesc))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RawBankCheckingData [transactionDate=" + transactionDate
				+ ", transactionId=" + transactionId
				+ ", transDesc=" + transDesc + ", extDesc=" + extDesc
				+ ", description=" + description + ", fee=" + fee + ", amount="
				+ amount + ", otherCharges=" + otherCharges + ", balance="
				+ balance + ", postDate=" + postDate + ", checkNumber="
				+ checkNumber + "]";
	}

}
