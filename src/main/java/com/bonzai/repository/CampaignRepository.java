package com.bonzai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonzai.model.Campaign;
import java.lang.String;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {

	List<Campaign> findByStatusNotLike(String status);
}
