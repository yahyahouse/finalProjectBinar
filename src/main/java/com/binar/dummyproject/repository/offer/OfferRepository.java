package com.binar.dummyproject.repository.offer;

import com.binar.dummyproject.model.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "select * from offer f where f.user_id=:userId", nativeQuery = true)
    List<Offer> getAllByUserId(Integer userId);

    @Query(value = "select offer.offer_id, offer.offer_price, offer.offer_status, offer.product_id, offer.user_id, offer.local_date_time " +
            "from offer inner join(select product_id from product where product.user_id =user_id)p on offer" +
                    ".product_id =p.product_id ", nativeQuery = true)
    List<Offer> getOfferBySeller(Integer userId, Long productId);

    @Query(value = "select * from offer where offer_status like 'Diminati%' and offer_id=:offerId",nativeQuery = true)
    List<Offer> getOfferDetailByOfferId(Long offerId);

    @Query(value = "select * from offer o join product p on p.product_id=o.product_id where o.offer_status like 'Diminati%' and o.user_id=:userId",nativeQuery = true)
    List<Offer> getOfferByStatusDiminatiAndUserId(Long userId);
}
