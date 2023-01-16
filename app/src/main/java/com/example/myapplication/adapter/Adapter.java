package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.datamodel.ItemDataModel;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    View rootView;

    Context context;

    private List<ItemDataModel> dataSet;

    // Usered Entered Expense amt will be stored in ExpAmtArray
    ArrayList<String> ExpAmtArray = new ArrayList<String>();

    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    TextView textviewTotalExpense;

    //constructor with dataSet passed from MainActivity when Adapter is called
    public Adapter(List<ItemDataModel> dataSet) {
        this.dataSet = dataSet;
    }

    //Recyclerview ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expensesName;
        SeekBar expHeld;
        Button imageButton;
        TextView qtyText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // finding view by view id.
            expensesName = (TextView) itemView.findViewById(R.id.textViewExpName);
            expHeld = (SeekBar) itemView.findViewById(R.id.seekBar);
            imageButton = (Button) itemView.findViewById(R.id.ExpBimageSelect);
            qtyText = (TextView) itemView.findViewById(R.id.seekText);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view for recyclerview
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_prod_list, parent, false);

        context = parent.getContext();
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        textviewTotalExpense = (TextView) rootView.findViewById(R.id.totalExpense);
        //attach view to MyViewHolder
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //initialize the view with view holder
        TextView expensesName = holder.expensesName;
        SeekBar expHeld = holder.expHeld;
        Button imageButton = holder.imageButton;
        TextView qtyText = holder.qtyText;
        expensesName.setText(dataSet.get(position).getExpenseName());

        // EditText with TextWatcher Listens each time when user enter value in edittext in recyclerview
        expHeld.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    qtyText.setText(String.valueOf(i));
                }

                Log.i("data changed: ","Item Seek changed: "+dataSet.get(position).getExpenseName());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
