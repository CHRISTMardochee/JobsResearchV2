package com.jobsresearch.controller.jobsresearchv2.demandeur;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.api.CvHelper;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.models.Cv;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateCvActivity extends BaseActivity {

    @Override
    public int getFragmentLayout() {return R.layout.activity_update_cv;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
        this.updateUIWhenCreating();
    }

    //FOR DESIGN
    @BindView(R.id.update_cv_activity_edit_text_name) TextInputEditText textInputUpdateTextName;
    @BindView(R.id.update_cv_activity_edit_text_email) TextInputEditText textInputUpdateTextEmail;
    @BindView(R.id.update_cv_activity_edit_text_adresse) TextInputEditText textInputUpdateTextAdresse;
    @BindView(R.id.update_cv_activity_edit_text_telephone) TextInputEditText textInputUpdateTextTelephone;
    @BindView(R.id.update_cv_activity_edit_text_objectif) TextInputEditText textInputUpdateTextObjectif;
    @BindView(R.id.update_cv_activity_edit_text_formation) TextInputEditText textInputUpdateTextFormation;
    @BindView(R.id.update_cv_activity_edit_text_competence) TextInputEditText textInputUpdateTextCompetence;
    @BindView(R.id.update_cv_activity_edit_text_centre_dinteret) TextInputEditText textInputUpdateTextCdi;

    @BindView(R.id.update_cv_activity_progress_bar)
    ProgressBar progressBar;

    @OnClick(R.id.cv_activity_button_update)
    public void onClickLoginButton() {
        updateCvInFirebase();
    }

    // 1 - Update UI when activity is creating
    private void updateUIWhenCreating() {

        if (this.getCurrentUser() != null) {

            // 7 - Get additional data from Firestore (isMentor & Username)
            CvHelper.getCv(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){

                        Cv currentCv = documentSnapshot.toObject(Cv.class);

                        //String name = TextUtils.isEmpty(currentCv.getNom()) ? getString(R.string.info_no_username_found) : currentCv.getNom();
                        String name =currentCv.getNom();
                        String email = currentCv.getEmail();
                        String adresse = currentCv.getAdresse();
                        String tel = currentCv.getTelephone();
                        String objectif = currentCv.getObjectif();
                        String formation = currentCv.getFormation();
                        String competence = currentCv.getCompetence();
                        String cdi = currentCv.getCdi();

                        textInputUpdateTextName.setText(name);
                        textInputUpdateTextEmail.setText(email);
                        textInputUpdateTextAdresse.setText(adresse);
                        textInputUpdateTextTelephone.setText(tel);
                        textInputUpdateTextObjectif.setText(objectif);
                        textInputUpdateTextFormation.setText(formation);
                        textInputUpdateTextCompetence.setText(competence);
                        textInputUpdateTextCdi.setText(cdi);
                    }
                    else{
                        textInputUpdateTextName.setText("");
                        textInputUpdateTextEmail.setText("");
                        textInputUpdateTextAdresse.setText("");
                        textInputUpdateTextTelephone.setText("");
                        textInputUpdateTextObjectif.setText("");
                        textInputUpdateTextFormation.setText("");
                        textInputUpdateTextCompetence.setText("");
                        textInputUpdateTextCdi.setText("");
                    }

                }

            });

        }
    }




    private void updateCvInFirebase(){

        this.progressBar.setVisibility(View.VISIBLE);
        String name = this.textInputUpdateTextName.getText().toString();
        String email=this.textInputUpdateTextEmail.getText().toString();
        String adresse=this.textInputUpdateTextAdresse.getText().toString();
        String telephone=this.textInputUpdateTextTelephone.getText().toString();
        String objectif=this.textInputUpdateTextObjectif.getText().toString();
        String formation=this.textInputUpdateTextFormation.getText().toString();
        String competence=this.textInputUpdateTextCompetence.getText().toString();
        String cdi=this.textInputUpdateTextCdi.getText().toString();

        if (this.getCurrentUser() != null){
            if (!name.isEmpty() && !email.isEmpty() && !adresse.isEmpty() && !telephone.isEmpty() && !objectif.isEmpty() && !formation.isEmpty() && !competence.isEmpty() && !cdi.isEmpty()){

                CvHelper.updateCv(this.getCurrentUser().getUid(),name,email,adresse,telephone,objectif,formation,competence,cdi);

                Toast.makeText(getApplicationContext(), "Votre Cv a été bien mise à jour", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(getApplicationContext(), "Vous devez rzmplir les champs!", Toast.LENGTH_LONG).show();
            }
        }
        this.progressBar.setVisibility(View.INVISIBLE);
    }
}
