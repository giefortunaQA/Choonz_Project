package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	@Query(value="SELECT * FROM track a JOIN album ar ON ar.id=a.album_id WHERE ar.id=?",nativeQuery=true)
	List<Track> findTracksInAlbums(Long id);
	
	@Query(value="SELECT * FROM track a JOIN playlist ar ON ar.id=a.playlist_id WHERE ar.id=?",nativeQuery=true)
	List<Track> findTracksInPlaylists(Long id);
	
	@Query(value="SELECT * FROM track a JOIN artist ar ON ar.id=a.id WHERE ar.id=?",nativeQuery=true)
	List<Track> findTracksInArtists(Long id);
	
	@Query(value="SELECT * FROM track a JOIN genre ar ON ar.id=a.id WHERE ar.id=?",nativeQuery=true)
	List<Track> findTracksInGenres(Long id);

}
