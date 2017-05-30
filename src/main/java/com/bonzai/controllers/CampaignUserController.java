package com.bonzai.controllers;

import com.bonzai.converters.CampaignUserConverter;
import com.bonzai.dto.CampaignUserDto;
import com.bonzai.model.CampaignUser;
import com.bonzai.services.CampaignAssigneeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@RestController
@RequestMapping("/assign")
public class CampaignUserController {

    final static Logger log = Logger.getLogger(CampaignUserController.class);

    @Autowired
    CampaignAssigneeService campaignAssigneeService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<CampaignUser> saveCA(@RequestBody CampaignUserDto campaignUserDto) {
        CampaignUser campaignUser = CampaignUserConverter.convertToCampaignUser(campaignUserDto);
        campaignAssigneeService.save(campaignUser);
        return new ResponseEntity<CampaignUser>(campaignUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCA(@RequestBody CampaignUserDto campaignUserDto) {
        CampaignUser campaignUser1 = campaignAssigneeService.getById(campaignUserDto.getId());
        if (campaignUser1 == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        campaignAssigneeService.save(CampaignUserConverter.convertToCampaignUser(campaignUserDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<CampaignUserDto> getbyidCA(@PathVariable("id") Long id) {
        CampaignUserDto campaignUserDto = CampaignUserConverter.convertToDto(campaignAssigneeService.getById(id));
        if (campaignUserDto == null) {
            return new ResponseEntity<CampaignUserDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CampaignUserDto>(campaignUserDto,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CampaignUserDto>> getAllCA() {
        List<CampaignUserDto> campaignUserDtos = CampaignUserConverter.convertToListCampaignUserDto(campaignAssigneeService.getAll());
        if (campaignUserDtos.isEmpty()) {
            return new ResponseEntity<List<CampaignUserDto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<CampaignUserDto>>(campaignUserDtos,HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCA(@PathVariable("id")Long id) {
        CampaignUser campaignUser = campaignAssigneeService.getById(id);
        if (campaignUser == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        campaignAssigneeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

}
