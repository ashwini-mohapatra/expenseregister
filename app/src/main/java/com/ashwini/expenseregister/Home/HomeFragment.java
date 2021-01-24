package com.ashwini.expenseregister.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashwini.expenseregister.Model.ExchangeRateModel.Exchange;
import com.ashwini.expenseregister.Model.TransactionModel.TransactionShowModel;
import com.ashwini.expenseregister.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();


        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TransactionShowModel transactionShowModel=snapshot.getValue(TransactionShowModel.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        String uid;
        databaseReference.child("Expense Register").child("").addValueEventListener(valueEventListener);
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