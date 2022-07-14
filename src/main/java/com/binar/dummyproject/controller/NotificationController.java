package com.binar.dummyproject.controller;

import com.binar.dummyproject.model.MessageResponse;
import com.binar.dummyproject.model.notification.Notification;
import com.binar.dummyproject.model.notification.NotificationResponse;
import com.binar.dummyproject.service.notification.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Notification", description = "API for processing various operations with Notification entity")
@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Operation(summary = "Get notification by userId")
    @GetMapping("/get-notification/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotification(@PathVariable Integer userId){
        List<Notification> notification = notificationService.getNotification(userId);
        List<NotificationResponse> notificationResponses =
                notification.stream()
                        .map(notification1 -> {
                            if(notification1.getOfferId() == null){
                                return new NotificationResponse(notification1,
                                        notification1.getProductId());
                            } else return new NotificationResponse(notification1,
                                    notification1.getProductId(), notification1.getOfferId());
                        })
                        .collect(Collectors.toList());
        return new ResponseEntity<>(notificationResponses, HttpStatus.OK);
    }

    @Operation(summary = "Read the notification")
    @PostMapping("read/{notifId}")
    public ResponseEntity<MessageResponse> readNotif (@PathVariable Long notifId){
        notificationService.updateIsRead(notifId);
        return new ResponseEntity(new MessageResponse("Notification read successfully"), HttpStatus.OK);
    }
}
