package com.infygo.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infygo.tickets.entity.Passanger;




@Repository
public interface PassangerRepository extends JpaRepository<Passanger, Integer>{

}