package com.grupodecorrida.web.repository;

import com.grupodecorrida.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
