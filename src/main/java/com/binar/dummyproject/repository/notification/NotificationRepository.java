package com.binar.dummyproject.repository.notification;

import com.binar.dummyproject.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByNotifId (Long notifId);

    @Query(value = "select * from notification where user_id=?1", nativeQuery = true)
    List<Notification> findNotif (Integer userId);
}
