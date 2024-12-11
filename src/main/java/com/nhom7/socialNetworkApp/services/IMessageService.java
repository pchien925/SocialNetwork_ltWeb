package com.nhom7.socialNetworkApp.services;

import java.util.List;
import java.util.Optional;

import com.nhom7.socialNetworkApp.entity.Message;

public interface IMessageService {

	boolean getIsLastPage(Long lastId, List<Message> messagePage);

	Optional<Message> findById(Long id);

	List<Message> findAll();

	<S extends Message> S save(S entity);

	Long findLastIdMessageFromUsers(Long idUser1, Long idUser2);

	List<Message> findByUsers(Long idUser1, Long idUser2, Integer page);

	void deleteById(Long id);

}
