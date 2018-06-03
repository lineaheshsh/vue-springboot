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
	private String scenario;
	private String[] fields;
	private String query;
	private String logInfo;
	private String hilightTxt;
	private String orderBy;
	private boolean flag;
	
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}	
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}	
}
