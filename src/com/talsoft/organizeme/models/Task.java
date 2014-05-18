package com.talsoft.organizeme.models;

import org.joda.time.DateTime;

public class Task 
{
	private String goal;
	private String place;
	private DateTime beginDate;
	private DateTime endDate;
	
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public DateTime getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(DateTime beginDate) {
		this.beginDate = beginDate;
	}
	public DateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	
	
}
