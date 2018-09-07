package com.jobsresearch.controller.jobsresearchv2.recruteur;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Spinner;
import android.widget.Toast;

import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.api.OffreHelper;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;

import butterknife.BindView;
import butterknife.OnClick;

public class AddOffreActivity extends BaseActivity {


    @Override
    public int getFragmentLayout() {return R.layout.activity_add_offre;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @BindView(R.id.add_offre_spinner_secteur) Spinner spinnerSecteur;
    @BindView(R.id.add_offre_activity_text_fonction) TextInputEditText textInputTextFonction;
    @BindView(R.id.add_offre_activity_text_lieu) TextInputEditText textInputTextLieu;
    @BindView(R.id.add_offre_spinner_contrat) Spinner spinnerTypeContrat;
    @BindView(R.id.add_offre_activity_text_profil) TextInputEditText textInputTextProfil;
    @BindView(R.id.add_offre_activity_text_poste) TextInputEditText textInputTextPoste;


    @OnClick(R.id.offre_activity_button_add)
    public void onClickAddCvButton(){
            createOffreInFirestore();
    }


    private void createOffreInFirestore(){

        if (this.getCurrentUser() != null){

            String uid = this.getCurrentUser().getUid();
            String secteur = this.spinnerSecteur.getSelectedItem().toString();
            String fonction=this.textInputTextFonction.getText().toString();
            String lieu=this.textInputTextLieu.getText().toString().trim();
            String type=this.spinnerTypeContrat.getSelectedItem().toString();
            String profil=this.textInputTextProfil.getText().toString().trim();
            String poste=this.textInputTextPoste.getText().toString().trim();

            if(!secteur.equals(getString(R.string.text_choice_secteur)) && !TextUtils.isEmpty(fonction)
                    && !TextUtils.isEmpty(lieu) && !type.equals(getString(R.string.text_choice_contrat)) &&
                    !TextUtils.isEmpty(profil) && !TextUtils.isEmpty(poste) ) {

                OffreHelper.createOffre(uid, secteur,fonction,lieu,type,poste, profil).addOnFailureListener(this.onFailureListener());
                Toast.makeText(this, "Votre offre a été bien publié", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Champs obligatoires", Toast.LENGTH_LONG).show();
            }
        }
    }
}
