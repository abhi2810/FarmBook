package in.co.onetwork.farmbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Buy extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<ProductInfo> list;
    RecyclerView recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("product");
        list=new ArrayList<ProductInfo>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ProductInfo productInfo=dataSnapshot1.getValue(ProductInfo.class);
                    ProductInfo productInfo1=new ProductInfo();
                    productInfo1.setTitle(productInfo.getTitle());
                    productInfo1.setDate(productInfo.getDate());
                    productInfo1.setDisc(productInfo.getDisc());
                    productInfo1.setLocation(productInfo.getLocation());
                    list.add(productInfo1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Buy.this, "Error in data retrieval!"+databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void refresh(View v){
        if(list.isEmpty())
            Toast.makeText(this, "No element found", Toast.LENGTH_SHORT).show();
        else {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, Buy.this);
            RecyclerView.LayoutManager recyce = new LinearLayoutManager(Buy.this);
            recycle.setLayoutManager(recyce);
            recycle.setItemAnimator(new DefaultItemAnimator());
            recycle.setAdapter(recyclerAdapter);
        }
    }
}
