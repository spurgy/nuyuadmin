package com.springbootexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootexample.model.Gender;


@Repository("genderRepository")
public interface GenderRepository extends JpaRepository<Gender, Long> {
}
