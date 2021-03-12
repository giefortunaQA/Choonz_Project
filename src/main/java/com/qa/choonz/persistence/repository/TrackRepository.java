package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	@Query(value="SELECT t.* FROM track t JOIN album a ON a.id=t.album_id WHERE a.id=?",nativeQuery=true)
	List<Track> findTracksInAlbums(Long id);
	
	@Query(value="SELECT t.* FROM track t JOIN playlist p ON p.id=t.playlist_id WHERE p.id=?",nativeQuery=true)
	List<Track> findTracksInPlaylists(Long id);
	
	@Query(value="SELECT t.* FROM album a JOIN artist ar ON ar.id=a.id JOIN track t ON t.album_id=a.id WHERE ar.id=?",nativeQuery=true)
	List<Track> findTracksInArtists(Long id);
	
	@Query(value="SELECT t.* FROM track t JOIN album a ON t.album_id=a.id JOIN genre g ON g.id=a.genre_id WHERE g.id=?",nativeQuery=true)
	List<Track> findTracksInGenres(Long id);

}
