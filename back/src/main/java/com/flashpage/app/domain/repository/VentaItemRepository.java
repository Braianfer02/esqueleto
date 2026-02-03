package com.flashpage.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.domain.model.VentaItem;

@Repository
public interface VentaItemRepository extends JpaRepository<VentaItem, Long> {}