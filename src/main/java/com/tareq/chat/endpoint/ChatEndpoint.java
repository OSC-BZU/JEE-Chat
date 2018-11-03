/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareq.chat.endpoint;

import com.tareq.chat.dao.ChatMessageDao;
import com.tareq.chat.entity.ChatMessage;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import org.glassfish.jersey.server.ManagedAsync;

/**
 *
 * @author tareq
 */
@Path("chat")
public class ChatEndpoint {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @ManagedAsync
    public List<ChatMessage> getAllMessages() {
        return new ChatMessageDao().listMessages();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createMessage(ChatMessage message) {
        new ChatMessageDao().store(message);
        ChatEndpoint.sseBroadcaster.broadcast(eventBuilder.data(message)
                .comment("testing")
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .reconnectDelay(500)
                .build());
        return Response.ok().build();
    }

    @GET
    @Path("subscribe")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void listen(@Context SseEventSink sseEventSink) {
        ChatEndpoint.sseBroadcaster.register(sseEventSink);
    }

    @Context
    public void setSse(Sse sse) {
        this.sse = sse;
        this.eventBuilder = sse.newEventBuilder();
    }

    @PostConstruct
    private void init() {
        if (ChatEndpoint.sseBroadcaster == null) {
            ChatEndpoint.sseBroadcaster = sse.newBroadcaster();
        }
    }

    @Context
    private Sse sse;

    @Context
    private OutboundSseEvent.Builder eventBuilder;

    private static SseBroadcaster sseBroadcaster;

}
