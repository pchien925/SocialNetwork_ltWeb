package com.nhom7.socialNetworkApp.services;

import java.util.Optional;

import com.nhom7.socialNetworkApp.entity.Status;

public interface IStatusService {

	void delete(Status entity);

	Optional<Status> findById(Long id);

	<S extends Status> S save(S entity);

}
