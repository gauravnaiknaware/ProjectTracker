package com.bonzai.Impl;

import com.bonzai.model.UserTime;
import com.bonzai.repository.UserTimeRepository;
import com.bonzai.services.UserTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@Service
public class UserTimeServiceImpl implements UserTimeService {

    @Autowired
    UserTimeRepository userTimeRepository;

    @Override
    public UserTime save(UserTime entity) {
        return userTimeRepository.save(entity);
    }

    @Override
    public UserTime getById(Serializable id) {
        return userTimeRepository.findOne((Long)id);
    }

    @Override
    public List<UserTime> getAll() {
        return userTimeRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        userTimeRepository.delete((Long)id);
    }
}
