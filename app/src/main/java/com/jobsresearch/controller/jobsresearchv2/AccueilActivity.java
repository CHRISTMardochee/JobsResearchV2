package com.jobsresearch.controller.jobsresearchv2;

import android.content.Intent;


import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.demandeur.SearchActivity;
import com.jobsresearch.controller.jobsresearchv2.recruteur.AddOffreActivity;
import com.jobsresearch.controller.jobsresearchv2.recruteur.RecruiterActivity;

import butterknife.OnClick;

public class AccueilActivity extends BaseActivity {

    @Override
    public int getFragmentLayout() { return R.layout.activity_accueil; }

    @OnClick(R.id.recruteur)
    public void onClickRecruteurButton() {
        Intent intent = new Intent(this, RecruiterActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.demandeur)
    public void onClickDemandeurButton() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }





}
