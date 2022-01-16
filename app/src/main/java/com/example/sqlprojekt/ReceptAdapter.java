package com.example.sqlprojekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReceptAdapter extends ArrayAdapter<Recept> {

    Context context;
    List<Recept> arrayListRecept;

    public ReceptAdapter(@NonNull Context context, List<Recept> arrayListRecept) {
        super(context, R.layout.recept_item,arrayListRecept);

        this.context = context;
        this.arrayListRecept = arrayListRecept;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_item, null, true);

        TextView vypisNazev = view.findViewById(R.id.vypisReceptNazev);
        TextView vypisId = view.findViewById(R.id.vypisReceptId);
        vypisNazev.setText(arrayListRecept.get(position).getNazev());
        vypisId.setText(arrayListRecept.get(position).getId());

        return view;
    }
}
