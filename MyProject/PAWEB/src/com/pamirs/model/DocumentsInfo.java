package com.pamirs.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DocumentsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "documents_info", catalog = "pamirs")
public class DocumentsInfo implements java.io.Serializable {

	// Fields

	private Integer documentId;
	private String articleNumber;
	private String title;
	private String author;
	private Timestamp date;
	private Integer rank;
	private String searchKey;
	private String titleHref;
	private String photoPath;
	private String bookName;
	private String bookLanguage;

	// Constructors

	/** default constructor */
	public DocumentsInfo() {
	}

	/** minimal constructor */
	public DocumentsInfo(String articleNumber, String title, String author,
			Timestamp date, Integer rank, String searchKey) {
		this.articleNumber = articleNumber;
		this.title = title;
		this.author = author;
		this.date = date;
		this.rank = rank;
		this.searchKey = searchKey;
	}

	/** full constructor */
	public DocumentsInfo(String articleNumber, String title, String author,
			Timestamp date, Integer rank, String searchKey, String titleHref,
			String photoPath, String bookName, String bookLanguage) {
		this.articleNumber = articleNumber;
		this.title = title;
		this.author = author;
		this.date = date;
		this.rank = rank;
		this.searchKey = searchKey;
		this.titleHref = titleHref;
		this.photoPath = photoPath;
		this.bookName = bookName;
		this.bookLanguage = bookLanguage;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false)
	public Integer getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	@Column(name = "article_number", nullable = false,unique =true, length = 45)
	public String getArticleNumber() {
		return this.articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	@Column(name = "title", nullable = false, length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false, length = 128)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "date", nullable = false, length = 19)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Column(name = "rank", nullable = false)
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "search_key", nullable = false, length = 45)
	public String getSearchKey() {
		return this.searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Column(name = "title_href", length = 128)
	public String getTitleHref() {
		return this.titleHref;
	}

	public void setTitleHref(String titleHref) {
		this.titleHref = titleHref;
	}

	@Column(name = "photo_path", length = 128)
	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(name = "book_name", length = 128)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "book_language", length = 45)
	public String getBookLanguage() {
		return this.bookLanguage;
	}

	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

}