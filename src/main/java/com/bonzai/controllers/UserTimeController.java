package com.bonzai.controllers;

import com.bonzai.converters.UserTimeConverter;
import com.bonzai.dto.UserTimeDto;
import com.bonzai.model.UserTime;
import com.bonzai.repository.UserTimeRepository;
import com.bonzai.services.UserTimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@RestController
@RequestMapping("/time")
public class UserTimeController {

    final static Logger log = Logger.getLogger(UserTimeController.class);

    @Autowired
    UserTimeService userTimeService;

    @Autowired
    UserTimeRepository userTimeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserTime> save(@RequestBody UserTimeDto userTimeDto) {
        UserTime userTime = UserTimeConverter.convertToUserTime(userTimeDto);
        userTimeService.save(userTime);
        return new ResponseEntity<UserTime>(userTime, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserTimeDto userTimeDto) {
        UserTime userTime1 = userTimeService.getById(userTimeDto.getId());
        if (userTime1 == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userTimeService.save(UserTimeConverter.convertToUserTime(userTimeDto));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserTimeDto> getbyid(@PathVariable("id") Long id) {
        UserTimeDto userTimeDto = UserTimeConverter.convertToDto(userTimeService.getById(id));
        if (userTimeDto == null) {
            return new ResponseEntity<UserTimeDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserTimeDto>(userTimeDto,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserTimeDto>> getAll() {
        List<UserTimeDto> userTimeDtos = UserTimeConverter.convertToListDto(userTimeService.getAll());
        if (userTimeDtos.isEmpty()) {
            return new ResponseEntity<List<UserTimeDto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserTimeDto>>(userTimeDtos,HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCA(@PathVariable("id")Long id) {
        UserTime userTime = userTimeService.getById(id);
        if (userTime == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userTimeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

    @RequestMapping(value="/campaign/{campaignId}")
    public ResponseEntity<List<UserTimeDto>> getByCampaign(@PathVariable("campaignId") long campignId) {
        List<UserTimeDto> userTimeDtos = UserTimeConverter.convertToListDto(userTimeRepository.findByCampaignId(campignId));
        return new ResponseEntity<List<UserTimeDto>>(userTimeDtos,HttpStatus.OK);
    }
}
