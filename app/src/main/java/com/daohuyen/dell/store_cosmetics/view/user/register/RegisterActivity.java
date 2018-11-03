package com.daohuyen.dell.store_cosmetics.view.user.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody;
import com.daohuyen.dell.store_cosmetics.presenters.register.RegisterPresenter;
import com.daohuyen.dell.store_cosmetics.presenters.register.RegisterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    LoadingDialog loadingDialog;
    RegisterPresenter presenter;
    ProgressBar progressBar;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pass)
    EditText et_pass;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.bt_register)
    Button bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Các sản phẩm danh mục");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadingDialog = new LoadingDialog(this);
        presenter = new RegisterPresenterImpl(this, this);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerBody body = new CustomerBody(et_name.getText().toString(),
                        et_address.getText().toString(), et_username.getText().toString(),et_pass.getText().toString());
                presenter.validateUsernameAndPassword(body);

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.hide();
    }
    @Override
    public void showUserNameError() {
        et_username.setError("Must not empty!");

    }

    @Override
    public void showPasswordError() {
        et_pass.setError("Must not empty!");
    }

    @Override
    public void showFullNameError() {
        et_name.setError("Must not empty!");


    }

    @Override
    public void showAddressError() {
        et_address.setError("Must not empty!");

    }

    @Override
    public void showPhoneError() {
        et_phone.setError("Must not empty!");
    }

    @Override
    public void showInvalidUser() {
        et_username.setError("Invalid email");
    }

    @Override
    public void finishActivity() {
         finish();
    }

}
