package com.Ai2018.ResourceServer.repositories;

import com.Ai2018.ResourceServer.models.Archive;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends MongoRepository<Archive, String> {

    List<Archive> findAllByUserIdEquals(String userId);
    Archive save(Archive archive);
    List<Archive> deleteAllByUserId(String username);
}
