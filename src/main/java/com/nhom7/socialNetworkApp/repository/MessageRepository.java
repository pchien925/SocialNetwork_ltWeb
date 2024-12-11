package com.nhom7.socialNetworkApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	@Query(value = "SELECT m.* FROM message m " +
            "WHERE (m.user_transmitter_id = ?1 AND m.user_receiver_id = ?2) " +
            "OR (m.user_transmitter_id = ?2 AND m.user_receiver_id = ?1) " +
            "ORDER BY m.created_at DESC LIMIT 10",
            nativeQuery = true)
    List<Message> findByUsers(Long idUser1, Long idUser2, Integer page);

    @Query(value = "SELECT MIN(m.id) FROM message m " +
            "WHERE (m.user_transmitter_id = ?1 AND m.user_receiver_id = ?2) " +
            "OR (m.user_transmitter_id = ?2 AND m.user_receiver_id = ?1) " +
            "ORDER BY m.created_at DESC",
            nativeQuery = true)
    Long findLastIdMessageFromUsers(Long idUser1, Long idUser2);

}
