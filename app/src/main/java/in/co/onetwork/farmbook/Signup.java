package in.co.onetwork.farmbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText useri,pass;
    DatabaseReference mRoot= FirebaseDatabase.getInstance().getReference();
    DatabaseReference user=mRoot.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        useri=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
    }
    public void register(View v){
        user.child(useri.getText().toString()).setValue(pass.getText().toString());
    }
}
