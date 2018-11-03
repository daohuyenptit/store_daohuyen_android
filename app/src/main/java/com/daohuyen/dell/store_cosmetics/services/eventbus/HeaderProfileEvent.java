package com.daohuyen.dell.store_cosmetics.services.eventbus;


import com.daohuyen.dell.store_cosmetics.model.Profile;

public class HeaderProfileEvent {
    private Profile headerProfile;

    public Profile getHeaderProfile() {
        return headerProfile;
    }

    public void setHeaderProfile(Profile headerProfile) {
        this.headerProfile = headerProfile;
    }

    public HeaderProfileEvent() {

    }

    public HeaderProfileEvent(Profile headerProfile) {

        this.headerProfile = headerProfile;
    }
}
