package com.pamirs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SavedHistroy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "saved_histroy", catalog = "pamirs")
public class SavedHistroy implements java.io.Serializable {

	// Fields

	private Integer savedHistoryId;
	private Users users;
	private String dateTime;
	private String savedName;
	private String docsHistory;

	// Constructors

	/** default constructor */
	public SavedHistroy() {
	}

	/** full constructor */
	public SavedHistroy(Users users, String dateTime, String savedName,
			String docsHistory) {
		this.users = users;
		this.dateTime = dateTime;
		this.savedName = savedName;
		this.docsHistory = docsHistory;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "saved_history_id", unique = true, nullable = false)
	public Integer getSavedHistoryId() {
		return this.savedHistoryId;
	}

	public void setSavedHistoryId(Integer savedHistoryId) {
		this.savedHistoryId = savedHistoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "date_time", length = 45)
	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "saved_name", length = 45)
	public String getSavedName() {
		return this.savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	@Column(name = "docs_history", length = 128)
	public String getDocsHistory() {
		return this.docsHistory;
	}

	public void setDocsHistory(String docsHistory) {
		this.docsHistory = docsHistory;
	}

}