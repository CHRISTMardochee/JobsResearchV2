package com.jobsresearch.controller.jobsresearchv2.demandeur;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jobsresearch.controller.jobsresearchv2.MainActivity;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.recruteur.ChatActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


public class DetailActivity extends BaseActivity {

    @Override
    public int getFragmentLayout() {
        return R.layout.activity_detail;
    }


    @BindView(R.id.txtfonction) TextView txtViewFonction;
    @BindView(R.id.txtsecteur) TextView txtViewSecteur;
    @BindView(R.id.txtlieu) TextView txtViewLieu;
    @BindView(R.id.txtcontrat) TextView txtViewContrat;
    @BindView(R.id.txtposte) TextView txtViewPoste;
    @BindView(R.id.txtprofile) TextView txtViewProfil;
    @BindView(R.id.date) TextView txtdate;


    @BindView(R.id.favorite) ImageButton ivfavorite;

    @OnClick(R.id.postuler)
    public void onClickPostButton() {
        if (this.isCurrentUserLogged()){
            Toast.makeText(this, "Votre canditature a été bien prise en compte", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.popup_message_confirmation_login)
                    .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.popup_message_choice_no, null)
                    .show();

        }

    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //RECEIVE DATA
        Intent i=this.getIntent();
        String fonction=i.getExtras().getString("FONCTION_KEY");
        String secteur=i.getExtras().getString("SECTEUR_KEY");
        String lieu=i.getExtras().getString("LIEU_KEY");
        String contrat=i.getExtras().getString("CONTRAT_KEY");
        String poste=i.getExtras().getString("POSTE_KEY");
        String profil=i.getExtras().getString("PROFIL_KEY");
        String date=i.getExtras().getString("DATE_KEY");

        txtViewSecteur.setText(secteur);
        txtViewFonction.setText(fonction);
        txtViewLieu.setText(lieu);
        txtViewContrat.setText(contrat);
        txtViewPoste.setText(poste);
        txtViewProfil.setText(profil);
        txtdate.setText(date);


        ivfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }
}
