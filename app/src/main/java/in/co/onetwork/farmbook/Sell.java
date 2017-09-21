package in.co.onetwork.farmbook;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Sell extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    String title,date,disc,loc;
    DatabaseReference mRoot= FirebaseDatabase.getInstance().getReference();
    DatabaseReference user=mRoot.child("product");
    SharedPreferences sp;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ed1=(EditText)findViewById(R.id.titlep);
        ed2=(EditText)findViewById(R.id.discr);
        ed3=(EditText)findViewById(R.id.loca);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        p=new ProgressDialog(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void post(View v){
        p.setTitle("loading");
        p.setMessage("storing");
        p.show();
        title=ed1.getText().toString();
        disc=ed2.getText().toString();
        loc=ed3.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(new Date());
        if (title.equals("")||disc.equals("")||loc.equals("")||date.equals("")){
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        }else{
            ProductInfo pi=new ProductInfo(title,date,disc,loc,sp.getString("uname",null));
            user.push().setValue(pi).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()) {
                        Toast.makeText(Sell.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        p.dismiss();
                        finish();
                    }
                    else
                        Toast.makeText(Sell.this, "Error in uploading", Toast.LENGTH_SHORT).show();
                }
            });
        }
        p.dismiss();
    }

}
