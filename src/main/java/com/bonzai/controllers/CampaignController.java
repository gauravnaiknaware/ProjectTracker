package com.bonzai.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bonzai.model.Campaign;
import com.bonzai.services.CampaignService;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

	final static Logger log = Logger.getLogger(CampaignController.class);
	
	@Autowired
	CampaignService campaignService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Campaign> saveCampaign(@RequestBody Campaign campaign){
		campaignService.save(campaign);		
		log.debug("Added ::"+campaign.toString());
		return new ResponseEntity<Campaign>(campaign,HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCampaign(@RequestBody Campaign campaign){
		Campaign exCampaign = campaignService.getById(campaign.getId());
		if(exCampaign == null) {
			log.debug("campaign with id "+campaign.getId()+" does not exists.");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else {
			campaignService.save(campaign);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Campaign> getCampaign(@PathVariable("id") long id){
		Campaign campaign = campaignService.getById(id);
		if(campaign == null) {
			log.debug("Campaign with id :"+id+" does not exists.");
			return new ResponseEntity<Campaign>(HttpStatus.NOT_FOUND);
		}
		log.debug("fount campaign :: "+campaign);
		return new ResponseEntity<Campaign>(campaign,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Campaign>> getAllCampaigns() {
		List<Campaign> campaigns = campaignService.getAll();
		if(campaigns.isEmpty()){
			log.debug("campaigns does not exists");
			return new ResponseEntity<List<Campaign>>(HttpStatus.NO_CONTENT);
		}
		log.debug("found "+campaigns.size()+" campaigns");
		log.debug(campaigns.toString());
		return new ResponseEntity<List<Campaign>>(campaigns,HttpStatus.OK);
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
	
}
