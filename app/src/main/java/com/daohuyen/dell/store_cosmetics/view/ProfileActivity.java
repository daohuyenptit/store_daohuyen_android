package com.daohuyen.dell.store_cosmetics.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.store_cosmetics.R;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.custom.LoadingDialog;
import com.daohuyen.dell.store_cosmetics.model.Profile;
import com.daohuyen.dell.store_cosmetics.services.eventbus.HeaderProfileEvent;
import com.daohuyen.dell.store_cosmetics.view.user.login.LoginActivity;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;
import com.sangcomz.fishbun.define.Define;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_AVATAR = 1;
    LoadingDialog loadingDialog;
    CircleImageView img_avt;
    TextView txt_name;
    TextView txt_birthday;
    TextView txt_address;
    TextView txt_email;
    ImageButton btn_edit_profile;
    ImageButton
            imgCamera;
    Toolbar toolbar;
    Profile profile;
    Uri uri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initWidget();
    }
    void initWidget() {
        img_avt = findViewById(R.id.img_avatar);
        txt_name = findViewById(R.id.txt_name);
        txt_birthday = findViewById(R.id.txt_birthday);
        txt_address = findViewById(R.id.txt_address);
        txt_email = findViewById(R.id.txt_email);
        btn_edit_profile = findViewById(R.id.btn_edit_user_profile);
        imgCamera= findViewById(R.id.img_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        imgCamera.setOnClickListener(v -> {
            updateImage();
        });
        btn_edit_profile.setOnClickListener(v -> {
            updateImage();
        });
        loadingDialog = new LoadingDialog(this);
        showProfile(Utils.getHeaderProfile(this));
    }
    public void updateImage(){
        FishBun.with(this)
                .setImageAdapter(new GlideAdapter())
                .setMaxCount(1)
                .setMinCount(1)
                .setActionBarColor(getResources().getColor(R.color.colorPrimary),
                        getResources().getColor(R.color.colorPrimaryDark),
                        false)
                .setActionBarTitleColor(getResources().getColor(android.R.color.white))
                .setButtonInAlbumActivity(false)
                .setCamera(true)
                .exceptGif(true)
                .setHomeAsUpIndicatorDrawable(ContextCompat.getDrawable(this, R.drawable.ic_back))
                .setOkButtonDrawable(ContextCompat.getDrawable(this, R.drawable.ic_select))
                .setAllViewTitle(getResources().getString(R.string.selected))
                .setActionBarTitle(getResources().getString(R.string.pick_avatar))
                .textOnNothingSelected(getResources().getString(R.string.must_pick_one_image))
                .setRequestCode(REQUEST_CODE_PICK_AVATAR)
                .startAlbum();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PICK_AVATAR: {
                if (Activity.RESULT_OK == resultCode) {
                    ArrayList<Parcelable> path = data.getParcelableArrayListExtra(Define.INTENT_PATH);
                    uri = Uri.parse(path.get(0).toString());

                    profile.setSrcAvatar(path.get(0).toString());
                    Glide.with(this).load(profile.getSrcAvatar()).apply(new RequestOptions().placeholder(R.drawable.avatar_placeholder)).into(img_avt);
//                    if(LoginActivity.userHelper.updateProfile(profile)) {
//                        Utils.saveHeaderProfile(this, profile);
//                        showAlertDialog("Cập nhật thành công");
//                        EventBus.getDefault().post(new HeaderProfileEvent(profile));
//                    }else{
//                        showAlertDialog("Cập nhật thất bại");
//                    }
                }
                break;
            }
        }
    }
    public void showAlertDialog(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Avatar");
        builder.setMessage(s);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void showProfile(Profile prof) {
        this.profile = prof;
        Glide.with(this).load(profile.getSrcAvatar()).apply(new RequestOptions().placeholder(R.drawable.avatar_placeholder)).into(img_avt);
        txt_name.setText(profile.getName() == null ? "-" : profile.getName());
        txt_birthday.setText(profile.getBirthday() == -1 ? "-" : Utils.getDateFromMilliseconds(profile.getBirthday()));
        txt_address.setText(profile.getAddress() == null ? "-" : profile.getAddress());
        txt_email.setText(profile.getUser().getUsername() == null ? "-" : profile.getUser().getUsername());
    }

}

