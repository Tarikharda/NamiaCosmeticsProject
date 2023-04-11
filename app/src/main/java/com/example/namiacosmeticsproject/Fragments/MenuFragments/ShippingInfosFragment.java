package com.example.namiacosmeticsproject.Fragments.MenuFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.namiacosmeticsproject.R;

public class ShippingInfosFragment extends Fragment {

    PdfView pdfView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping_infos, container, false);

        // Get the PDFView from the fragment's view
        pdfView = view.findViewById(R.id.pdfView);

// Load the PDF file from assets
        pdfView.fromAsset("my_pdf_file.pdf").load();

        return view;
    }
}