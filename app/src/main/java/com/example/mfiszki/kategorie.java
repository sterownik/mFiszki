package com.example.mfiszki;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class kategorie extends AppCompatActivity {

    ImageButton zyciecodzienne, nauka, cywilizacja, podrozowanie, technika, zdrowie, kultura, emocje, czlowiek, biznes, bezpieczenstwo, sport, przyroda, swiat, handel, wolny;
    RelativeLayout zyciecodzienne2, nauka2, cywilizacja2, podrozowanie2, technika2, zdrowie2, kultura2, emocje2, czlowiek2, biznes2, bezpieczenstwo2, sport2, przyroda2, swiat2, handel2, wolny2;
    Button logout;
    TextView nazwa;
    private DatabaseReference reff;
    FirebaseUser user;
    int slowkapok =-16;
    int slowkawszystkie =-16;
    int zdjecie;
    Thread thread;
    FirebaseAuth firebaseAuth;
    ImageView img;
    ProgressBar osiagniecia;
    TextView textView,przebyte,wszystkie;
    TextView slowkacodzienne, slowkaczlowiek, slowkaemocje, slowazdrowie, slowkkultura, slowkanauka, slowkacywilizacja, slowkapodrozowanie, slowkatechnika, slowkabiznes, slowkabezpieczenstwo, slowkasport, slowkaprzyroda, slowkaswiat, slowkahandel, slowkawolny;
    TextView lbzycie, lbczlowiek, lbemocje, lbzdrowie, lbkultura, lbnauka, lbcywilizacja, lbpodrozowanie, lbtechnika, lbbiznes, lbbezpieczenstwo, lbsport, lbprzyroda, lbswiat, lbhandel, lbwolny;


    @Override
    public void onBackPressed() {

    }
    public void przpisanietxt(){
        user = firebaseAuth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference().child("bezpieczenstwo").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();

                slowkabezpieczenstwo.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbbezpieczenstwo.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("biznes").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkabiznes.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbbiznes.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("cywilizacja").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkacywilizacja.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbcywilizacja.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("czlowiek").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkaczlowiek.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbczlowiek.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("emocje").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkaemocje.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbemocje.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("handel").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkahandel.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbhandel.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("kultura").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkkultura.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();

                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbkultura.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("nauka").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkanauka.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbnauka.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("podroze").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkapodrozowanie.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbpodrozowanie.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("przyroda").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkaprzyroda.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbprzyroda.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("sport").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkasport.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbsport.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("swiat").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkaswiat.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbswiat.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("technika").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkatechnika.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbtechnika.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("wolny").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkawolny.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbwolny.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("zdrowie").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowazdrowie.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbzdrowie.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("zyciecodzienne").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("slowko").getValue().toString();
                slowkacodzienne.setText(slowko);
                String lb = dataSnapshot.child("ileslowek").getValue().toString();
                int slowo = Integer.parseInt(slowko);
                slowkapok = slowkapok+slowo;

                int lb1 = Integer.parseInt(lb);
                slowkawszystkie = slowkawszystkie+lb1;

                lbzycie.setText("/"+lb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reff = FirebaseDatabase.getInstance().getReference().child("photo").child(user.getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String slowko = dataSnapshot.child("ph").getValue().toString();
                zdjecie = Integer.parseInt(slowko);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        if(zdjecie==0){
                            final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                            StorageReference pathReference = storageReference.child("zdjecia/" + user.getUid() + "/avatar.jpg");

                            reff = FirebaseDatabase.getInstance().getReference().child("zdjecia").child(user.getUid());

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
//                reff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                               textView.setText(dataSnapshot.child("-M53WLJ7nT4HgX-Rb3Fq").child("mImageUurl").getValue().toString());
//                       // Picasso.
//                       // Glide.with(getApplicationContext()).load(dataSnapshot.child("-M53WLJ7nT4HgX-Rb3Fq").child("mImageUurl").getValue().toString()).into(img);
//                        Picasso.get().load("gs://mfiszki-6a1f4.appspot.com/zdjecia/htDf406EZbWUPALKsdAbG5ssaJR2/avatar.jpg").fit().centerCrop().into(img);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
                                    storageReference.child("zdjecia/" + user.getUid() + "/avatar.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // textView.setText(uri.toString());
                                            Picasso.get().load(uri).into(img);
                                        }
                                    });

                                }
                            }, 2000);
                        }
                        else if(zdjecie==1){

                            img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.straznik));
                        }
                        else if(zdjecie==2){

                            img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.bus));
                        }
                        else if(zdjecie==3){

                            img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.englishman));
                        }
                    }
                }, 500);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void przpisanie() {
        slowkacodzienne = (TextView) findViewById(R.id.slowkocodzienne);
        slowkaczlowiek = (TextView) findViewById(R.id.slowkoczlowiek);
        slowkaemocje = (TextView) findViewById(R.id.slowkoemocje);
        slowazdrowie = (TextView) findViewById(R.id.slowkozdrowie);
        slowkkultura = (TextView) findViewById(R.id.slowkokultura);
        slowkanauka = (TextView) findViewById(R.id.slowkonauka);
        slowkacywilizacja = (TextView) findViewById(R.id.slowkocywilizacja);
        slowkapodrozowanie = (TextView) findViewById(R.id.slowkopodrozowanie);
        slowkatechnika = (TextView) findViewById(R.id.slowkotechnika);
        slowkabiznes = (TextView) findViewById(R.id.slowkobiznes);
        slowkabezpieczenstwo = (TextView) findViewById(R.id.slowkobezpieczenstwo);
        slowkasport = (TextView) findViewById(R.id.slowkosport);
        slowkaprzyroda = (TextView) findViewById(R.id.slowkoprzyroda);
        slowkaswiat = (TextView) findViewById(R.id.slowkoswiat);
        slowkahandel = (TextView) findViewById(R.id.slowkohandel);
        slowkawolny = (TextView) findViewById(R.id.slowkowolny);
//
        lbzycie = (TextView) findViewById(R.id.iloscslowekcodzienne);
        lbczlowiek = (TextView) findViewById(R.id.iloscslowekczlowiek);
        lbemocje = (TextView) findViewById(R.id.iloscslowekemocje);
        lbzdrowie = (TextView) findViewById(R.id.iloscslowekzdrowie);
        lbkultura = (TextView) findViewById(R.id.iloscslowekkultura);
        lbnauka = (TextView) findViewById(R.id.iloscsloweknauka);
        lbcywilizacja = (TextView) findViewById(R.id.iloscslowekcywilizacja);
        lbpodrozowanie = (TextView) findViewById(R.id.iloscslowekpodrozowanie);
        lbtechnika = (TextView) findViewById(R.id.iloscslowektechnika);
        lbbiznes = (TextView) findViewById(R.id.iloscslowekbiznes);
        lbbezpieczenstwo = (TextView) findViewById(R.id.iloscslowekbezpieczenstwo);
        lbsport = (TextView) findViewById(R.id.iloscsloweksport);
        lbprzyroda = (TextView) findViewById(R.id.iloscslowekprzyroda);
        lbswiat = (TextView) findViewById(R.id.iloscslowekswiat);
        lbhandel = (TextView) findViewById(R.id.iloscslowekhandel);
        lbwolny = (TextView) findViewById(R.id.iloscslowekwolny);

//
        przebyte = (TextView)findViewById(R.id.txtlbzrobionych);
        wszystkie = (TextView)findViewById(R.id.txtlbwszystkich);

        osiagniecia=(ProgressBar)findViewById(R.id.osiagniecia);
    }

    @Override
    protected void onStart() {
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {

//                przebyte.setText(slowkapok+"");
//                wszystkie.setText(slowkawszystkie+"");
//
//                osiagniecia.setMax(slowkawszystkie);
//                osiagniecia.setProgress(slowkapok);
            }
        }, 3000);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategorie);
        img = (ImageView) findViewById(R.id.img);
        firebaseAuth = FirebaseAuth.getInstance();
        przpisanie();
        przpisanietxt();

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        }, 3000);

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                przebyte.setText(slowkapok+"");
                                wszystkie.setText(slowkawszystkie+"");

                                osiagniecia.setMax(slowkawszystkie);
                                osiagniecia.setProgress(slowkapok);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

        textView = (TextView) findViewById(R.id.txt);
        getSupportActionBar().hide();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            Intent intent = new Intent(kategorie.this, Logowanie.class);
            startActivity(intent);
        }


        user = firebaseAuth.getCurrentUser();
        logout = (Button) findViewById(R.id.logout);

        String nazwauz[] = user.getEmail().split("@");
        firebaseAuth = FirebaseAuth.getInstance();

        //  Toast.makeText(getApplicationContext(),user.getUid(),Toast.LENGTH_LONG).show();


        textView.setText("Witam, " + nazwauz[0]);
        zyciecodzienne2 = (RelativeLayout) findViewById(R.id.codzienne2);
        zyciecodzienne = (ImageButton) findViewById(R.id.codzienne);
        handel = (ImageButton) findViewById(R.id.handel);
        handel2 = (RelativeLayout) findViewById(R.id.handel2);
        zdrowie = (ImageButton) findViewById(R.id.zdrowie);
        zdrowie2 = (RelativeLayout) findViewById(R.id.zdrowie2);
        wolny = (ImageButton) findViewById(R.id.wolny);
        wolny2 = (RelativeLayout) findViewById(R.id.wolny2);
        kultura = (ImageButton) findViewById(R.id.kultura);
        kultura2 = (RelativeLayout) findViewById(R.id.kultura2);
        nauka = (ImageButton) findViewById(R.id.nauka);
        nauka2 = (RelativeLayout) findViewById(R.id.nauka2);
        swiat = (ImageButton) findViewById(R.id.swiat);
        swiat2 = (RelativeLayout) findViewById(R.id.swiat2);
        podrozowanie = (ImageButton) findViewById(R.id.podrozowanie);
        podrozowanie2 = (RelativeLayout) findViewById(R.id.podrozowanie2);
        bezpieczenstwo = (ImageButton) findViewById(R.id.bezpieczenstwo);
        bezpieczenstwo2 = (RelativeLayout) findViewById(R.id.bezpieczenstwo2);
        biznes = (ImageButton) findViewById(R.id.biznes);
        biznes2 = (RelativeLayout) findViewById(R.id.biznes2);
        przyroda = (ImageButton) findViewById(R.id.przyroda);
        przyroda2 = (RelativeLayout) findViewById(R.id.przyroda2);
        technika = (ImageButton) findViewById(R.id.technika);
        technika2 = (RelativeLayout) findViewById(R.id.technika2);
        sport = (ImageButton) findViewById(R.id.sport);
        sport2 = (RelativeLayout) findViewById(R.id.sport2);

        emocje = (ImageButton) findViewById(R.id.emocje);
        emocje2 = (RelativeLayout) findViewById(R.id.emocje2);
        cywilizacja = (ImageButton) findViewById(R.id.cywilizacja);
        cywilizacja2 = (RelativeLayout) findViewById(R.id.cywilizacja2);







        czlowiek = (ImageButton) findViewById(R.id.czlowiek);
        czlowiek2 = (RelativeLayout) findViewById(R.id.czlowiek2);
        final Intent intent = new Intent(kategorie.this, MainActivity.class);
        zyciecodzienne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                zyciecodzienne2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "zyciecodzienne");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        czlowiek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                czlowiek2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "czlowiek");
                        startActivity(intent);

                    }
                }, 900);

            }
        });
        emocje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                emocje2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "emocje");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        zdrowie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                zdrowie2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "zdrowie");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        kultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                kultura2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "kultura");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        nauka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                nauka2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "nauka");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        cywilizacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                cywilizacja2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "cywilizacja");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        podrozowanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                podrozowanie2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "podroze");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        technika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                technika2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "technika");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        biznes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                biznes2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "biznes");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        bezpieczenstwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                bezpieczenstwo2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "bezpieczenstwo");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                sport2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "sport");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        przyroda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                przyroda2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "przyroda");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        swiat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                swiat2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "swiat");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        handel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                handel2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "handel");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        wolny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                wolny2.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        intent.putExtra("kat", "wolny");
                        startActivity(intent);

                    }
                }, 1000);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intent1 = new Intent(kategorie.this, Logowanie.class);
                startActivity(intent1);

            }
        });
    }
}
