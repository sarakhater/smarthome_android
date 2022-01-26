package com.iot.smarthomemasterandroid.ui.home;

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
import com.iot.smarthomemasterandroid.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView meterNoTxt = binding.meterNo;
        final TextView meterAvgConsTxt = binding.txtConsumptionId;
        final ImageView meterImg = binding.meterImageId;
        final RecyclerView recyclerView = binding.recycleView;
        // data to populate the RecyclerView with
        ArrayList<ElectricData> data = new ArrayList<>();
        data.add(new ElectricData("1/10/2021" , "E-1234","4564" , "" ,"100 KW","120 EGP" ));
        data.add(new ElectricData("1/11/2021" , "E-1234","67353" , "" ,"130 KW","150 EGP" ));
        data.add(new ElectricData("1/12/2021" , "E-1234","3424" , "" ,"80 KW","100 EGP" ));
        data.add(new ElectricData("1/1/2022" , "E-1234","2342" , "" ,"75 KW","80 EGP" ));


        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ElectricAdapter adapter = new ElectricAdapter(getActivity(), data);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}