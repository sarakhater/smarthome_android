package com.iot.smarthomemasterandroid.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.smarthomemasterandroid.R;
import com.iot.smarthomemasterandroid.databinding.FragmentGalleryBinding;
import com.iot.smarthomemasterandroid.ui.home.ElectricAdapter;
import com.iot.smarthomemasterandroid.ui.home.ElectricData;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView meterNoTxt = binding.meterNo;
        final TextView meterAvgConsTxt = binding.txtConsumptionId;
        final ImageView meterImg = binding.meterImageId;
        final RecyclerView recyclerView = binding.recycleView;
        // data to populate the RecyclerView with
        ArrayList<ElectricData> data = new ArrayList<>();
        data.add(new ElectricData("1/10/2021" , "W-1234","54" , "" ,"30 m3","30 EGP" ));
        data.add(new ElectricData("1/11/2021" , "W-1234","60" , "" ,"50 m3","50 EGP" ));
        data.add(new ElectricData("1/12/2021" , "W-1234","40" , "" ,"25 m3","45 EGP" ));
        data.add(new ElectricData("1/1/2022" , "W-1234","50" , "" ,"20 m3","35 EGP" ));


        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ElectricAdapter adapter = new ElectricAdapter(getActivity(), data);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}