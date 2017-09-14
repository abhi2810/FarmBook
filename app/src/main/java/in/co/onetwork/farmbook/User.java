package in.co.onetwork.farmbook;

/**
 * Created by abhi on 14/9/17.
 */

public class User {
    String uname;
    String name;
    String pass;
    String city;
    String uid;
    String ques;
    String ans;
    String contact;

    public String getUname() {
        return uname;
    }

    public void setUname(String userId) {
        this.uname = userId;
    }
    public String getName() {
        return name;
    }

    public void setName(String userId) {
        this.name = userId;
    }public String getPass() {
        return pass;
    }

    public void setPass(String userId) {
        this.pass = userId;
    }public String getCity() {
        return city;
    }

    public void setCity(String userId) {
        this.city = userId;
    }public String getUid() {
        return uid;
    }

    public void setUid(String userId) {
        this.uid = userId;
    }public String getQues() {
        return ques;
    }

    public void setQues(String userId) {
        this.ques = userId;
    }public String getAns() {
        return ans;
    }

    public void setAns(String userId) {
        this.ans = userId;
    }public String getContact() {
        return contact;
    }

    public void setContact(String userId) {
        this.contact = userId;
    }
    User(String a,String b,String c,String d,String e,String f,String g, String h){
        uname=a;
        name=b;
        pass=c;
        city=d;
        uid=e;
        ques=f;
        ans=g;
        contact=h;
    }
}
