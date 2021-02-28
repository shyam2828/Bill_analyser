package com.example.design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DisplayBillActivity extends AppCompatActivity {


    String get_total;
    TextView total;
    Button sending,store;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bill);

        total=findViewById(R.id.get_total);
        sending=findViewById(R.id.sending);
        store=findViewById(R.id.action2);

        Intent intent=getIntent();
        get_total=intent.getStringExtra("total");
        total.setText(get_total);





        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_total = total.getText().toString();

                if (TextUtils.isEmpty(txt_total)) {
                    Toast.makeText(DisplayBillActivity.this, "check the billing content", Toast.LENGTH_SHORT).show();
                } else {
                    update_bill(txt_total);
                }
            }
        });
        sending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayBillActivity.this,MessageActivity.class));
            }
        });












    }
    private void update_bill(String txt_total) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("bills").child(userid);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", userid);
        hashMap.put("total", txt_total);

        reference.setValue(hashMap);
    }
}
