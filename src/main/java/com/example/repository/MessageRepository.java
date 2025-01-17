package com.example.repository;
import java.util.List;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByPostedBy(Integer posted_by);

    //Message insertMessage(Message message);

    //void deleteMessage(int id);

    //List<Message> getAllMessages();
}
