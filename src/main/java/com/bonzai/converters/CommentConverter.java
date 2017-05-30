package com.bonzai.converters;

import com.bonzai.dto.CommentDto;
import com.bonzai.model.Campaign;
import com.bonzai.model.Comment;
import com.bonzai.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaurav on 8/6/17.
 */
public class CommentConverter {

    public static Comment convertToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setTime(new Date());

        Campaign campaign = new Campaign();
        campaign.setId(commentDto.getCampaignId());

        User user = new User();
        user.setId(commentDto.getUserId());

        comment.setCampaign(campaign);
        comment.setUser(user);

        return comment;
    }

    public static CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setTime(comment.getTime());

        Campaign campaign = new Campaign();
        campaign.setId(comment.getCampaign().getId());
        campaign.setName(comment.getCampaign().getName());
        campaign.setDate(comment.getCampaign().getDate());
        campaign.setZone(comment.getCampaign().getZone());
        campaign.setBuildEntry(comment.getCampaign().getBuildEntry());
        campaign.setClientName(comment.getCampaign().getClientName());
        campaign.setDueDate(comment.getCampaign().getDueDate());
        campaign.setState(comment.getCampaign().getState());
        campaign.setStatus(comment.getCampaign().getStatus());
        campaign.setPriority(comment.getCampaign().getPriority());
        campaign.setTeam(comment.getCampaign().getTeam());
        campaign.setDescription(comment.getCampaign().getDescription());

        commentDto.setCampaign(campaign);

        User user = new User();
        user.setId(comment.getUser().getId());
        user.setName(comment.getUser().getName());
        user.setRole(comment.getUser().getRole());
        user.setTeam(comment.getUser().getTeam());

        commentDto.setUser(user);

        return commentDto;
    }

    public static List<CommentDto> convertToListCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<CommentDto>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setComment(comment.getComment());
            commentDto.setTime(comment.getTime());

            Campaign campaign = new Campaign();
            campaign.setId(comment.getCampaign().getId());
            campaign.setName(comment.getCampaign().getName());
            campaign.setDate(comment.getCampaign().getDate());
            campaign.setZone(comment.getCampaign().getZone());
            campaign.setBuildEntry(comment.getCampaign().getBuildEntry());
            campaign.setClientName(comment.getCampaign().getClientName());
            campaign.setDueDate(comment.getCampaign().getDueDate());
            campaign.setState(comment.getCampaign().getState());
            campaign.setStatus(comment.getCampaign().getStatus());
            campaign.setPriority(comment.getCampaign().getPriority());
            campaign.setTeam(comment.getCampaign().getTeam());
            campaign.setDescription(comment.getCampaign().getDescription());

            commentDto.setCampaign(campaign);

            User user = new User();
            user.setId(comment.getUser().getId());
            user.setName(comment.getUser().getName());
            user.setRole(comment.getUser().getRole());
            user.setTeam(comment.getUser().getTeam());

            commentDto.setUser(user);
            commentDtos.add(commentDto);
        }

        return commentDtos;
    }
}
