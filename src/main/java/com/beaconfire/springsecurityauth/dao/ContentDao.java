package com.beaconfire.springsecurityauth.dao;

import com.beaconfire.springsecurityauth.entity.Content;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ContentDao {

    private static List<Content> contents;

    static {
        contents = new ArrayList<>();
        contents.add(new Content(1, "content1"));
        contents.add(new Content(2, "content2"));
        contents.add(new Content(3, "content3"));
    }

    public void addNewContent(Integer id, String message){
        contents.add(new Content(id, message));
    }

    public void updateContent(Integer id, String message){
        Optional<Content> contentOptional = contents.stream().filter(content -> id.equals(content.getId())).findFirst();
        contentOptional.get().setMessage(message);
    }

    public void delete(Integer id){
        contents.removeIf(content -> id.equals(content.getId()));
    }

    public Content findById(Integer id){
        //return contents.stream().filter(content -> id.equals(content.getId())).findFirst();
        return contents.get(id-1);
    }

    public List<Content> getAll(){
        return contents;
    }

    public boolean isContentIdValid(Integer id) {
        return contents.stream().filter(c -> c.getId() == id).collect(Collectors.toList()).isEmpty();
    }
}
