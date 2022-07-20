package com.binar.dummyproject.repository.notification;

import com.binar.dummyproject.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByNotifId (Long notifId);

    @Query(value = "select * from notification where user_id=?1", nativeQuery = true)
    List<Notification> findNotif (Integer userId);

    @Modifying
    @Query(value = "delete from notification where product_id =:product_id", nativeQuery = true)
    void deleteNotifById(@Param("product_id") Long productId);
}
