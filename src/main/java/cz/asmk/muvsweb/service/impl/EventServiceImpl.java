package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Event;
import cz.asmk.muvsweb.repository.EventRepository;
import cz.asmk.muvsweb.service.api.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public Event get(Long id) {
		return eventRepository.findById(id).orElse(null);
	}

	@Override
	public List<Event> findAll() {
		return IterableUtils.toList(eventRepository.findAll());
	}

	@Override
	public Event delete(Long id) {
		Event event = this.get(id);
		eventRepository.delete(event);
		return event;
	}

	@Override
	public Event update(Event entity) {
		Event event = this.get(entity.getId());
		if(Objects.isNull(event)) throw new IllegalArgumentException("Image not found in database!");
		event = entity;
		return eventRepository.save(event);
	}

	@Override
	public Event save(Event entity) {
		return eventRepository.save(entity);
	}
}
