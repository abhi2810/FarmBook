package in.co.onetwork.farmbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }
    public void buy(View v){
        startActivity(new Intent(Homescreen.this,Buy.class));
    }
    public void sell(View v){
        startActivity(new Intent(Homescreen.this,Sell.class));
    }
    public void requests(View v){
        startActivity(new Intent(Homescreen.this,ViewOrders.class));
    }
}
