package sg.edu.np.mad.mad_recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> items;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.listItem.setText(items.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DIALOG BOX WHEN TAP ITEM
                final AlertDialog.Builder delete = new AlertDialog.Builder(v.getContext());
                delete.setTitle("Delete");
                delete.setMessage("Are you sure you want to delete\n" + items.get(position));


                //PRESSING OK WILL SET _LoginCard back to visible, blocking _ResetCard
                delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,items.size());
                    }
                });

                delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete.create().show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView listItem;
        CheckBox checkBox;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.listItem);
            checkBox = itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
