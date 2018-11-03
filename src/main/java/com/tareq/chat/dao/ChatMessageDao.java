/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareq.chat.dao;

import com.tareq.chat.entity.ChatMessage;
import com.tareq.chat.utils.EntityManagerFactoryWrapper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author tareq
 */
public class ChatMessageDao {

    private EntityManager em = EntityManagerFactoryWrapper.getEntityManager();

    public void store(ChatMessage message) {
        em.getTransaction().begin();
        try {
            em.persist(message);
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

    public List<ChatMessage> listMessages() {
        em.getTransaction().begin();
        try {
            List<ChatMessage> resultList = em.createNamedQuery("ChatMessage.list", ChatMessage.class)
                    .getResultList();
            if (resultList.isEmpty()) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                return resultList;
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }

}
