package com.iot.smarthomemasterandroid.ui.slideshow;

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
import com.iot.smarthomemasterandroid.databinding.FragmentSlideshowBinding;
import com.iot.smarthomemasterandroid.ui.home.ElectricAdapter;
import com.iot.smarthomemasterandroid.ui.home.ElectricData;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        final TextView meterNoTxt = binding.meterNo;
        final TextView meterAvgConsTxt = binding.txtConsumptionId;
        final ImageView meterImg = binding.meterImageId;
        final RecyclerView recyclerView = binding.recycleView;
        // data to populate the RecyclerView with
        ArrayList<ElectricData> data = new ArrayList<>();
        data.add(new ElectricData("1/10/2021" , "G-1234","4564" , "" ,"100 m3","60 EGP" ));
        data.add(new ElectricData("1/11/2021" , "G-1234","67353" , "" ,"130 m3","80 EGP" ));
        data.add(new ElectricData("1/12/2021" , "G-1234","3424" , "" ,"80 m3","50 EGP" ));
        data.add(new ElectricData("1/1/2022" , "G-1234","2342" , "" ,"75 mw","45 EGP" ));


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