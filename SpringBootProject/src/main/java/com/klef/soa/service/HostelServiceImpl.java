package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Hostel;
import com.klef.soa.repository.HostelRepository;

@Service
public class HostelServiceImpl implements HostelService{
	@Autowired
	private HostelRepository repo;

	@Override
	public Hostel addHostel(Hostel h) {
		// TODO Auto-generated method stub
		return repo.save(h);
	}


@Override
public Hostel updateHostel(Hostel h) {
    if (h.getId() == null) {
        return null;
    }

    Optional<Hostel> optional = repo.findById(h.getId());

    if (optional.isPresent()) {
        Hostel hostel = optional.get();

        hostel.setName(h.getName());
        hostel.setType(h.getType());
        hostel.setStatus(h.isStatus());
        hostel.setLocation(h.getLocation());

        return repo.save(hostel);
    } else {
        return null;
    }
}


	@Override
	public String deleteHostelById(Long id) {
		// TODO Auto-generated method stub
		boolean flag = repo.existsById(id);
		if(flag)
		{
			repo.deleteById(id);
			return "Hostel deleted successfully";
			
		}
		else
		{
			return "Hostel ID not found";
		}
	}

	@Override
	public Hostel displayById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Hostel> displayAllHostels() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Hostel> findByType(String type) {
		// TODO Auto-generated method stub
		return repo.findByType(type);
	}

	@Override
	public List<Hostel> findByName(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name);
	}

}
