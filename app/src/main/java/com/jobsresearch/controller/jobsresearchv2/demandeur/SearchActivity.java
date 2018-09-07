package com.jobsresearch.controller.jobsresearchv2.demandeur;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.jobsresearch.controller.jobsresearchv2.MainActivity;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.api.OffreHelper;
import com.jobsresearch.controller.jobsresearchv2.api.UserHelper;
import com.jobsresearch.controller.jobsresearchv2.base.BaseActivity;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;

import java.util.Date;

public class SearchActivity extends BaseActivity{


    @Override
    public int getFragmentLayout() { return R.layout.activity_search;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        this.setUpRecyclerView();
    }


    public OffreAdapter adapter;

    public void setUpRecyclerView() {

        Query query = OffreHelper.getOffresCollection().
                orderBy("secteur", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Offre> options = new FirestoreRecyclerOptions.Builder<Offre>()
                .setQuery(query, Offre.class)
                .build();

        adapter = new OffreAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OffreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Offre offre = documentSnapshot.toObject(Offre.class);
                String fonction= offre.getFonction();
                String secteur =offre.getSecteur();
                String lieu = offre.getLieu();
                String contrat= offre.getType();
                String profil = offre.getProfil();
                String poste = offre.getPoste();
                Date date = offre.getDateCreated();
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                String dt=date.toString();


                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                //PACK DATA TO SEND
                intent.putExtra("SECTEUR_KEY",secteur);
                intent.putExtra("FONCTION_KEY",fonction);
                intent.putExtra("LIEU_KEY",lieu);
                intent.putExtra("CONTRAT_KEY",contrat);
                intent.putExtra("PROFIL_KEY",profil);
                intent.putExtra("POSTE_KEY",poste);
                intent.putExtra("DATE_KEY",dt);

                //open activity
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (this.isCurrentUserLogged()){
            getMenuInflater().inflate(R.menu.login, menu);
        }else {
            getMenuInflater().inflate(R.menu.logout, menu);
        }

       /* final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchitem));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit (String text){
                Query querySearch = OffreHelper.getOffresCollection().whereEqualTo("secteur", text);
                if (!text.trim().isEmpty()) {
                    updateRecyclerView(querySearch);
                    adapter.startListening();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange (String newText){
                String userInput = newText.toLowerCase();
                if (newText.trim().isEmpty()) {
                    //getList(queryFinal);
                    adapter.startListening();
                }
                return false;
            }
        });*/




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.deleteitem:
                new AlertDialog.Builder(this)
                        .setMessage(R.string.popup_message_confirmation_delete_account)
                        .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteUserFromFirebase();
                            }
                        })
                        .setNegativeButton(R.string.popup_message_choice_no, null)
                        .show();


        }
        switch (item.getItemId()) {
            case R.id.logoutitem:
                new AlertDialog.Builder(this)
                        .setMessage(R.string.popup_message_confirmation_logout_account)
                        .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                signOutUserFromFirebase();
                            }
                        })
                        .setNegativeButton(R.string.popup_message_choice_no, null)
                        .show();

        }

        switch (item.getItemId()) {
            case R.id.addcvitem:
                Intent intent = new Intent(this, AddCvActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()) {
            case R.id.updatecvitem:
                Intent intent = new Intent(this, UpdateCvActivity.class);
                startActivity(intent);
        }

        switch (item.getItemId()) {
            case R.id.loginitem:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()) {
            case R.id.searchitem:

        }





        return super.onOptionsItemSelected(item);
    }


    private void deleteUserFromFirebase(){

        //4 - We also delete user from firestore storage
        UserHelper.deleteUser(this.getCurrentUser().getUid()).addOnFailureListener(this.onFailureListener());

        if (this.getCurrentUser() != null) {
            AuthUI.getInstance()
                    .delete(this)
                    .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(DELETE_USER_TASK));
        }
    }

    private void signOutUserFromFirebase(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(this, this.updateUIAfterRESTRequestsCompleted(SIGN_OUT_TASK));

    }


    // 2 - Identify each Http Request
    private static final int SIGN_OUT_TASK = 10;
    private static final int DELETE_USER_TASK = 20;
    // Creating identifier to identify REST REQUEST (Update username)
    private static final int UPDATE_USERNAME = 30;

    // 3 - Create OnCompleteListener called after tasks ended
    private OnSuccessListener<Void> updateUIAfterRESTRequestsCompleted(final int origin){
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                switch (origin){
                    case SIGN_OUT_TASK:
                        finish();
                        break;
                    case DELETE_USER_TASK:
                        finish();
                        break;
                    default:
                        break;
                    // 8 - Hiding Progress bar after request completed

                }
            }
        };
    }


    public void updateRecyclerView(Query query) {

        FirestoreRecyclerOptions<Offre> options = new FirestoreRecyclerOptions.Builder<Offre>()
                .setQuery(query, Offre.class)
                .build();

        adapter = new OffreAdapter(options);
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OffreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Offre offre = documentSnapshot.toObject(Offre.class);
                String fonction= offre.getFonction();
                String secteur =offre.getSecteur();
                String lieu = offre.getLieu();
                String contrat= offre.getType();
                String profil = offre.getProfil();
                String poste = offre.getPoste();
                Date date = offre.getDateCreated();
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                String dt=date.toString();


                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                //PACK DATA TO SEND
                intent.putExtra("SECTEUR_KEY",secteur);
                intent.putExtra("FONCTION_KEY",fonction);
                intent.putExtra("LIEU_KEY",lieu);
                intent.putExtra("CONTRAT_KEY",contrat);
                intent.putExtra("PROFIL_KEY",profil);
                intent.putExtra("POSTE_KEY",poste);
                intent.putExtra("DATE_KEY",dt);

                //open activity
                startActivity(intent);

            }
        });

    }



}
