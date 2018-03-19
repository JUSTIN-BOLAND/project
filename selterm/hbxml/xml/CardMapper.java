package com.deyi.dao;

import com.deyi.entity.Card;
import com.deyi.util.Page;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardMapper {
    List<Card> getPageCards(Page<Card> page);
    List<Card> getCards(Card card);
    Card getCardByCode(Card card);
    Card getCard(Integer id);
    int insert(Card card);
    int update(Card card);
    int delete(Integer id);
}
