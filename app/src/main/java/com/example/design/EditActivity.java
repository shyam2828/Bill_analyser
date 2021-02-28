package com.example.design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.design.Fragments.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditActivity extends AppCompatActivity {

    private EditText company_name,email,phonenumber,profession,country;
    private String scompany_name,semail,sphonenumber,sprofession,scountry;
    private Button update;

    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        company_name=findViewById(R.id.edit_company_name_1);
        email=findViewById(R.id.edit_email);
        phonenumber=findViewById(R.id.edit_phone);
        profession=findViewById(R.id.edit_profession);
        country=findViewById(R.id.edit_country);
        update=findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scompany_name=company_name.getText().toString();
                semail=email.getText().toString();
                sphonenumber=phonenumber.getText().toString();
                sprofession=profession.getText().toString();
                scountry=country.getText().toString();

                if (TextUtils.isEmpty(scompany_name)||TextUtils.isEmpty(semail)||TextUtils.isEmpty(sphonenumber)||TextUtils.isEmpty(sprofession)||TextUtils.isEmpty(scountry))
                {
                    Toast.makeText(EditActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    update_database(scompany_name,semail,sphonenumber,sprofession,scountry);
                }
            }
        });







    }

    private void update_database(String scompany_name, String semail, String sphonenumber, String sprofession, String scountry) {


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = firebaseUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Profile").child(userid);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", userid);
        hashMap.put("company_name", scompany_name);
        hashMap.put("email", semail);
        hashMap.put("phone_number", sphonenumber);
        hashMap.put("profession", sprofession);
        hashMap.put("country", scountry);


        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(EditActivity.this, "You can't register with this email or password", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}





