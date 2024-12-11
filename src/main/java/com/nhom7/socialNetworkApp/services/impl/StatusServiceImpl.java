package com.nhom7.socialNetworkApp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.entity.Status;
import com.nhom7.socialNetworkApp.repository.StatusRepository;
import com.nhom7.socialNetworkApp.services.IStatusService;
@Service
public class StatusServiceImpl implements IStatusService{
	@Autowired
	StatusRepository statusRepository;

	@Override
	public <S extends Status> S save(S entity) {
		return statusRepository.save(entity);
	}

	@Override
	public Optional<Status> findById(Long id) {
		return statusRepository.findById(id);
	}

	@Override
	public void delete(Status entity) {
		statusRepository.delete(entity);
	}
	
}
