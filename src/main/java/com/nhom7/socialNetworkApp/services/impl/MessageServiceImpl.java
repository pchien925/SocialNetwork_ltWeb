package com.nhom7.socialNetworkApp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.entity.Message;
import com.nhom7.socialNetworkApp.repository.MessageRepository;
import com.nhom7.socialNetworkApp.services.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
	 
	@Autowired
	MessageRepository repository;

	@Override
	public List<Message> findByUsers(Long idUser1, Long idUser2, Integer page) {
		return repository.findByUsers(idUser1, idUser2, page);
	}

	@Override
	public Long findLastIdMessageFromUsers(Long idUser1, Long idUser2) {
		return repository.findLastIdMessageFromUsers(idUser1, idUser2);
	}

	@Override
	public <S extends Message> S save(S entity) {
		return repository.save(entity);
	}

	@Override
	public List<Message> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Message> findById(Long id) {
		return repository.findById(id);
	}
	@Override
	public boolean getIsLastPage(Long lastId, List<Message> messagePage) {
        //Check PostService for details (this method works in a similar way, but with messages)
        if (lastId == null) {
            return true;
        }
        return messagePage.get(messagePage.size()-1).getId().equals(lastId);
    }

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
