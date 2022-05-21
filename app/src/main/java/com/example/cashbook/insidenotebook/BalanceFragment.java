package com.example.cashbook.insidenotebook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cashbook.R;

public class BalanceFragment extends Fragment {

    TextView netBalance, inAmount, outAmount;
    View view;
    mainBalance activity2;

    public interface mainBalance
    {
        void onMainBalance(TextView nb, TextView iA, TextView oA);
    }
    public BalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity2 = (mainBalance) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_balance, container, false);
        return view;

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        netBalance = view.findViewById(R.id.netBalance);
        inAmount = view.findViewById(R.id.inBalance);
        outAmount = view.findViewById(R.id.outBalance);

        activity2.onMainBalance(netBalance,inAmount,outAmount);

    }
}