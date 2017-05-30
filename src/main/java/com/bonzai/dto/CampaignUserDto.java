package com.bonzai.dto;

import com.bonzai.model.Campaign;
import com.bonzai.model.User;


/**
 * Created by gaurav on 8/6/17.
 */
public class CampaignUserDto {
    private long id;
    private long userId;
    private long campaignId;
    private Campaign campaign;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
