package in.co.onetwork.farmbook;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import in.co.onetwork.farmbook.User;

public class Signup extends AppCompatActivity {
    EditText useri,pass,city,cpass,UIDa,ans,name,contact;
    String uname,passw,citys,uidn,qans,names,con,ques;
    MaterialBetterSpinner spin;
    DatabaseReference mRoot= FirebaseDatabase.getInstance().getReference();
    DatabaseReference user=mRoot.child("user");
    String a[]={"What is your mother's first name?","What is your Cow's name?","What is your favourite crop?"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        useri=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        spin=(MaterialBetterSpinner)findViewById(R.id.sq);
        city=(EditText)findViewById(R.id.city);
        cpass=(EditText)findViewById(R.id.cnfpass);
        UIDa=(EditText)findViewById(R.id.UID);
        ans=(EditText)findViewById(R.id.sans);
        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.contact);
        ArrayAdapter<String> array=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,a);
        spin.setAdapter(array);
    }
    public void register(View v){
        uname=useri.getText().toString();
        passw=pass.getText().toString();
        citys=city.getText().toString();
        uidn=UIDa.getText().toString();
        qans=ans.getText().toString();
        names=name.getText().toString();
        con=contact.getText().toString();
        ques=spin.getText().toString();
        if(uname.equals("")||passw.equals("")||ques.equals("")||citys.equals("")||cpass.getText().toString().equals("")||uidn.equals("")||
                qans.equals("")||con.equals("")) {
            Toast.makeText(this, "Field/s is/are empty.", Toast.LENGTH_SHORT).show();
        }else {
            user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(uname)) {
                        Toast.makeText(Signup.this, "Username already taken.", Toast.LENGTH_SHORT).show();
                    }else{
                        if (passw.equals(cpass.getText().toString()) && passw.length() >= 8) {
                            User u = new User(uname, names, passw, citys, uidn, ques, qans, con);
                            u.setUname(uname);
                            user.child(uname).setValue(u).addOnCompleteListener(Signup.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isComplete()) {
                                        Toast.makeText(Signup.this, "Signed up!", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(Signup.this, "Error" + task.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(Signup.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
