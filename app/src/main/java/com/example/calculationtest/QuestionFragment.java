package com.example.calculationtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculationtest.databinding.FragmentHomeBinding;
import com.example.calculationtest.databinding.FragmentQuestionBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel viewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(viewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button12.setOnClickListener(v->{
            Integer rightAnswer = viewModel.getAnswer().getValue();
            String userAnswer = viewModel.getNOW_ANSWER().getValue();
            if (userAnswer.equals(getActivity().getApplication().getString(R.string.question_answer))){
                return;
            }
            if (rightAnswer==Integer.valueOf(userAnswer)){
                viewModel.answerCorrect();
            }else{
                NavController controller = Navigation.findNavController(v);
                if (viewModel.isWin()){
                    controller.navigate(R.id.action_questionFragment_to_winFragment);
                }else{
                    controller.navigate(R.id.action_questionFragment_to_loseFragment);
                }
            }

        });

        return binding.getRoot();
//      return inflater.inflate(R.layout.fragment_question, container, false);
    }
}
