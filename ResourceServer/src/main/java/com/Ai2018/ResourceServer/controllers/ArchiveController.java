package com.Ai2018.ResourceServer.controllers;

import com.Ai2018.ResourceServer.models.Archive;
import com.Ai2018.ResourceServer.models.Position;
import com.Ai2018.ResourceServer.services.AccountService;
import com.Ai2018.ResourceServer.services.ArchiveService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;


    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(path="/archives/upload", produces="application/json")
    public ResponseEntity<?> uploadArchive(
            @RequestBody ArrayList<Position> positions
//            Authentication authentication
    ) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Archive a = archiveService.createArchive(username, positions);
            return new ResponseEntity<Object>(a, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(new Error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(path="/archives", produces="application/json")
    public ResponseEntity<?> purchasedArchives()//Authentication authentication)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Archive> archives = archiveService.findOwnedArchives(username);
          return new ResponseEntity<>(archives, HttpStatus.OK);
    }
}
