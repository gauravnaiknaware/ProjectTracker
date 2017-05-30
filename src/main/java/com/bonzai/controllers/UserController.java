package com.bonzai.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonzai.mail.EmailSender;
import com.bonzai.model.User;
import com.bonzai.repository.UserRepository;
import com.bonzai.services.UserService;

/**
 * Created by gaurav on 5/5/17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    final static Logger log = Logger.getLogger(UserController.class);
    private String subject = "Invitation to join team";
    
    
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    EmailSender emailSender;


    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) throws UnsupportedEncodingException{
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    	String pass = Base64.getEncoder().encodeToString(uuid.getBytes("utf-8"));

    	user.setPassword(pass);
        userService.save(user);
        
       // String text = "username :"+user.getName()+" password :"+uuid;
        //emailSender.sendS(user.getName(), subject, text, false);
        
        log.debug("users : "+user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value="/team",method=RequestMethod.GET)
    public ResponseEntity<List<User>> getUserByTeam(@RequestParam("team") String team){
        List<User> users = userRepository.findUserByTeam(team);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        User exUser = userService.getById(user.getId());
        if(exUser == null){
            log.debug("user with id :"+user.getId()+" does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{
            userService.save(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }
    
   

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        User user = userService.getById(id);

        if(user == null){
            log.debug("user with id :"+id+" does not exists");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        log.debug("found user :"+user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            log.debug("users does not exists");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        log.debug("users found "+users);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id){
        User user = userService.getById(id);
        if(user == null){
            log.debug("user with id :"+id+" does not working");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{
            userService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value="/{email}/email",method = RequestMethod.GET)
    public ResponseEntity<Long> isEmailExist(@PathVariable("email") String email){
    	log.debug(email);
        User user = userRepository.findUserByName(email);
        if(user == null){
            log.debug("user with email :"+email+" does not found");
            return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
        }
        log.debug("user found "+user);
        return new ResponseEntity<Long>(user.getId(),HttpStatus.OK);
    }
    
    @RequestMapping("/update/password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable("id") long id,@RequestParam("old") String old,@RequestParam("new") String newpass) throws UnsupportedEncodingException{
    	User user = userService.getById(id);
    	if(user == null){
    		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    	}
    	
    	
    	String pass = new String( Base64.getDecoder().decode(user.getPassword()), "utf-8" );
    	
    	if(!old.equals(pass) ){
    		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    	}
    	String newPass = Base64.getEncoder().encodeToString(newpass.getBytes("utf-8"));
    	user.setPassword(newPass);
    	userService.save(user);
    	return new ResponseEntity<User>(user,HttpStatus.OK);
    }
   
}
