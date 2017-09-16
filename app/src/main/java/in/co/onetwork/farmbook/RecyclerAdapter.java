package in.co.onetwork.farmbook;

/**
 * Created by abhi on 16/9/17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<ProductInfo> list;
    Context context;

    public RecyclerAdapter(List<ProductInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        ProductInfo mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.date.setText(mylist.getDate());
        holder.disc.setText(mylist.getDisc());
        holder.loc.setText(mylist.getLocation());
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


        public MyHoder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date= (TextView) itemView.findViewById(R.id.date);
            disc= (TextView) itemView.findViewById(R.id.disc);
            loc=(TextView)itemView.findViewById(R.id.loc);

        }
    }

}