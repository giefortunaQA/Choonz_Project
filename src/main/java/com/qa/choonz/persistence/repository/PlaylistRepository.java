package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.qa.choonz.persistence.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	

}
