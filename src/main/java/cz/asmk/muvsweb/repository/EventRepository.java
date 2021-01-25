package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
