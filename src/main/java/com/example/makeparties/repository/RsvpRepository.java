package com.example.makeparties.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.makeparties.entity.Rsvp;

public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
}