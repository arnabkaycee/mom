package mom.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

@SerializedName("val")
@Expose		
private String val;

/**
* 
* @return
* The val
*/
public String getVal() {
return val;
}

/**
* 
* @param val
* The val
*/
public void setVal(String val) {
this.val = val;
}


}