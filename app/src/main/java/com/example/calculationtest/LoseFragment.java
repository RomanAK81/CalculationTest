package com.example.calculationtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculationtest.databinding.FragmentLoseBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoseFragment extends Fragment {

    public LoseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel viewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentLoseBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_lose,container,false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(getActivity());
        binding.button13.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_loseFragment_to_homeFragment);
        });

        return binding.getRoot();

//        return inflater.inflate(R.layout.fragment_lose, container, false);
    }
}
