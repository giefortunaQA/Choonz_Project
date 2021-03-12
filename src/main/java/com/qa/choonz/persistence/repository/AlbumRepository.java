package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	@Query(value="SELECT * FROM album a JOIN artist ar ON ar.id=a.artist_id WHERE ar.id=?",nativeQuery=true)
	List<Album> findAlbumsInArtist(Long id);
	
	@Query(value="SELECT * FROM album a JOIN genre g ON ar.id=a.id WHERE g.id=?",nativeQuery=true)
	List<Album> findAlbumsInGenres(Long id);

}
