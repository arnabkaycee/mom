package mom.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionItem {

@SerializedName("actionItem")
@Expose	
private String actionItem;

@SerializedName("responsibility")
@Expose
private List<String> responsibility = new ArrayList<String>();

@SerializedName("targetDate")
@Expose
private Date targetDate;

@SerializedName("revisedTargetDate")
@Expose
private Date revisedTargetDate;

@SerializedName("actualEndDate")
@Expose
private Date actualEndDate;

@SerializedName("status")
@Expose
private Status status;

@SerializedName("remarks")
@Expose
private String remarks;

/**
* 
* @return
* The actionItem
*/
public String getActionItem() {
return actionItem;
}

/**
* 
* @param actionItem
* The actionItem
*/
public void setActionItem(String actionItem) {
this.actionItem = actionItem;
}

/**
* 
* @return
* The responsibility
*/
public List<String> getResponsibility() {
return responsibility;
}

/**
* 
* @param responsibility
* The responsibility
*/
public void setResponsibility(List<String> responsibility) {
this.responsibility = responsibility;
}

/**
* 
* @return
* The targetDate
*/
public Date getTargetDate() {
return targetDate;
}

/**
* 
* @param targetDate
* The targetDate
*/
public void setTargetDate(Date targetDate) {
this.targetDate = targetDate;
}

/**
* 
* @return
* The revisedTargetDate
*/
public Date getRevisedTargetDate() {
return revisedTargetDate;
}

/**
* 
* @param revisedTargetDate
* The revisedTargetDate
*/
public void setRevisedTargetDate(Date revisedTargetDate) {
this.revisedTargetDate = revisedTargetDate;
}

/**
* 
* @return
* The actualEndDate
*/
public Date getActualEndDate() {
return actualEndDate;
}

/**
* 
* @param actualEndDate
* The actualEndDate
*/
public void setActualEndDate(Date actualEndDate) {
this.actualEndDate = actualEndDate;
}

/**
* 
* @return
* The status
*/
public Status getStatus() {
return status;
}

/**
* 
* @param status
* The status
*/
public void setStatus(Status status) {
this.status = status;
}

/**
* 
* @return
* The remarks
*/
public String getRemarks() {
return remarks;
}

/**
* 
* @param remarks
* The remarks
*/
public void setRemarks(String remarks) {
this.remarks = remarks;
}

}
