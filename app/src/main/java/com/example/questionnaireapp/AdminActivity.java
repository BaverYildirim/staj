package com.example.questionnaireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {
    //global variable
    //google Sign In
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView emailGoogleLoginId;
    Button signOutButtonId;
    private Toolbar myToolBarId;

    private void signOutMethod() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                //güvenli çıkış yaptıkran sonra nere gidelim. ?
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //start
        signOutButtonId=findViewById(R.id.signOutButtonId);
        // gso ve gsc instance
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        //google SingInAccount
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            String name = signInAccount.getDisplayName();
            String email = signInAccount.getEmail();
            //nameGoogleLoginId.setText(name);
            emailGoogleLoginId.setText(email);
            signOutButtonId.setVisibility(View.VISIBLE);
            signOutButtonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signOutMethod();
                }
            });
        }



        myToolBarId = findViewById(R.id.myToolBarId);
        // myToolBarId.setNavigationIcon(R.drawable.logo);
        setSupportActionBar(myToolBarId);



    }//end onCreate
}//end AdminActivity