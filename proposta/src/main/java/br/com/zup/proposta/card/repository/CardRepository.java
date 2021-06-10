package br.com.zup.proposta.card.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.proposta.card.entity.Card;

public interface CardRepository extends CrudRepository<Card, Long>{

	Optional<Card> findByCardNumber(String cardNumber);

}
