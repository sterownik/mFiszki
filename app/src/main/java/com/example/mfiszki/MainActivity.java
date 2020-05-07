package com.example.mfiszki;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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
import com.example.mfiszki.database.zyciecodzienne;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button slowkobtn;
    ImageButton back;
    boolean przod = false;
    int seconds;
    boolean odpowiedzpyt;
    FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    bezpieczenstwo bezpieczenstwo;
    biznes biznes;
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
    String kategoria;
    boolean running;
    CountDownTimer mCountDownTimer2;
    ImageButton next;
    TextView odliczanie;
    String angielskie;
    String polskie;
    LinearLayout calagra;
    boolean udzielonaodp;
    int odliczanie2;

    TextView czas, nrslowka, lbslowka;
    ImageButton mow;

    public MainActivity() {
    }

    public int getIleslowek() {
        return ileslowek;
    }

    public void setIleslowek(int ileslowek) {
        this.ileslowek = ileslowek;
    }

    int ileslowek;

    public int getIlebylo() {
        return ilebylo;
    }

    public void setIlebylo(int ilebylo) {
        this.ilebylo = ilebylo;
    }

    int ilebylo;
    int ilecofnac = 20;
    DatabaseReference reff;

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    String nazwaKategorii;
    int licznik = 0;

    public int getLiczniknrslowka() {
        return liczniknrslowka;
    }

    public void setLiczniknrslowka(int liczniknrslowka) {
        this.liczniknrslowka = liczniknrslowka;
    }

    int liczniknrslowka = 1;
    private TextToSpeech mTTS;
    int iloscslowek;

    @Override
    public void onBackPressed() {
        stopTimer();
        resetTimer();
        zapis();
        finish();
        Intent intent = new Intent(MainActivity.this,kategorie.class);
        startActivity(intent);

//            firebaseAuth = FirebaseAuth.getInstance();
//
//            reff= FirebaseDatabase.getInstance().getReference().child(getKategoria()).child(user.getUid());
//
//            reff.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Toast.makeText(getApplicationContext(),dataSnapshot.child("ileslowek").getValue().toString()+"",Toast.LENGTH_LONG).show();
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                }
//            });
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        stopTimer();
        resetTimer();
        super.onDestroy();
    }

    public void startTimer() {
        running = true;
    }

    public void stopTimer() {
        running = false;
    }

    public void resetTimer() {
        running = true;
        seconds = 0;
    }

    public void runTimer() {
        final TextView textView = (TextView) findViewById(R.id.czas);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format("%02d:%02d", minutes, sec);
                textView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        back = (ImageButton) findViewById(R.id.back);
        odliczanie = (TextView) findViewById(R.id.odliczanie);
        czas = (TextView) findViewById(R.id.czas);
        calagra = (LinearLayout) findViewById(R.id.calagra);
        odliczanie2 = 3;
        nrslowka = (TextView) findViewById(R.id.nrslowka);
        lbslowka = (TextView) findViewById(R.id.liczbaslowek);
        slowkobtn = (Button) findViewById(R.id.slowko);
        mow = (ImageButton) findViewById(R.id.mow);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Intent intent = getIntent();
        String kat = intent.getStringExtra("kat");
        setKategoria(kat);
       // Toast.makeText(getApplicationContext(),getKategoria(),Toast.LENGTH_LONG).show();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference().child(getKategoria()).child(user.getUid());


        mCountDownTimer2 = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);

                odliczanie2--;
                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mixed_anim);
                odliczanie.startAnimation(animationprzyciski);
                odliczanie.setText(odliczanie2 + "");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String nr = dataSnapshot.child("slowko").getValue().toString();
                      //  Toast.makeText(getApplicationContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
                        setLiczniknrslowka(Integer.parseInt(nr));
                        if (Integer.parseInt(nr) == 0) {
                            setLiczniknrslowka(1);
                        }
                        liczniknrslowka = getLiczniknrslowka();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onFinish() {
                //Do what you want
                try {
                    odliczanie.setVisibility(View.GONE);
                    calagra.setVisibility(View.VISIBLE);
                    odliczanie.clearAnimation();
                    mCountDownTimer2.cancel();
                    iloscslowek = czytanieslowek(getLiczniknrslowka());

                    lbslowka.setText(" / " + iloscslowek);
                    setIleslowek(iloscslowek);
                    setIlebylo(getLiczniknrslowka());
                    runTimer();
                    startTimer();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


//        reff = FirebaseDatabase.getInstance().getReference().child("Bezpieczenstwo").child(user.getUid());
////        bezpieczenstwo.getSlowko();
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String przebyteslowka = dataSnapshot.child("slowko").getValue().toString();
//                Toast.makeText(getApplicationContext(),przebyteslowka+"",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms


            }
        }, 2000);
        next = (ImageButton) findViewById(R.id.next);


        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //Log.e("TTS", "Language not supported");
                    } else {
                        mow.setEnabled(true);
                    }
                } else {
                    // Log.e("TTS", "Initialization failed");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setEnabled(false);
                przod = false;

                Animation animationprzyciski2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.lefttoright);
                back.startAnimation(animationprzyciski2);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        back.setEnabled(true);
                        back.clearAnimation();
                        try {

                            if (liczniknrslowka > 1) {
                                if (back.isEnabled()) {
                                    liczniknrslowka--;
                                    czytanieslowek(liczniknrslowka);
                                }


                            } else {
                                liczniknrslowka = 1;
                                czytanieslowek(liczniknrslowka);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, 700);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                przod = true;
                next.setEnabled(false);


                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.righttoleft);
                next.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        next.setEnabled(true);
                        next.clearAnimation();
                        try {

                            if (liczniknrslowka < iloscslowek) {
                                if (next.isEnabled()) {
                                    liczniknrslowka++;
                                    czytanieslowek(liczniknrslowka);
                                }


                            } else {
                                liczniknrslowka = 1;
                                czytanieslowek(liczniknrslowka);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, 700);

            }
        });


    }

    public int czytanieslowek(int liczniknrslowka) throws IOException {

        Intent intent = getIntent();
        String kat = intent.getStringExtra("kat");
      //  Toast.makeText(getApplicationContext(),getKategoria(),Toast.LENGTH_LONG).show();
        setKategoria(kat);
        Charset ch = Charset.forName("UTF-8");
        setNazwaKategorii(kat);
        licznik = 0;
        String slowka = "";
        StringBuffer buf = new StringBuffer();
        InputStream is = null;
        if (kat.equals("zyciecodzienne")) {
            is = this.getResources().openRawResource(R.raw.zyciecodzienne);
        } else if (kat.equals("czlowiek")) {
            is = this.getResources().openRawResource(R.raw.czlowiek);
        } else if (kat.equals("emocje")) {
            is = this.getResources().openRawResource(R.raw.emocje);
        } else if (kat.equals("zdrowie")) {
            is = this.getResources().openRawResource(R.raw.zdrowie);
        } else if (kat.equals("kultura")) {
            is = this.getResources().openRawResource(R.raw.kultura);
        } else if (kat.equals("nauka")) {
            is = this.getResources().openRawResource(R.raw.nauka);
        } else if (kat.equals("cywilizacja")) {
            is = this.getResources().openRawResource(R.raw.cywilizacja);
        } else if (kat.equals("podroze")) {
            is = this.getResources().openRawResource(R.raw.podroze);
        } else if (kat.equals("technika")) {
            is = this.getResources().openRawResource(R.raw.technika);
        } else if (kat.equals("biznes")) {
            is = this.getResources().openRawResource(R.raw.biznes);
        } else if (kat.equals("bezpieczenstwo")) {
            is = this.getResources().openRawResource(R.raw.bezpieczenstwo);
        } else if (kat.equals("sport")) {
            is = this.getResources().openRawResource(R.raw.sport);
        } else if (kat.equals("przyroda")) {
            is = this.getResources().openRawResource(R.raw.przyroda);
        } else if (kat.equals("swiat")) {
            is = this.getResources().openRawResource(R.raw.swiat);
        } else if (kat.equals("handel")) {
            is = this.getResources().openRawResource(R.raw.handel);
        } else if (kat.equals("wolny")) {
            is = this.getResources().openRawResource(R.raw.wolny);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, ch));
        if (is != null) {
            while ((slowka = reader.readLine()) != null) {
                buf.append(slowka + "\n");
            }
        }
        String slowkazczytane = buf.toString();

        String pocieciu[] = slowkazczytane.split("\n");

        String angpl[] = pocieciu[liczniknrslowka - 1].split(" - ");
        angielskie = angpl[0];
        if (angielskie.contains(";")) {
            angielskie = angielskie.replaceAll(";", ",");
        }
        polskie = angpl[1];
        if (polskie.contains(";")) {
            polskie = polskie.replace(";", ",");
        }
        slowkobtn.setText(polskie);
        slowkobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animationprzyciski = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                slowkobtn.startAnimation(animationprzyciski);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        licznik++;
                        if (licznik % 2 == 1) {
                            slowkobtn.setText(angielskie);
                        } else {
                            slowkobtn.setText(polskie);
                        }


                    }
                }, 100);

            }
        });
        mow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(angielskie);
            }
        });
        nrslowka.setText(liczniknrslowka + "");
        if (liczniknrslowka % 20 == 0 && przod) {
            int dobrerand = new Random().nextInt(ilecofnac + 1) + liczniknrslowka - ilecofnac;

            int jedenrand = new Random().nextInt(ilecofnac + 1) + liczniknrslowka - ilecofnac;
            int dwarand = new Random().nextInt(ilecofnac + 1) + liczniknrslowka - ilecofnac;
            while (jedenrand == dwarand) {
                dwarand = new Random().nextInt(ilecofnac + 1) + liczniknrslowka - ilecofnac;
            }
            while (dobrerand == dwarand || dobrerand == jedenrand) {
                dobrerand = new Random().nextInt(ilecofnac + 1) + liczniknrslowka - ilecofnac;
            }

            boolean czyodp;


            czyodp = tekst(liczniknrslowka, pocieciu[dobrerand], pocieciu[dwarand], pocieciu[jedenrand]);
//         if(udzielonaodp)
//         {
//             Toast.makeText(getApplicationContext(),udzielonaodp+"",Toast.LENGTH_LONG).show();
//
//         }
//         else {
//             Toast.makeText(getApplicationContext(),udzielonaodp+"",Toast.LENGTH_LONG).show();
//             setLiczniknrslowka(getLiczniknrslowka()-10);
//         }


        }


        return pocieciu.length;

    }

    private void speak(String angstr) {
        mTTS.speak(angstr, TextToSpeech.QUEUE_FLUSH, null);
    }

    private boolean tekst(int liczniknrslowka, String dobre, String angpljeden, String angpldwa) {
        String angpljedenn[] = angpljeden.split(" - ");
        String angielskieodp = angpljedenn[0];
        if (angielskieodp.contains(";")) {
            angielskieodp = angielskieodp.replaceAll(";", ",");
        }
        String polskieeodp = angpljedenn[1];
        if (polskieeodp.contains(";")) {
            polskieeodp = polskieeodp.replace(";", ",");
        }


        String dowstawienia[] = dobre.split(" - ");
        String dowstanieniaang = dowstawienia[0];
        if (dowstanieniaang.contains(";")) {
            dowstanieniaang = dowstanieniaang.replaceAll(";", ",");
        }
        String dowstawieniapl = dowstawienia[1];
        if (dowstawieniapl.contains(";")) {
            dowstawieniapl = dowstawieniapl.replace(";", ",");
        }


        String angpldwaa[] = angpldwa.split(" - ");
        String angielskiedwaodp = angpldwaa[0];
        if (angielskiedwaodp.contains(";")) {
            angielskiedwaodp = angielskiedwaodp.replaceAll(";", ",");
        }
        String polskiedwaeodp = angpldwaa[1];
        if (polskiedwaeodp.contains(";")) {
            polskiedwaeodp = polskiedwaeodp.replace(";", ",");
        }

        String polskaodpowiedzjeden = polskieeodp;
        String angielskieodpowiedzjeden = angielskieodp;

        final String angwstawienie = dowstanieniaang;
        final String plwstaienie = dowstawieniapl;
        String polskaodpowiedzdwa = polskiedwaeodp;
        String angielskieodpowiedzdwa = angielskiedwaodp;


        final Dialog factory = new Dialog(MainActivity.this);
        factory.setContentView(R.layout.pytanie);
        factory.setCancelable(false);
        factory.show();
        final Button glowny, jeden, dwa, trzy;
        glowny = factory.findViewById(R.id.glownebtn);
        jeden = factory.findViewById(R.id.jedenbtn);
        dwa = factory.findViewById(R.id.dwabtn);
        trzy = factory.findViewById(R.id.trzybtn);


        int[] liczby = {0, 1, 2};
        ArrayList<Integer> lista = new ArrayList<Integer>();
        Random rand2 = new Random();
        while (lista.size() < 3) {
            int random2 = rand2.nextInt(liczby.length);
            if (!lista.contains(liczby[random2])) {
                lista.add(liczby[random2]);
            }
        }
        String[] odp = {plwstaienie, polskaodpowiedzdwa, polskaodpowiedzjeden};


        glowny.setText(angwstawienie);
        jeden.setText(odp[lista.get(0)]);
        dwa.setText(odp[lista.get(1)]);
        trzy.setText(odp[lista.get(2)]);
        udzielonaodp = true;
        odpowiedzpyt = false;

        jeden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jeden.getText().equals(plwstaienie)) {
                    odpowiedzpyt = true;
                    udzielonaodp = true;
                } else {
                    odpowiedzpyt = false;
                    udzielonaodp = false;
                    factory.dismiss();
                    setLiczniknrslowka(getLiczniknrslowka() - ilecofnac + 1);
                    if (getLiczniknrslowka() < 1) {
                        setLiczniknrslowka(1);
                    }
                    try {
                        czytanieslowek(getLiczniknrslowka());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                next.setEnabled(true);
                factory.dismiss();

            }
        });
        dwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dwa.getText().equals(plwstaienie)) {
                    odpowiedzpyt = true;
                    udzielonaodp = true;
                } else {
                    odpowiedzpyt = false;
                    udzielonaodp = false;
                    factory.dismiss();
                    setLiczniknrslowka(getLiczniknrslowka() - ilecofnac + 1);
                    if (getLiczniknrslowka() < 1) {
                        setLiczniknrslowka(1);
                    }
                    try {
                        czytanieslowek(getLiczniknrslowka());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                next.setEnabled(true);
                factory.dismiss();

            }
        });
        trzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trzy.getText().equals(plwstaienie)) {
                    odpowiedzpyt = true;
                    udzielonaodp = true;
                } else {
                    odpowiedzpyt = false;
                    udzielonaodp = false;
                    setLiczniknrslowka(getLiczniknrslowka() - ilecofnac + 1);
                    if (getLiczniknrslowka() < 1) {
                        setLiczniknrslowka(1);
                    }
                    try {
                        czytanieslowek(getLiczniknrslowka());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                next.setEnabled(true);
                factory.dismiss();

            }
        });


        return odpowiedzpyt;
    }

    private void zapis() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user;
        user = firebaseAuth.getCurrentUser();
        if (getKategoria().equals("bezpieczenstwo")) {
            reff = FirebaseDatabase.getInstance().getReference().child("bezpieczenstwo").child(user.getUid());
            bezpieczenstwo = new bezpieczenstwo();
            bezpieczenstwo.setIleslowek(iloscslowek);
            bezpieczenstwo.setSlowko(getLiczniknrslowka());
            reff.setValue(bezpieczenstwo);
        }
        if (getKategoria().equals("biznes")) {
            reff = FirebaseDatabase.getInstance().getReference().child("biznes").child(user.getUid());
            biznes = new biznes();
            biznes.setIleslowek(iloscslowek);
            biznes.setSlowko(getLiczniknrslowka());
            reff.setValue(biznes);
        }

        if (getKategoria().equals("cywilizacja")) {
            reff = FirebaseDatabase.getInstance().getReference().child("cywilizacja").child(user.getUid());
            cywilizacja = new cywilizacja();
            cywilizacja.setIleslowek(iloscslowek);
            cywilizacja.setSlowko(getLiczniknrslowka());
            reff.setValue(cywilizacja);
        }
        if (getKategoria().equals("czlowiek")) {
            reff = FirebaseDatabase.getInstance().getReference().child("czlowiek").child(user.getUid());
            czlowiek = new czlowiek();
            czlowiek.setIleslowek(iloscslowek);
            czlowiek.setSlowko(getLiczniknrslowka());
            reff.setValue(czlowiek);
        }
        if (getKategoria().equals("emocje")) {
            reff = FirebaseDatabase.getInstance().getReference().child("emocje").child(user.getUid());
            emocje = new emocje();
            emocje.setIleslowek(iloscslowek);
            emocje.setSlowko(getLiczniknrslowka());
            reff.setValue(emocje);
        }
        if (getKategoria().equals("handel")) {
            reff = FirebaseDatabase.getInstance().getReference().child("handel").child(user.getUid());
            handel = new handel();
            handel.setIleslowek(iloscslowek);
            handel.setSlowko(getLiczniknrslowka());
            reff.setValue(handel);
        }
        if (getKategoria().equals("kultura")) {
            reff = FirebaseDatabase.getInstance().getReference().child("kultura").child(user.getUid());
            kultura = new kultura();
            kultura.setIleslowek(iloscslowek);
            kultura.setSlowko(getLiczniknrslowka());
            reff.setValue(kultura);
        }
        if (getKategoria().equals("nauka")) {
            reff = FirebaseDatabase.getInstance().getReference().child("nauka").child(user.getUid());
            nauka = new nauka();
            nauka.setIleslowek(iloscslowek);
            nauka.setSlowko(getLiczniknrslowka());
            reff.setValue(nauka);
        }

        if (getKategoria().equals("podroze")) {
            reff = FirebaseDatabase.getInstance().getReference().child("podroze").child(user.getUid());
            podroze = new podroze();
            podroze.setIleslowek(iloscslowek);
            podroze.setSlowko(getLiczniknrslowka());
            reff.setValue(podroze);
        }
        if (getKategoria().equals("przyroda")) {
            reff = FirebaseDatabase.getInstance().getReference().child("przyroda").child(user.getUid());
            przyroda = new przyroda();
            przyroda.setIleslowek(iloscslowek);
            przyroda.setSlowko(getLiczniknrslowka());
            reff.setValue(przyroda);
        }
        if (getKategoria().equals("sport")) {
            reff = FirebaseDatabase.getInstance().getReference().child("sport").child(user.getUid());
            sport = new sport();
            sport.setIleslowek(iloscslowek);
            sport.setSlowko(getLiczniknrslowka());
            reff.setValue(sport);
        }
        if (getKategoria().equals("swiat")) {
            reff = FirebaseDatabase.getInstance().getReference().child("swiat").child(user.getUid());
            swiat = new swiat();
            swiat.setIleslowek(iloscslowek);
            swiat.setSlowko(getLiczniknrslowka());
            reff.setValue(swiat);
        }
        if (getKategoria().equals("technika")) {
            reff = FirebaseDatabase.getInstance().getReference().child("technika").child(user.getUid());
            technika = new technika();
            technika.setIleslowek(iloscslowek);
            technika.setSlowko(getLiczniknrslowka());
            reff.setValue(technika);
        }
        if (getKategoria().equals("wolny")) {
            reff = FirebaseDatabase.getInstance().getReference().child("wolny").child(user.getUid());
            wolny = new wolny();
            wolny.setIleslowek(iloscslowek);
            wolny.setSlowko(getLiczniknrslowka());
            reff.setValue(wolny);
        }
        if (getKategoria().equals("zdrowie")) {
            reff = FirebaseDatabase.getInstance().getReference().child("zdrowie").child(user.getUid());
            zdrowie = new zdrowie();
            zdrowie.setIleslowek(iloscslowek);
            zdrowie.setSlowko(getLiczniknrslowka());
            reff.setValue(zdrowie);
        }
        if (getKategoria().equals("zyciecodzienne")) {
            reff = FirebaseDatabase.getInstance().getReference().child("zyciecodzienne").child(user.getUid());
            zyciecodzienne = new zyciecodzienne();
            zyciecodzienne.setIleslowek(iloscslowek);
            zyciecodzienne.setSlowko(getLiczniknrslowka());
            reff.setValue(zyciecodzienne);
        }
    }


}
