package in.co.onetwork.farmbook;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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

public class ViewOrders extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Order> list;
    RecyclerView recycle;
    SharedPreferences sp;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("order");
        list=new ArrayList<Order>();
        sp=getSharedPreferences("login",MODE_PRIVATE);
        uname=sp.getString("uname",null);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Order order=dataSnapshot1.getValue(Order.class);
                    if(order.getBy().equals(uname)) {
                        list.add(order);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewOrders.this, "Error in data retrieval!"+databaseError.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void refresh(View v){
        if(list.isEmpty())
            Toast.makeText(this, "No element found", Toast.LENGTH_SHORT).show();
        else {
            RecyclerAdapter1 recyclerAdapter = new RecyclerAdapter1(list, ViewOrders.this,uname);
            RecyclerView.LayoutManager recyce = new LinearLayoutManager(ViewOrders.this);
            recycle.setLayoutManager(recyce);
            recycle.setItemAnimator(new DefaultItemAnimator());
            recycle.setAdapter(recyclerAdapter);
        }
    }
}
