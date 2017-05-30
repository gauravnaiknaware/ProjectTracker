package com.bonzai.repository;

import com.bonzai.model.CampaignUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gaurav on 7/6/17.
 */
@Repository
public interface CampaignUserRepository extends JpaRepository<CampaignUser,Long>{
}
