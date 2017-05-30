package com.bonzai.converters;

import com.bonzai.dto.UserDto;
import com.bonzai.dto.UserTimeDto;
import com.bonzai.model.Campaign;
import com.bonzai.model.User;
import com.bonzai.model.UserTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 8/6/17.
 */
public class UserTimeConverter {

    public static UserTime convertToUserTime(UserTimeDto userTimeDto) {
        UserTime userTime = new UserTime();
        userTime.setId(userTimeDto.getId());
        userTime.setStartTime(userTimeDto.getStartTime());
        userTime.setEndTime(userTimeDto.getEndTime());

        Campaign campaign = new Campaign();
        campaign.setId(userTimeDto.getCampaignId());

        User user = new User();
        user.setId(userTimeDto.getUserId());

        userTime.setCampaign(campaign);
        userTime.setUser(user);

        return userTime;
    }

    public static UserTimeDto convertToDto(UserTime userTime) {
        UserTimeDto userTimeDto = new UserTimeDto();
        userTimeDto.setId(userTime.getId());
        userTimeDto.setStartTime(userTime.getStartTime());
        userTimeDto.setEndTime(userTime.getEndTime());

        Campaign campaign = new Campaign();
        campaign.setId(userTime.getCampaign().getId());
        campaign.setName(userTime.getCampaign().getName());
        campaign.setDate(userTime.getCampaign().getDate());
        campaign.setZone(userTime.getCampaign().getZone());
        campaign.setBuildEntry(userTime.getCampaign().getBuildEntry());
        campaign.setClientName(userTime.getCampaign().getClientName());
        campaign.setDueDate(userTime.getCampaign().getDueDate());
        campaign.setState(userTime.getCampaign().getState());
        campaign.setStatus(userTime.getCampaign().getStatus());
        campaign.setPriority(userTime.getCampaign().getPriority());
        campaign.setTeam(userTime.getCampaign().getTeam());
        campaign.setDescription(userTime.getCampaign().getDescription());

        userTimeDto.setCampaign(campaign);

        User user = new User();
        user.setId(userTime.getUser().getId());
        user.setName(userTime.getUser().getName());
        user.setRole(userTime.getUser().getRole());
        user.setTeam(userTime.getUser().getTeam());

        userTimeDto.setUser(user);

        return userTimeDto;
    }

    public static List<UserTimeDto> convertToListDto(List<UserTime> userTimes){
        List<UserTimeDto> userTimeDtos = new ArrayList<UserTimeDto>();
        for (UserTime userTime : userTimes) {
            UserTimeDto userTimeDto = new UserTimeDto();
            userTimeDto.setId(userTime.getId());
            userTimeDto.setStartTime(userTime.getStartTime());
            userTimeDto.setEndTime(userTime.getEndTime());

            Campaign campaign = new Campaign();
            campaign.setId(userTime.getCampaign().getId());
            campaign.setName(userTime.getCampaign().getName());
            campaign.setDate(userTime.getCampaign().getDate());
            campaign.setZone(userTime.getCampaign().getZone());
            campaign.setBuildEntry(userTime.getCampaign().getBuildEntry());
            campaign.setClientName(userTime.getCampaign().getClientName());
            campaign.setDueDate(userTime.getCampaign().getDueDate());
            campaign.setState(userTime.getCampaign().getState());
            campaign.setStatus(userTime.getCampaign().getStatus());
            campaign.setPriority(userTime.getCampaign().getPriority());
            campaign.setTeam(userTime.getCampaign().getTeam());
            campaign.setDescription(userTime.getCampaign().getDescription());

            userTimeDto.setCampaign(campaign);

            User user = new User();
            user.setId(userTime.getUser().getId());
            user.setName(userTime.getUser().getName());
            user.setRole(userTime.getUser().getRole());
            user.setTeam(userTime.getUser().getTeam());

            userTimeDto.setUser(user);

            userTimeDtos.add(userTimeDto);
        }
        return userTimeDtos;
    }
}
