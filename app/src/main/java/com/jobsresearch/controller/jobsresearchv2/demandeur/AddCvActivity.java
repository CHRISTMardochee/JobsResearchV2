package com.jobsresearch.controller.jobsresearchv2.demandeur;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.api.CvHelper;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.models.Cv;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCvActivity extends BaseActivity {


    @Override
    public int getFragmentLayout() { return R.layout.activity_add_cv; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureToolbar();
        this.updateUIWhenCreating();
    }

    //FOR DESIGN
    @BindView(R.id.add_cv_activity_edit_text_name) TextInputEditText textInputEditTextName;
    @BindView(R.id.add_cv_activity_edit_text_email) TextInputEditText textInputEditTextEmail;
    @BindView(R.id.add_cv_activity_edit_text_adresse) TextInputEditText textInputEditTextAdresse;
    @BindView(R.id.add_cv_activity_edit_text_telephone) TextInputEditText textInputEditTextTelephone;
    @BindView(R.id.add_cv_activity_edit_text_objectif) TextInputEditText textInputEditTextObjectif;
    @BindView(R.id.add_cv_activity_edit_text_formation) TextInputEditText textInputEditTextFormation;
    @BindView(R.id.add_cv_activity_edit_text_competence) TextInputEditText textInputEditTextCompetence;
    @BindView(R.id.add_cv_activity_edit_text_centre_dinteret) TextInputEditText textInputEditTextCdi;



    @OnClick(R.id.cv_activity_button_add)
    public void onClickAddButton() {
        createCvInFirestore();
    }

    // 1 - Http request that create cv in firestore
    private void createCvInFirestore(){

        if (this.getCurrentUser() != null){

            String uid = this.getCurrentUser().getUid();
            String name = this.textInputEditTextName.getText().toString();
            String email=this.textInputEditTextEmail.getText().toString();
            String adresse=this.textInputEditTextAdresse.getText().toString().trim();
            String telephone=this.textInputEditTextTelephone.getText().toString().trim();
            String objectif=this.textInputEditTextObjectif.getText().toString().trim();
            String formation=this.textInputEditTextFormation.getText().toString().trim();
            String competence=this.textInputEditTextCompetence.getText().toString().trim();
            String cdi=this.textInputEditTextCdi.getText().toString().trim();

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(adresse)&& !TextUtils.isEmpty(cdi) && !TextUtils.isEmpty(telephone) &&
                    !TextUtils.isEmpty(objectif) && !TextUtils.isEmpty(formation) && !TextUtils.isEmpty(competence)) {

                CvHelper.createCv(uid, name, email, adresse, telephone, objectif, formation, competence, cdi).addOnFailureListener(this.onFailureListener());
                Toast.makeText(this, "Votre CV a été bien enregistrez", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Champs obligatoires", Toast.LENGTH_LONG).show();
            }
        }
    }


    // 1 - Update UI when activity is creating
    private void updateUIWhenCreating(){
        //Get email & username from Firebase
        String email = TextUtils.isEmpty(this.getCurrentUser().getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
        String name = TextUtils.isEmpty(this.getCurrentUser().getDisplayName()) ? getString(R.string.info_no_username_found) : this.getCurrentUser().getDisplayName();

        //Update views with data
        if (this.getCurrentUser() != null) {

            // 7 - Get additional data from Firestore (isMentor & Username)
            CvHelper.getCv(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Cv currentCv = documentSnapshot.toObject(Cv.class);

                    if (documentSnapshot.exists()) {
                        String name = TextUtils.isEmpty(currentCv.getNom()) ? getString(R.string.info_no_username_found) : currentCv.getNom();
                        String email = currentCv.getEmail();
                        String adresse = currentCv.getAdresse();
                        String tel = currentCv.getTelephone();
                        String objectif = currentCv.getObjectif();
                        String formation = currentCv.getFormation();
                        String competence = currentCv.getCompetence();
                        String cdi = currentCv.getCdi();

                        textInputEditTextName.setText(name);
                        textInputEditTextEmail.setText(email);
                        textInputEditTextAdresse.setText(adresse);
                        textInputEditTextTelephone.setText(tel);
                        textInputEditTextObjectif.setText(objectif);
                        textInputEditTextFormation.setText(formation);
                        textInputEditTextCompetence.setText(competence);
                        textInputEditTextCdi.setText(cdi);
                    } else {
                        textInputEditTextName.setText("");
                        textInputEditTextEmail.setText("");
                        textInputEditTextAdresse.setText("");
                        textInputEditTextTelephone.setText("");
                        textInputEditTextObjectif.setText("");
                        textInputEditTextFormation.setText("");
                        textInputEditTextCompetence.setText("");
                        textInputEditTextCdi.setText("");
                    }

                }
            });
        }
    }




}
