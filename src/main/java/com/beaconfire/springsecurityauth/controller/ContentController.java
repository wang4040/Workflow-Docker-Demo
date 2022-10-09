package com.beaconfire.springsecurityauth.controller;

import com.beaconfire.springsecurityauth.domain.ServiceStatus;
import com.beaconfire.springsecurityauth.domain.request.ContentCreationRequest;
import com.beaconfire.springsecurityauth.domain.request.ContentUpdateRequest;
import com.beaconfire.springsecurityauth.domain.response.AllContentResponse;
import com.beaconfire.springsecurityauth.domain.response.ContentResponse;
import com.beaconfire.springsecurityauth.domain.response.MessageResponse;
import com.beaconfire.springsecurityauth.entity.Content;
import com.beaconfire.springsecurityauth.exception.ContentNotFoundException;
import com.beaconfire.springsecurityauth.exception.InvalidIdException;
import com.beaconfire.springsecurityauth.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("content")
public class ContentController {

    private ContentService contentService;

    @Autowired
    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/test")
    public Object getAuthUserDetail(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('read')")
    public AllContentResponse getAllContent(){
        List<Content> contents = contentService.getAllContent();

        return AllContentResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .contents(contents)
                .build();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('read')")
    public ContentResponse getContentById(@PathVariable Integer id){
        try {
            Content content = contentService.getContentById(id);

            return ContentResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(true)
                                    .build()
                    )
                    .content(content)
                    .build();
        } catch (ContentNotFoundException e) {
            e.printStackTrace();
            return ContentResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(false)
                                    .build()
                    )
                    .build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('write')")
    public MessageResponse createContent(@RequestBody ContentCreationRequest request){
        try {
            contentService.createContent(request.getId(), request.getContent());

            return MessageResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(true)
                                    .build()
                    )
                    .message("New content created")
                    .build();
        } catch (InvalidIdException e) {
            e.printStackTrace();
            return MessageResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(false)
                                    .build()
                    )
                    .message("Invalid Id exception")
                    .build();
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('update')")
    public MessageResponse updateContent(@RequestBody ContentUpdateRequest request){
        contentService.updateContent(request.getId(), request.getContent());
        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Content updated")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('delete')")
    public MessageResponse deleteContent(@PathVariable Integer id){
        contentService.deleteContent(id);

        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Content deleted")
                .build();
    }


}
