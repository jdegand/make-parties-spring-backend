package com.example.makeparties.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.makeparties.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}