package bulidshorturl.bvulidshorturl.dao;

import bulidshorturl.bvulidshorturl.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UrlMappindao extends JpaRepository<UrlMapping,Long> {
    @Query(value = "select * from url_mapping  where shortened_url=?",nativeQuery = true)
     Optional<UrlMapping> findbyurl(String shortendurl);
     @Query(value = " SELECT * FROM url_mapping WHERE expiration_time < ?1",nativeQuery = true)
    List<UrlMapping> findByCreationTimeBefore(Long expirationTime);
}
