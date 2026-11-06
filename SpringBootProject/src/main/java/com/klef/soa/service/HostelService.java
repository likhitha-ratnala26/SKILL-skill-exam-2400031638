package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Hostel;

public interface HostelService {
	Hostel addHostel(Hostel h);
	Hostel updateHostel(Hostel h);
	String deleteHostelById(Long id);
	Hostel displayById(Long id);
	List<Hostel> displayAllHostels();
	List<Hostel> findByType(String type);
	List<Hostel> findByName(String name);

}
