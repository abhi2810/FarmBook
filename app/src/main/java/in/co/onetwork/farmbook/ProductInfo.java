package in.co.onetwork.farmbook;

/**
 * Created by abhi on 16/9/17.
 */

public class ProductInfo {
    String title;
    String date;
    String disc;
    String location;
    ProductInfo(){
        title="";
        date="";
        disc="";
        location="";
    }
    ProductInfo(String a,String b,String c,String d){
        title=a;
        date=b;
        disc=c;
        location=d;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public String getDisc(){
        return disc;
    }
    public String getLocation(){
        return location;
    }
    public void setTitle(String a){
        title=a;
    }
    public void setDate(String a){
        date=a;
    }
    public void setDisc(String a){
        disc=a;
    }
    public void setLocation(String a){
        location=a;
    }
}
