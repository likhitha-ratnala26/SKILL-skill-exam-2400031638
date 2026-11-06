package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long>{
	List<Hostel> findByName(String name);
	List<Hostel> findByType(String type);
}
