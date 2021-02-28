package com.example.design;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.design.Adapter.RecyclerAdapter;
import com.example.design.Adapter.RemoveClickListner;
import com.example.design.Model.Bill;
import com.example.design.Model.RecyclerData;
import com.example.design.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class BillActivity extends Activity implements RemoveClickListner {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private DatabaseReference reference,reference1;
    private FirebaseUser firebaseUser;
    CircleImageView profile_image;
    TextView username;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem,btncalculate,review;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    TextView total;
    Intent intent;
    String pass_total;
    String title = "";
    String description = "";
    String apple="apple",banana="banana",mango="mango";
    String r_apple="50",r_banana="30",r_mango="60";
    int apple_num1,apple_num2,total_apple;
    int banana_num1,banana_num2,total_banana;
    int mango_num1,mango_num2,total_mango;
    int sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        mRecyclerView = (RecyclerView) findViewById(R.id.fill_items);
        mRecyclerAdapter = new RecyclerAdapter(myList,this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        total=findViewById(R.id.total);

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);



        btncalculate=findViewById(R.id.calculate);
        review=findViewById(R.id.review);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);

        intent = getIntent();
        final String userid = intent.getStringExtra("userid");

        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                username.setText(user.getUsername());
                if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(getApplicationContext()).load(user.getImageURL()).into(profile_image);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                description = etDescription.getText().toString();

                if (title.matches("")) {
                    Toast.makeText(v.getContext(), "You did not enter a Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (description.matches("")) {
                    Toast.makeText(v.getContext(), "You did not enter a description", Toast.LENGTH_SHORT).show();
                    return;
                }






                RecyclerData mLog = new RecyclerData();







                if (title.equals(apple))
                {
                    apple_num1=Integer.parseInt(etDescription.getText().toString());
                    apple_num2=Integer.parseInt(r_apple);
                    total_apple=apple_num1*apple_num2;
                    mLog.setTax(r_apple);
                    mLog.setRate(Integer.toString(total_apple));
                }
                if (title.equals(banana))
                {
                    banana_num1=Integer.parseInt(etDescription.getText().toString());
                    banana_num2=Integer.parseInt(r_banana);
                    total_banana=banana_num1*banana_num2;
                    mLog.setTax(r_banana);
                    mLog.setRate(Integer.toString(total_banana));
                }
                if (title.equals(mango))
                {
                    mango_num1=Integer.parseInt(etDescription.getText().toString());
                    mango_num2=Integer.parseInt(r_mango);
                    total_mango=mango_num1*mango_num2;
                    mLog.setTax(r_mango);
                    mLog.setRate(Integer.toString(total_mango));
                }
                mLog.setTitle(title);
                mLog.setDescription(description);
                myList.add(mLog);
                mRecyclerAdapter.notifyData(myList);
                etTitle.setText("");
                etDescription.setText("");
            }
        });
        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = total_apple+total_banana+total_mango;
                total.setText(Integer.toString(sum));

            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BillActivity.this, DisplayBillActivity.class);
                pass_total=total.getText().toString();
                i.putExtra("total",pass_total);
                startActivity(i);
            }
        });




    }




    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }
}
