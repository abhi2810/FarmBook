package in.co.onetwork.farmbook;

/**
 * Created by abhi on 16/9/17.
 */

public class ProductInfo {
    String title;
    String date;
    String disc;
    String location;
    String user;
    ProductInfo(){
        title="";
        date="";
        disc="";
        location="";
        user="";
    }
    ProductInfo(String a,String b,String c,String d,String e){
        title=a;
        date=b;
        disc=c;
        location=d;
        user=e;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
