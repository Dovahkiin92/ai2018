package com.Ai2018.ResourceServer.services;

import com.Ai2018.ResourceServer.models.Archive;
import com.Ai2018.ResourceServer.models.Position;
import com.Ai2018.ResourceServer.repositories.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArchiveService {
    public int MAXIMUM_SPEED =100;
    @Autowired
    ArchiveRepository archiveRepository;
    @Autowired
    private PositionService positionRepository;

    @Transactional
    public Archive createArchive(String userId, ArrayList<Position> positions) throws Exception {
        positionRepository.checkPositions(positions); // Will throw if any position is not valid.
        Archive a = new Archive();
        a.setUserId(userId);
     //   a.setPurchases(0);
      //  a.setPrice(positions.size() * PRICE_PER_POSITION);
   //     a.setDeleted(false);
        a.setPositions(positions);
        archiveRepository.save(a);
        return a;
    }

    public List<Archive> findOwnedArchives(String username) {
        return archiveRepository.findAllByUserIdEquals(username);
    }

    public List<Archive> findUserArchives(String username) {
        return archiveRepository.findAllByUserIdEquals(username);
    }

    public List<Archive> deleteUserArchives(String username) {
        return archiveRepository.deleteAllByUserId(username);
    }
}
