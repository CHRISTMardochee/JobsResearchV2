package com.jobsresearch.controller.jobsresearchv2.demandeur;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.instrumentation.stats.Tag;
import com.jobsresearch.controller.jobsresearchv2.R;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;

public class OffreAdapter extends FirestoreRecyclerAdapter<Offre, OffreAdapter.OffreHolder> {

    private OnItemClickListener listener;

    public OffreAdapter(@NonNull FirestoreRecyclerOptions<Offre> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OffreHolder holder, int position, @NonNull Offre model) {
        holder.textViewSecteur.setText(model.getSecteur());
        holder.textViewContrat.setText(model.getType());
        holder.textViewPosteDescription.setText(model.getPoste());

    }

    @NonNull
    @Override
    public OffreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offre_item,
                parent, false);
        return new OffreHolder(v);
    }

    class OffreHolder extends RecyclerView.ViewHolder {
        TextView textViewSecteur;
        TextView textViewPosteDescription;
        TextView textViewContrat;

        public OffreHolder(View itemView) {
            super(itemView);
            textViewSecteur = itemView.findViewById(R.id.secteur);
            textViewPosteDescription = itemView.findViewById(R.id.desc_poste);
            textViewContrat = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
                });



        }
    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateOptions(FirestoreRecyclerOptions<Offre> newOptions){


    }

}
