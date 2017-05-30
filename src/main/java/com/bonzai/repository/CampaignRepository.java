package com.bonzai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bonzai.model.Campaign;


import java.lang.String;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
	List<Campaign> findByStatusNotLike(String status);

	List<Campaign> findByUserIdAndStatusNotLike(Long id,String status);

	List<Campaign> findByUserIdAndStatusNotLikeAndStateLike(Long id,String status,String state);

	List<Campaign> findByAssigneeAndStatusNotLike(String assignee,String status);
	
	List<Campaign> findByAssigneeAndStatusNotLikeAndState(String assignee,String status,String state);

	@Modifying
	@Query("update Campaign c set c.assignee = ?1 where c.id = ?2")
	int setAssigneeForCamapign(String assignee,long id);
	
}
