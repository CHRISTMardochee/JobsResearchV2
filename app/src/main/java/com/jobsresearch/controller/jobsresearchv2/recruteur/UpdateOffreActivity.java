package com.jobsresearch.controller.jobsresearchv2.recruteur;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.api.OffreHelper;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateOffreActivity extends BaseActivity {

    @Override
    public int getFragmentLayout(){ return R.layout.activity_update_offre;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateUIWhenCreating();
    }

    @BindView(R.id.update_offre_spinner_secteur) Spinner updateSpinnerSecteur;
    @BindView(R.id.update_offre_activity_text_fonction) TextInputEditText textUpdateTextFonction;
    @BindView(R.id.update_offre_activity_text_lieu) TextInputEditText textUpdateTextLieu;
    @BindView(R.id.update_offre_spinner_contrat) Spinner updateSpinnerContrat;
    @BindView(R.id.update_offre_activity_text_profil) TextInputEditText textUpdateTextProfil;
    @BindView(R.id.update_offre_activity_text_poste) TextInputEditText textUpdateTextPoste;
    @BindView(R.id.offre_update_activity_progress_bar)
    ProgressBar progressBar;


    @OnClick(R.id.offre_activity_button_update)
    public void onClickUpdateOffreButton(){
        updateOffreInFirebase();
    }

    // 1 - Update UI when activity is creating
    private void updateUIWhenCreating() {

        if (this.getCurrentUser() != null) {

            // 7 - Get additional data from Firestore (isMentor & Username)
            OffreHelper.getOffre(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Offre currentOffre = documentSnapshot.toObject(Offre.class);
                    if (documentSnapshot.exists()) {
                        //String name = TextUtils.isEmpty(currentCv.getNom()) ? getString(R.string.info_no_username_found) : currentCv.getNom();
                        String secteur = currentOffre.getSecteur();
                        String fonction = currentOffre.getFonction();
                        String lieu = currentOffre.getLieu();
                        String contrat = currentOffre.getType();
                        String profil = currentOffre.getProfil();
                        String poste = currentOffre.getPoste();

                        textUpdateTextFonction.setText(fonction);
                        textUpdateTextLieu.setText(lieu);
                        textUpdateTextProfil.setText(profil);
                        textUpdateTextPoste.setText(poste);
                    }else{
                        textUpdateTextFonction.setText("");
                        textUpdateTextLieu.setText("");
                        textUpdateTextProfil.setText("");
                        textUpdateTextPoste.setText("");
                    }

                }
            });
        }
    }

    private void updateOffreInFirebase(){

        this.progressBar.setVisibility(View.VISIBLE);
        String secteur = this.updateSpinnerSecteur.getSelectedItem().toString();
        String fonction=this.textUpdateTextFonction.getText().toString();
        String lieu=this.textUpdateTextLieu.getText().toString();
        String contrat=this.updateSpinnerContrat.getSelectedItem().toString();
        String profil=this.textUpdateTextProfil.getText().toString();
        String poste=this.textUpdateTextPoste.getText().toString();

        if (this.getCurrentUser() != null){
            if (!secteur.equals(getString(R.string.text_choice_secteur)) && !fonction.isEmpty() && !lieu.isEmpty() &&
                    !contrat.equals(getString(R.string.text_choice_contrat))&& !poste.isEmpty() && !profil.isEmpty() ){

                OffreHelper.updateOffre(this.getCurrentUser().getUid(),secteur,fonction,lieu,contrat,profil,poste);

                Toast.makeText(getApplicationContext(), "Votre Offre a été bien mise à jour", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(getApplicationContext(), "Un problème est survenu!!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
