package in.co.onetwork.farmbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mRoot= FirebaseDatabase.getInstance().getReference();
    DatabaseReference user=mRoot.child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View v){
        DatabaseReference userauth=user.child(((EditText)findViewById(R.id.uname)).getText().toString());
        userauth.child("pass").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pass=dataSnapshot.getValue(String.class);
                if(pass!=null) {
                    if ((pass).equals(((EditText) findViewById(R.id.pass)).getText().toString())) {
                        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Homescreen.class);
                        startActivity(i);
                    } else
                        Toast.makeText(MainActivity.this, "Wrong password.", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "User doesn't exist.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void sign(View v){
        Intent i=new Intent(this,Signup.class);
        startActivity(i);
    }
}
//