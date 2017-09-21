package in.co.onetwork.farmbook;

/**
 * Created by abhi on 21/9/17.
 */

public class Order {
    String orderno,title,loc,username,date,by;
    Order(String orderno,String title,String loc,String username,String date,String by){
        this.orderno=orderno;
        this.title=title;
        this.loc=loc;
        this.username=username;
        this.date=date;
        this.by=by;
    }
    Order(){

    }
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getDate() {
        return date;
    }

    public String getLoc() {
        return loc;
    }

    public String getOrderno() {
        return orderno;
    }
    public String getUsername() {
        return username;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}
