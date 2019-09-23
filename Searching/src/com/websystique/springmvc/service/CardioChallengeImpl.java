package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.websystique.springmvc.model.CardioChallenge;

@Service("cardioService")
@Transactional
public class CardioChallengeImpl implements CardioChallengeService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<CardioChallenge> cardios;

	static {
		cardios = populateDummyCardios();
	}

	public List<CardioChallenge> findAllCardios() {
		return cardios;
	}

	public CardioChallenge findById(long id) {
		for (CardioChallenge user : cardios) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public CardioChallenge findByName(String name) {
		for (CardioChallenge user : cardios) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveCardio(CardioChallenge user) {
		user.setId(counter.incrementAndGet());
		cardios.add(user);
	}

	public void updateCardio(CardioChallenge user) {
		int index = cardios.indexOf(user);
		cardios.set(index, user);
	}

	public void deleteCardioById(long id) {

		for (Iterator<CardioChallenge> iterator = cardios.iterator(); iterator.hasNext();) {
			CardioChallenge user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isCardioExist(CardioChallenge user) {
		return findByName(user.getName()) != null;
	}

	private static List<CardioChallenge> populateDummyCardios() {
		List<CardioChallenge> cardios = new ArrayList<CardioChallenge>();

		// beginner
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "push up", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "squats", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "butt kicks", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "tricep dips", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "sides lunges", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "jumping jacks", "beginner"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "sit-ups", "beginner"));

		// intermediate
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "40 jumping jacks", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "30 jumping rope", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "20 high knees", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "20 butt kickers", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "30 mountain climbers", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "5 jump squats", "intermediate"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "5 burpees", "intermediate"));

		// advance
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "100 crunches", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "90 jumping jacks", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "80 lunges", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "70 squats", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "60 seconds running in place", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "50-second plank", "advance"));
		cardios.add(new CardioChallenge(counter.incrementAndGet(), "10 push up", "advance"));
		return cardios;
	}

	public void deleteAllCardios() {
		cardios.clear();
	}

	@Override
	public List<CardioChallenge> findAllCardiosByName(String name) {
		List<CardioChallenge> tList = new ArrayList<CardioChallenge>();

		for (CardioChallenge cardio : cardios) {
			
			if (cardio.getName().contains(name)) {
				tList.add(cardio);
			}
		}
		return tList;
	}

}
