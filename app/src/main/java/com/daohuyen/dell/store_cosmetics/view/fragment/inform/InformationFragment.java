package com.daohuyen.dell.store_cosmetics.view.fragment.inform;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.view.customer.EditInformationActivity;
import com.daohuyen.dell.store_cosmetics.view.history.HistoryBillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {
    @BindView(R.id.txt_orders)
    TextView txt_orders;
    @BindView(R.id.btn_edit_inform)
    ImageButton btn_edit_inform;
    @BindView(R.id.txt_nameRegis)
    TextView txt_nameRegis;
    @BindView(R.id.txt_email)
    TextView txt_email;
    @BindView(R.id.txt_phone)
    TextView txt_phone;
    @BindView(R.id.txt_birthday)
    TextView txt_birthday;
    @BindView(R.id.txt_address)
    TextView txt_address;
    @BindView(R.id.txt_identicard)
    TextView txt_identicard;






    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_information, container, false);
        ButterKnife.bind(this,view);
        if(Utils.getSharePreferenceValues(getContext(), Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            CustomerView customerView= Utils.getCustomerview(getContext());
            txt_email.setText(customerView.getEmail());
            txt_address.setText(customerView.getAddress());
            txt_nameRegis.setText(customerView.getFullName());
            txt_phone.setText(customerView.getPhone());
            txt_identicard.setText(customerView.getIdentityCard());
            txt_birthday.setText(Utils.getDateFromMilliseconds(customerView.getBirthday())+"");
        }
        txt_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), HistoryBillActivity.class));
            }
        });
        btn_edit_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), EditInformationActivity.class));
            }
        });
        return view;
    }

}
