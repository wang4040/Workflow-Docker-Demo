package com.beaconfire.springsecurityauth.service;

import com.beaconfire.springsecurityauth.dao.ContentDao;
import com.beaconfire.springsecurityauth.domain.request.ContentCreationRequest;
import com.beaconfire.springsecurityauth.domain.request.ContentUpdateRequest;
import com.beaconfire.springsecurityauth.entity.Content;
import com.beaconfire.springsecurityauth.exception.ContentNotFoundException;
import com.beaconfire.springsecurityauth.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private ContentDao contentDao;

    @Autowired
    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    public List<Content> getAllContent(){
        return contentDao.getAll();
    }

    public Content getContentById(Integer id) throws ContentNotFoundException{
        return Optional.ofNullable(contentDao.findById(id))
                .orElseThrow(ContentNotFoundException::new);
    }

    public void createContent(Integer id, String content) throws InvalidIdException {
        if (!contentDao.isContentIdValid(id)) {
            throw new InvalidIdException(id);
        }

        contentDao.addNewContent(id, content);
    }

    public void updateContent(Integer id, String content) {
        contentDao.updateContent(id, content);
    }

    public void deleteContent(Integer id){
        contentDao.delete(id);
    }
}
