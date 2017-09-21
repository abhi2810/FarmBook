package in.co.onetwork.farmbook;

/**
 * Created by abhi on 16/9/17.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.MyHoder>{
    DatabaseReference order=FirebaseDatabase.getInstance().getReference().child("order").getRef();
    DatabaseReference product=FirebaseDatabase.getInstance().getReference().child("product").getRef();
    List<Order> list;
    Context context;
    String uname;

    public RecyclerAdapter1(List<Order> list, Context context,String uname) {
        this.list = list;
        this.context = context;
        this.uname=uname;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card1,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        final Order mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.date.setText(mylist.getDate());
        holder.disc.setText(mylist.getUsername());
        holder.loc.setText(mylist.getLoc());
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.child(mylist.getOrderno()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Order deleted.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView title,date,disc,loc;
        FloatingActionButton order;


        public MyHoder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date= (TextView) itemView.findViewById(R.id.date);
            disc= (TextView) itemView.findViewById(R.id.disc);
            loc=(TextView)itemView.findViewById(R.id.loc);
            order=(FloatingActionButton) itemView.findViewById(R.id.order);
        }
    }

}