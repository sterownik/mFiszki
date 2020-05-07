package com.example.mfiszki;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;

import com.example.mfiszki.database.bezpieczenstwo;
import com.example.mfiszki.database.biznes;
import com.example.mfiszki.database.cywilizacja;
import com.example.mfiszki.database.czlowiek;
import com.example.mfiszki.database.emocje;
import com.example.mfiszki.database.handel;
import com.example.mfiszki.database.kultura;
import com.example.mfiszki.database.nauka;
import com.example.mfiszki.database.podroze;
import com.example.mfiszki.database.przyroda;
import com.example.mfiszki.database.sport;
import com.example.mfiszki.database.swiat;
import com.example.mfiszki.database.technika;
import com.example.mfiszki.database.wolny;
import com.example.mfiszki.database.zdrowie;
import com.example.mfiszki.database.photo;
import com.example.mfiszki.database.zyciecodzienne;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Rejestracja extends AppCompatActivity {
    DatabaseReference reff;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    EditText mail, haslo1, haslo2;
    Button register;
    bezpieczenstwo bezpieczenstwo;
    biznes biznes;
    photo photo1;
    cywilizacja cywilizacja;

    czlowiek czlowiek;
    emocje emocje;
    handel handel;
    kultura kultura;
    nauka nauka;
    podroze podroze;
    przyroda przyroda;
    sport sport;
    swiat swiat;
    technika technika;
    wolny wolny;
    zdrowie zdrowie;
    zyciecodzienne zyciecodzienne;
    ImageButton your, straznik, bus, englishman;
    int zdj=-1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    boolean avatar = false;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(Rejestracja.this, Logowanie.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        your = (ImageButton) findViewById(R.id.your);
        straznik = (ImageButton) findViewById(R.id.straznik);
        bus = (ImageButton) findViewById(R.id.bus);
        englishman = (ImageButton) findViewById(R.id.englishman);



        progressDialog = new ProgressDialog(this);
        mail = (EditText) findViewById(R.id.mail);
        haslo1 = (EditText) findViewById(R.id.pass);
        haslo2 = (EditText) findViewById(R.id.pass2);
        your.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zdj=0;
                your.setBackgroundResource(R.drawable.kolodwa);
                straznik.setBackgroundResource(R.drawable.kolo);
                bus.setBackgroundResource(R.drawable.kolo);
                englishman.setBackgroundResource(R.drawable.kolo);
                openFileChooser();
            }
        });
        straznik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zdj=1;
                your.setBackgroundResource(R.drawable.kolo);
                straznik.setBackgroundResource(R.drawable.kolodwa);
                bus.setBackgroundResource(R.drawable.kolo);
                englishman.setBackgroundResource(R.drawable.kolo);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                your.setBackgroundResource(R.drawable.kolo);
                zdj=2;
                straznik.setBackgroundResource(R.drawable.kolo);
                bus.setBackgroundResource(R.drawable.kolodwa);
                englishman.setBackgroundResource(R.drawable.kolo);
            }
        });
        englishman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zdj=3;
                your.setBackgroundResource(R.drawable.kolo);
                straznik.setBackgroundResource(R.drawable.kolo);
                bus.setBackgroundResource(R.drawable.kolo);
                englishman.setBackgroundResource(R.drawable.kolodwa);
            }
        });


        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(your);
            // your.setImageURI(mImageUri);
        }
    }

    private void registeruser() {
        String email = mail.getText().toString().trim();
        String haslo = haslo1.getText().toString().trim();
        String haslo3 = haslo2.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Wpisz maila", Toast.LENGTH_LONG).show();
            return;
        }
        if(zdj==-1)
        {
            Toast.makeText(getApplicationContext(), "Wybierz Avatara", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(haslo) || !haslo.equals(haslo3)) {
            Toast.makeText(getApplicationContext(), "Hasła sie nie zgadzają lub hasło jest puste", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Trwa rejestracja...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, haslo).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Pomyślnie zarejestrowano", Toast.LENGTH_LONG);
                    Intent intent = new Intent(Rejestracja.this, kategorie.class);
                    startActivity(intent);

                    addData();


                } else {
                    Toast.makeText(getApplicationContext(), "Nie zarejestrowano podałeś złego maila za ktrótkie hasło lub dany użytkownik juz istnieje", Toast.LENGTH_LONG);
                }
                progressDialog.dismiss();
            }
        });


    }

    private String getFireExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child("avatar" + "." + getFireExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Upload upload = new Upload("avatar", taskSnapshot.getUploadSessionUri().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "nie wybrałeś zdjęcia", Toast.LENGTH_LONG).show();
        }
    }

    private void addData() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        user = firebaseAuth.getCurrentUser();

        mStorageRef = FirebaseStorage.getInstance().getReference().child("zdjecia").child(user.getUid());
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("zdjecia").child(user.getUid());
        if(zdj==0)
        {
            uploadFile();
        }
        reff = FirebaseDatabase.getInstance().getReference().child("photo").child(user.getUid());
        photo1 = new photo();
        photo1.setPh(zdj);
        reff.setValue(photo1);

        reff = FirebaseDatabase.getInstance().getReference().child("biznes").child(user.getUid());
        biznes = new biznes();
        biznes.setIleslowek(270);
        biznes.setSlowko(1);
        reff.setValue(biznes);

        reff = FirebaseDatabase.getInstance().getReference().child("bezpieczenstwo").child(user.getUid());
        bezpieczenstwo = new bezpieczenstwo();
        bezpieczenstwo.setIleslowek(530);
        bezpieczenstwo.setSlowko(1);
        reff.setValue(bezpieczenstwo);

        reff = FirebaseDatabase.getInstance().getReference().child("cywilizacja").child(user.getUid());
        cywilizacja = new cywilizacja();
        cywilizacja.setIleslowek(697);
        cywilizacja.setSlowko(1);
        reff.setValue(cywilizacja);


        reff = FirebaseDatabase.getInstance().getReference().child("czlowiek").child(user.getUid());
        czlowiek = new czlowiek();
        czlowiek.setIleslowek(503);
        czlowiek.setSlowko(1);
        reff.setValue(czlowiek);

        reff = FirebaseDatabase.getInstance().getReference().child("emocje").child(user.getUid());
        emocje = new emocje();
        emocje.setIleslowek(189);
        emocje.setSlowko(1);
        reff.setValue(emocje);


        reff = FirebaseDatabase.getInstance().getReference().child("handel").child(user.getUid());
        handel = new handel();
        handel.setIleslowek(542);
        handel.setSlowko(1);
        reff.setValue(handel);

        reff = FirebaseDatabase.getInstance().getReference().child("kultura").child(user.getUid());
        kultura = new kultura();
        kultura.setIleslowek(454);
        kultura.setSlowko(1);
        reff.setValue(kultura);

        reff = FirebaseDatabase.getInstance().getReference().child("nauka").child(user.getUid());
        nauka = new nauka();
        nauka.setIleslowek(582);
        nauka.setSlowko(1);
        reff.setValue(nauka);

        reff = FirebaseDatabase.getInstance().getReference().child("podroze").child(user.getUid());
        podroze = new podroze();
        podroze.setIleslowek(280);
        podroze.setSlowko(1);
        reff.setValue(podroze);

        reff = FirebaseDatabase.getInstance().getReference().child("przyroda").child(user.getUid());
        przyroda = new przyroda();
        przyroda.setIleslowek(695);
        przyroda.setSlowko(1);
        reff.setValue(przyroda);

        reff = FirebaseDatabase.getInstance().getReference().child("sport").child(user.getUid());
        sport = new sport();
        sport.setIleslowek(352);
        sport.setSlowko(1);
        reff.setValue(sport);


        reff = FirebaseDatabase.getInstance().getReference().child("swiat").child(user.getUid());
        swiat = new swiat();
        swiat.setIleslowek(466);
        swiat.setSlowko(1);
        reff.setValue(swiat);

        reff = FirebaseDatabase.getInstance().getReference().child("technika").child(user.getUid());
        technika = new technika();
        technika.setIleslowek(287);
        technika.setSlowko(1);
        reff.setValue(technika);

        reff = FirebaseDatabase.getInstance().getReference().child("wolny").child(user.getUid());
        wolny = new wolny();
        wolny.setIleslowek(177);
        wolny.setSlowko(1);
        reff.setValue(wolny);

        reff = FirebaseDatabase.getInstance().getReference().child("zdrowie").child(user.getUid());
        zdrowie = new zdrowie();
        zdrowie.setIleslowek(301);
        zdrowie.setSlowko(1);
        reff.setValue(zdrowie);

        reff = FirebaseDatabase.getInstance().getReference().child("zyciecodzienne").child(user.getUid());
        zyciecodzienne = new zyciecodzienne();
        zyciecodzienne.setIleslowek(556);
        zyciecodzienne.setSlowko(1);
        reff.setValue(zyciecodzienne);


    }
}
