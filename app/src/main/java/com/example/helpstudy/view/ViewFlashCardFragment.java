package com.example.helpstudy.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewFlashCardFragment extends Fragment {

    AnimatorSet backanim, frontanim;
    Boolean isFront = true;
    TextView textFront, textBack, textInstrucao;
    String bundleFront, bundleBack;
    View view;

    Handler handler;

    Boolean canClick = true;
    FloatingActionButton fb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_view_flash_card, container, false);
        Bundle bundle = getArguments();
        textFront = view.findViewById(R.id.front_card);
        textBack = view.findViewById(R.id.back_card);
        textInstrucao = view.findViewById(R.id.textoInstrucao);
        fb = view.findViewById(R.id.btvoltar);
        handler = new Handler();
        //pegar o texto vindo do flashcard
        if(bundle != null){

            bundleFront = getArguments().getString("front");
            bundleBack = getArguments().getString("back");
            textFront.setText(bundleFront);
            textBack.setText(bundleBack);
        }

        View card_back = view.findViewById(R.id.back_card);
        View card_front = view.findViewById(R.id.front_card);

        float scale = getContext().getResources().getDisplayMetrics().density;
        card_back.setCameraDistance(8000 * scale);
        card_front.setCameraDistance(8000 * scale);


        //instanciar as variaveis do anim

        frontanim = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.front_animator);
        backanim = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.back_animator);

        textFront.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(canClick){


                    if(isFront){

                        frontanim.setTarget(card_front);
                        backanim.setTarget(card_back);
                        frontanim.start();
                        backanim.start();
                        isFront= false;
                    }else{

                        frontanim.setTarget(card_back);
                        backanim.setTarget(card_front);
                        backanim.start();
                        frontanim.start();
                        isFront = true;

                    }

                    canClick = false;


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            canClick = true;
                        }
                    }, 1500 );
                }
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new FlashCardFragment());
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}