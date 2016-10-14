/**
 * 
 */
package com.chapman.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	//Transaction ID	Posting Date	Effective Date	Transaction Type	
	//Amount	Check Number	Reference Number	Payee	Memo	Transaction Category	Type	Balance
	private Long id;
	private String transactionId; //Transaction ID
	private Date postingDate; //Posting Date	
	private Date effectiveDate; //Effective Date
	private String transactionType;//Transaction Type	
	private Double amount; //Amount	
	private Long checkNumber; //Check_Number
	private String referenceNumber; //Reference Number	
	private String payee; //Payee							
	private String memo; //Memo				
	private String extDesc; //added for bulk updates
	private String transactionCategory; //Transaction Category		
	private String type; //Type	
	private Double balance; //Balance	
	private Category category; //category Id
	
	
	public RawBankCheckingData() {
		// TODO Auto-generated constructor stub
	}
	
	public RawBankCheckingData(String transactionId, Date postingDate, Date effectiveDate, String transactionType, Double amount,
			Long checkNumber, String referenceNumber, String payee, String memo, String extDesc, String transactionCategory, String type, Double balance) {
		this.transactionId = transactionId;
		this.postingDate = postingDate;
		this.effectiveDate=effectiveDate;
		this.transactionType = transactionType;
		this.amount = amount;
		this.checkNumber = checkNumber;
		this.referenceNumber = referenceNumber;
		this.payee = payee;
		this.memo = memo;
		this.extDesc=extDesc;
		this.transactionCategory = transactionCategory;
		this.type = type;
		this.balance = balance;
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
	 * @return the postingDate
	 */
	@Column(name = "posting_date")
	@Temporal(TemporalType.DATE)
	@Field
	public Date getPostingDate() {
		return postingDate;
	}

	/**
	 * @return the effectiveDate
	 */
	@Column(name = "effective_date")
	@Temporal(TemporalType.DATE)
	@Field
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @return the transactionType
	 */
	@Column(name = "trans_type", length = 75)
    @Field
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @return the referenceNumber
	 */
	@Column(name = "ref_number", nullable = false, length = 18, unique = true)
    @Field
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * @return the payee
	 */
	@Column(name = "payee", length = 32)
    @Field
	public String getPayee() {
		return payee;
	}

	/**
	 * @return the memo
	 */
	@Column(name = "memo", length =96)
    @Field
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @return the memo
	 */
	@Column(name = "ext_desc", length =96)
    @Field
	public String getExtDesc() {
		return extDesc;
	}

	/**
	 * @return the transactionCategory
	 */
	@Column(name = "trans_category", length = 50)
    @Field
	public String getTransactionCategory() {
		return transactionCategory;
	}

	/**
	 * @return the type
	 */
	@Column(name = "type", length = 32)
    @Field
	public String getType() {
		return type;
	}

	/**
	 * @return the transactionId
	 */
	@Column(name="trans_id", length=64, nullable=false)
	@Field
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @return the amount
	 */
	@Column(name="amount", precision=8, scale=2)
	public Double getAmount() {
		return amount;
	}

	/**
	 * @return the checkNumber
	 */
	@Column(name="check_number")
	public Long getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @return the balance
	 */
	@Column (name="balance", precision = 8, scale = 2 )
	public Double getBalance() {
		return balance;
	}

	/**
	 * @return the categoryId
	 */
	@ManyToOne
    @JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(Long checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param postingDate the postingDate to set
	 */
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @param referenceNumber the referenceNumber to set
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @param memo the extDesc to set
	 */
	public void setExtDesc(String extDesc) {
		this.extDesc = extDesc;
	}

	/**
	 * @param transactionCategory the transactionCategory to set
	 */
	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RawBankCheckingData [transactionId=" + transactionId + ", postingDate=" + postingDate
				+ ", effectiveDate=" + effectiveDate + ", transactionType=" + transactionType + ", amount=" + amount
				+ ", checkNumber=" + checkNumber + ", referenceNumber=" + referenceNumber + ", payee=" + payee
				+ ", memo=" + memo + ", extDesc = "+extDesc+ ", transactionCategory=" + transactionCategory + ", type=" + type + ", balance="
				+ balance + ", category=" + category + "]";
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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((checkNumber == null) ? 0 : checkNumber.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((extDesc == null) ? 0 : extDesc.hashCode());
		result = prime * result + ((payee == null) ? 0 : payee.hashCode());
		result = prime * result + ((postingDate == null) ? 0 : postingDate.hashCode());
		result = prime * result + ((referenceNumber == null) ? 0 : referenceNumber.hashCode());
		result = prime * result + ((transactionCategory == null) ? 0 : transactionCategory.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (checkNumber == null) {
			if (other.checkNumber != null)
				return false;
		} else if (!checkNumber.equals(other.checkNumber))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (extDesc == null) {
			if (other.extDesc != null)
				return false;
		} else if (!extDesc.equals(other.extDesc))
			return false;
		if (payee == null) {
			if (other.payee != null)
				return false;
		} else if (!payee.equals(other.payee))
			return false;
		if (postingDate == null) {
			if (other.postingDate != null)
				return false;
		} else if (!postingDate.equals(other.postingDate))
			return false;
		if (referenceNumber == null) {
			if (other.referenceNumber != null)
				return false;
		} else if (!referenceNumber.equals(other.referenceNumber))
			return false;
		if (transactionCategory == null) {
			if (other.transactionCategory != null)
				return false;
		} else if (!transactionCategory.equals(other.transactionCategory))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
}
