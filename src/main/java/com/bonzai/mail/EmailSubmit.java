package com.bonzai.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Configuration
@Component
public class EmailSubmit {

	final static Logger logger = Logger.getLogger(EmailSubmit.class);
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EmailHtmlSender emailHtmlSender;

	@Autowired
	EmailSender emailSender;

	
	public Date getYesterDayDate(){
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.DAY_OF_MONTH, -1);
	    return c.getTime();
	}
}
