package com.daohuyen.dell.store_cosmetics.view.customer;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer.EditPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer.EditPresenterIpl;
import com.daohuyen.dell.store_cosmetics.view.fragment.inform.InformationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInformationActivity extends AppCompatActivity implements EditInformView,View.OnClickListener{
    @BindView(R.id.img_save)
    ImageButton img_save;
    @BindView(R.id.img_date)
    ImageButton img_date;
    @BindView(R.id.edt_des)
    EditText edt_des;
    @BindView(R.id.edt_card)
    EditText edt_card;
    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_address)
    EditText edt_address;
    @BindView(R.id.edt_birthday)
    EditText edt_birthday;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_gender)
    EditText edt_gender;
    LoadingDialog loadingDialog;
    EditPresenter presenter;
    long birthday=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);
        presenter=new EditPresenterIpl(this,this);
        img_save.setOnClickListener(this);
        img_date.setOnClickListener(this);
        if(Utils.getSharePreferenceValues(this, Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            CustomerView customerView= Utils.getCustomerview(this);
            edt_email.setText(customerView.getEmail());
            edt_name.setText(customerView.getFullName());
            edt_address.setText(customerView.getAddress());
            edt_phone.setText(customerView.getPhone());
            edt_card.setText(customerView.getIdentityCard());
            edt_gender.setText(customerView.getGender());
            edt_des.setText(customerView.getDescription());
            edt_birthday.setText(Utils.getDateFromMilliseconds(customerView.getBirthday())+"");
        }



    }

    @Override
    public void showLoadingDiaolog() {
        loadingDialog.show();

    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.hide();

    }

    @Override
    public void showFullNameError() {
        edt_name.setError(getString(R.string.must_not_empty));

    }

    @Override
    public void showAddressError() {
        edt_address.setError(getString(R.string.must_not_empty));

    }

    @Override
    public void showEmailError() {
        edt_email.setError(getString(R.string.must_not_empty));

    }

    @Override
    public void showPhoneError() {
        edt_phone.setError(getString(R.string.must_not_empty));

    }

    @Override
    public void showIDCardError() {
        edt_card.setError(getString(R.string.must_not_empty));

    }

    @Override
    public void backToProfileScreen() {
        finish();



    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.img_save:
                int gender=0;
                if(edt_gender.getText().toString().equals("Nam")){
                    gender=1;
                }
                CustomerView customerView=new CustomerView();
                customerView.setPhone(edt_phone.getText().toString());
                customerView.setIdentityCard(edt_card.getText().toString());
                customerView.setId(Utils.getSharePreferenceValues(this,Constants.CUSTOMER_ID));
                customerView.setGender(edt_gender.getText().toString());
                customerView.setDescription(edt_des.getText().toString());
                customerView.setEmail(edt_email.getText().toString());
                customerView.setBirthday(birthday);
                customerView.setAddress(edt_address.getText().toString());
                customerView.setFullName(edt_name.getText().toString());
                Utils.saveCustomer(EditInformationActivity.this,customerView);
                presenter.validateInform( edt_name.getText().toString(),
                        edt_phone.getText().toString(),
                        edt_address.getText().toString(),
                        edt_card.getText().toString(), null,
                        gender, birthday, edt_email.getText().toString(),
                        edt_des.getText().toString());
                getSupportFragmentManager().beginTransaction().add(R.id.container_activity,new InformationFragment()).commit();


                break;
            case R.id.img_date:
                Utils.dialogShowDate(this, "Chọn ngày sinh", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.format("%02d", dayOfMonth)
                                + "-" +
                                String.format("%02d", (monthOfYear + 1))
                                + "-" +
                                year;
                         birthday = Utils.milliseconds(date);
                        edt_birthday.setText(date);
                    }
                });
                break;
        }

    }
}
