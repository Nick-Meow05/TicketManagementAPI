package com.nicolasdev.ticketmanagementapi.repository;

import com.nicolasdev.ticketmanagementapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
