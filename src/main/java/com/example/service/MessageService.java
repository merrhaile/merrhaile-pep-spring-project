package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.MessageLengthOutOfRangeException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.UserLogInException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    /**
     * 
     * @param messageRepository
     * @param accountRepository
     * both repositories are being autowired into the class
     */
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * uses messageRepository to register a new message
     * @param message
     * @return message with id
     * @throws MessageLengthOutOfRangeException
     * @throws ResourceNotFoundException
     */
    public Message createMessage(Message message) throws MessageLengthOutOfRangeException, ResourceNotFoundException{
        boolean checkAccount = accountRepository.existsById(message.getPostedBy());
        if(checkAccount == false){
            throw new ResourceNotFoundException("User not in dataabase");
        }
        else if(message.getMessageText().length() == 0 || message.getMessageText().length() > 255){
            throw new MessageLengthOutOfRangeException("Message is empty or exceeds 255 char-length");
        }
        else return messageRepository.save(message);
    }

    /**
     * retrieves all message from the database
     * @return
     */
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    /**
     * 
     * @param id
     * @return Message
     */
    public Message getMessageByMessageId(Integer id){
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()) return messageRepository.findById(id).get();
        else return null;
    }

    /**
     * 
     * @param id
     * @return Integer
     */
    public Integer deleteMessageByMessageId(Integer id){
        if(messageRepository.findById(id).isPresent()){
            messageRepository.deleteById(id);
            return 1;
        }
        return null;
    }

    public Message getAllMessagesForUser(){
        return new Message();
    }

    public Message updateMessageByMessageId(Integer id, Message message){
        return new Message();
    }

    
}
