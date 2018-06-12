package com.boot.spring.search.vo;

/** 
 * Dao 관련 Value Object
 * 
 * @author 장진후(Jinhoo.Jang)
 * @team 기술지원팀(Technical Support)
 * @since 2013.06.20
 * @version 1.0
 */
public class SearchVO {
	private String url;
	private String fields;
	private String from;
	private String charset;	
		
	private String query;
	private String logInfo;
	private String hilightTxt;
	private String orderBy;

	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getHilightTxt() {
		return hilightTxt;
	}
	public void setHilightTxt(String hilightTxt) {
		this.hilightTxt = hilightTxt;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
