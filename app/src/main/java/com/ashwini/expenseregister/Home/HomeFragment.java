package com.ashwini.expenseregister.Home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ashwini.expenseregister.Adapter.TransactionAdapter;
import com.ashwini.expenseregister.Model.ExchangeRateModel.Exchange;
import com.ashwini.expenseregister.Model.TransactionModel.TransactionShowModel;
import com.ashwini.expenseregister.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    TextView t1,t2,t3;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String type;

    ArrayList<TransactionShowModel> transactionShowModelArrayList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        t1=v.findViewById(R.id.textView7);
        t2=v.findViewById(R.id.textView8);
        t3=v.findViewById(R.id.textView9);
        fab=v.findViewById(R.id.floatingActionButton);
        recyclerView=v.findViewById(R.id.lin4);
        TransactionAdapter transactionAdapter1=new TransactionAdapter(transactionShowModelArrayList,getActivity());
        recyclerView.setAdapter(transactionAdapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        String uid=mAuth.getUid();
        databaseReference.child("Expense Register").child(uid).child("Transactions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    TransactionShowModel transactionShowModel=snapshot.getValue(TransactionShowModel.class);
                    transactionShowModelArrayList.add(transactionShowModel);
                }
                TransactionAdapter transactionAdapter=new TransactionAdapter(transactionShowModelArrayList,getActivity());
                recyclerView.setAdapter(transactionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_transactions);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextInputEditText e1,e2,e3,e4;
                Spinner s1;
                Button b1;
                ImageView i1;
                MaterialTextView t1;

                e1=dialog.findViewById(R.id.tname);
                e2=dialog.findViewById(R.id.tndesc);
                e3=dialog.findViewById(R.id.tnamt);
                s1=dialog.findViewById(R.id.tntype);
                t1=dialog.findViewById(R.id.tndate);
                b1=dialog.findViewById(R.id.bt_follow);
                i1=dialog.findViewById(R.id.bt_close);

                s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        type=parent.getItemAtPosition(position).toString();
                        s1.setSelection(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        s1.setSelection(0);
                    }
                });
                i1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                String[] arraySpinner=new String[]{"Expense","Saving","Income"};
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s1.setAdapter(adapter);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                dialog.show();
            }
        });
        return v;
   }

//   public void getDate(){
//       Calendar cur_calender = Calendar.getInstance();
//       DatePickerDialog datePicker = DatePickerDialog.newInstance(
//               new DatePickerDialog.OnDateSetListener() {
//                   @Override
//                   public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                       Calendar calendar = Calendar.getInstance();
//                       calendar.set(Calendar.YEAR, year);
//                       calendar.set(Calendar.MONTH, monthOfYear);
//                       calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                       long date_ship_millis = calendar.getTimeInMillis();
//                       .setText(Tools.getFormattedDateSimple(date_ship_millis));
//                   }
//               },
//               cur_calender.get(Calendar.YEAR),
//               cur_calender.get(Calendar.MONTH),
//               cur_calender.get(Calendar.DAY_OF_MONTH)
//       );
//       //set dark theme
//       datePicker.setThemeDark(true);
//       datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
//       datePicker.setMinDate(cur_calender);
//       datePicker.show(getFragmentManager(), "Datepickerdialog");
//   }
}