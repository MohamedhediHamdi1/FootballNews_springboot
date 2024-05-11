package com.FootballNews.FootballNews.Repositories;

import com.FootballNews.FootballNews.Entities.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Long> {
NewsEntity findByImage(String id);

NewsEntity findByTitle(String title);

@Query(value = "SELECT * FROM news n ORDER BY n.date DESC", nativeQuery = true)
Page<NewsEntity> findpage(Pageable pageable);


}
