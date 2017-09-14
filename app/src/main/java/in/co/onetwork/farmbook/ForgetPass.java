package in.co.onetwork.farmbook;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgetPass extends AppCompatActivity {
    EditText un,sa,np,cnp;
    TextView sq;
    String uname,cpass,cnfpass,ans,ques;
    ProgressDialog p;
    DatabaseReference mRoot= FirebaseDatabase.getInstance().getReference();
    DatabaseReference user=mRoot.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        un=(EditText)findViewById(R.id.unamer);
        sa=(EditText)findViewById(R.id.san);
        np=(EditText)findViewById(R.id.npass);
        cnp=(EditText)findViewById(R.id.ncpass);
        sq=(TextView)findViewById(R.id.squ);
        p=new ProgressDialog(this);
        p.setTitle("Please wait");
        p.setMessage("Loading");
    }
    public void get(View v){
        p.show();
        uname=un.getText().toString();
        if(uname.equals("")){
            Toast.makeText(this, "Enter Username to get question!!", Toast.LENGTH_SHORT).show();
        }else{
            user.child(uname).child("ques").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    p.dismiss();
                    ques=dataSnapshot.getValue(String.class);
                    sq.setText(ques);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    public void reset(View v){
        ans=sa.getText().toString();
        cpass=np.getText().toString();
        cnfpass=cnp.getText().toString();
        if(uname.equals("")||ans.equals("")||cpass.equals("")||cnfpass.equals("")||ques.equals("")){
            Toast.makeText(this, "Field/s can't be empty.", Toast.LENGTH_SHORT).show();
        }else{
            user.child(uname).child("ans").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String ansq=dataSnapshot.getValue(String.class);
                    if(ansq.equals(ans)){
                        if(cpass.equals(cnfpass)&&cpass.length()>=8){
                            user.child(uname).child("pass").setValue(cpass);
                            Toast.makeText(ForgetPass.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else
                            Toast.makeText(ForgetPass.this, "Passwords don't match or is less than 8 characters.", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(ForgetPass.this, "Incorrect Answer!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
