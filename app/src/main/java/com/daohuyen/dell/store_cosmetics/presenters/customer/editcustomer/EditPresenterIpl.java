package com.daohuyen.dell.store_cosmetics.presenters.customer.editcustomer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.daohuyen.dell.store_cosmetics.common.Constants;
import com.daohuyen.dell.store_cosmetics.common.Utils;
import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.body.CustomerBody1;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;
import com.daohuyen.dell.store_cosmetics.view.customer.EditInformView;
import com.daohuyen.dell.store_cosmetics.view.fragment.inform.InformationFragment;

import org.greenrobot.eventbus.EventBus;

public class EditPresenterIpl implements EditPresenter {
    private Context context;
    private EditInformView editInformView;
    private EditInteractor editProfileInterator;

    public EditPresenterIpl(Context context, EditInformView editInformView) {
        this.context = context;
        this.editInformView = editInformView;
        this.editProfileInterator=new EditInteractorIpl(context);
    }

    @Override
    public void validateInform( String fullName, String phone, String address, String identityCard, String avatarUrl, int gender, long birthday, String email,String description) {
        if (fullName.isEmpty()) {
            editInformView.showFullNameError();
            return;
        }
        if (phone.isEmpty()) {
            editInformView.showPhoneError();
            return;
        }
        if (address.isEmpty()) {
            editInformView.showAddressError();
            return;
        }
        if (identityCard.isEmpty()) {
            editInformView.showIDCardError();
            return;
        }
        if (email.isEmpty()) {
            editInformView.showEmailError();
            return;
        }
        String customerID = Utils.getSharePreferenceValues(context, Constants.CUSTOMER_ID);

        editInformView.showLoadingDiaolog();
        CustomerBody1 customerBody1 = new CustomerBody1(fullName,phone,gender,address,identityCard, description, avatarUrl,birthday,email);
//        if (uri != null) {
//            FirebaseApp.initializeApp(context);
//            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//            StorageReference sr = firebaseStorage.getReferenceFromUrl("gs://banhangonline-187609.appspot.com/");
//            StorageReference storageReference = firebaseStorage.getReference().child("Customer/" + customerID);
//            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    profileBody.setAvatarUrl(taskSnapshot.getDownloadUrl().toString());
//                    Log.i("firebaswURI", "onSuccess: " + taskSnapshot.getDownloadUrl().toString());
//                    updateProfile(profileBody, customerID);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.i("firebaswURI", "onSuccess:" + e.getCause().toString());
//                    Toast.makeText(context, "Upload Image Fail!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            });
//        } else {
//            profileBody.setAvatarUrl(avatarUrl);
//        }

        updateProfile(customerBody1);

    }
    public void updateProfile(CustomerBody1 customerBody1) {
        editProfileInterator.editCustomer(customerBody1, new OnGetEditSuccess() {
            @Override
            public void onSuccess(CustomerView customer) {
                editInformView.hideLoadingDialog();
                editInformView.backToProfileScreen();
                Toast.makeText(context, "Luu thanh cong", Toast.LENGTH_SHORT).show();
//                EventBus.getDefault().post(new ProfileChangeEvent(profile));
//                HeaderProfile headerProfile = new HeaderProfile(customerID, profile.getFullName(),
//                        profile.getAvatarUrl(), profile.getEmail());
//                EventBus.getDefault().post(new HeaderProfileEvent(headerProfile));
//                Utils.saveHeaderProfile(context,headerProfile);

            }

            @Override
            public void onError(String message) {
                editInformView.hideLoadingDialog();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onViewDestroy() {
        editProfileInterator.onViewDestroy();

    }
}
