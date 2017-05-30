package com.bonzai.repository;

import com.bonzai.model.UserTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@Repository
public interface UserTimeRepository extends JpaRepository<UserTime,Long>{

    UserTime findTopByCampaignIdAndEndTimeIsNullOrderByIdDesc(Long campaignId);
    List<UserTime> findByCampaignId(Long id);
}
