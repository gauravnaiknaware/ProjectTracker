package com.bonzai.controllers;

import com.bonzai.converters.CampaignConverter;
import com.bonzai.dto.CampaignDto;
import com.bonzai.model.Campaign;
import com.bonzai.repository.UserRepository;
import com.bonzai.services.CampaignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	final static Logger log = Logger.getLogger(CampaignController.class);
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CampaignDto> saveCampaign(@RequestBody CampaignDto campaignDto){
		campaignService.save(CampaignConverter.convertToCampaign(campaignDto));
		log.debug("Added ::");
		return new ResponseEntity<CampaignDto>(campaignDto,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCampaign(@RequestBody CampaignDto campaignDto){
		Campaign exCampaign = campaignService.getById(campaignDto.getId());
		if(exCampaign == null) {
			log.debug("campaign does not exists.");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else {
			campaignService.save(CampaignConverter.convertToCampaign(campaignDto));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<CampaignDto> getCampaign(@PathVariable("id") long id){
		CampaignDto campaignDto = CampaignConverter.convertToDto(campaignService.getById(id));
		if(campaignDto == null) {
			log.debug("Campaign with id :"+id+" does not exists.");
			return new ResponseEntity<CampaignDto>(HttpStatus.NOT_FOUND);
		}
		log.debug("found campaign :: "+campaignDto);
		return new ResponseEntity<CampaignDto>(campaignDto,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
		List<CampaignDto> campaignDtos = CampaignConverter.convertToListDto(campaignService.getAll());
		if(campaignDtos.isEmpty()){
			log.debug("campaigns does not exists");
			return new ResponseEntity<List<CampaignDto>>(HttpStatus.NO_CONTENT);
		}
		log.debug("found "+campaignDtos.size()+" campaigns");
		log.debug(campaignDtos.toString());
		return new ResponseEntity<List<CampaignDto>>(campaignDtos,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCampaign(@PathVariable("id") long id){
		Campaign campaign = campaignService.getById(id);
		if(campaign == null) {
			log.debug("Campaign with id :: "+id+" does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
			campaignService.delete(id);
			log.debug("Campaign with id ::"+id+" deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ResponseEntity<Campaign> updateValue(@PathVariable("id") long id,
												HttpSession session,
												@RequestParam("key")String key,
												@RequestParam("value")String value){
		Campaign campaign = campaignService.getById(id);
		if(campaign == null) {
			log.debug("Campaign with id :: "+id+" does not exists");
			return new ResponseEntity<Campaign>(HttpStatus.NOT_FOUND);
		}else{
			switch (key) {
				case "status":
					campaign.setStatus(value);
					break;
				default:
					break;
			}
			campaignService.save(campaign);
			log.debug("test==");
			return new ResponseEntity<Campaign>(HttpStatus.OK);
		}

	}
	
}
