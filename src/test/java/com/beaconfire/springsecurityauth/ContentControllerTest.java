package com.beaconfire.springsecurityauth;

import com.beaconfire.springsecurityauth.controller.ContentController;
import com.beaconfire.springsecurityauth.domain.ServiceStatus;
import com.beaconfire.springsecurityauth.domain.response.AllContentResponse;
import com.beaconfire.springsecurityauth.domain.response.MessageResponse;
import com.beaconfire.springsecurityauth.entity.Content;
import com.beaconfire.springsecurityauth.service.ContentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = ContentController.class)
@ComponentScan({"com.beaconfire.springsecurityauth"})
public class ContentControllerTest {
    @MockBean
    private ContentService contentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_getAllProduct() throws Exception {

        List<Content> contents = new ArrayList<>();
        contents.add(new Content(1, "content1"));
        contents.add(new Content(2, "content2"));
        contents.add(new Content(3, "content3"));
        Mockito.when(contentService.getAllContent()).thenReturn(contents);

        AllContentResponse expected = AllContentResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .contents(contents)
                .build();
        Mockito.when(contentService.getAllContent()).thenReturn(contents);

        Gson gson = new Gson();
        String expectedJson = gson.toJson(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/content/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @Test
    void test_getProductById() throws Exception {
        Content expected = new Content(1, "content1");
        Mockito.when(contentService.getContentById(1)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/content/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\n" +
                                "    \"serviceStatus\": {\n" +
                                "        \"success\": true\n" +
                                "    },\n" +
                                "    \"content\": {\n" +
                                "        \"id\": 1,\n" +
                                "        \"message\": \"content1\"\n" +
                                "    }\n" +
                                "}"));
    }

    @Test
    void test_createContent() throws Exception {
        Integer id = 4;
        String content = "content4";
        Content expected = new Content(id, content);
        //Mockito.when(contentService.createContent(id, content)).thenReturn();

        Gson gson = new Gson();
        String expectedJson = gson.toJson(expected);

        MessageResponse response = MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("New content created")
                .build();

        String responseJson = gson.toJson(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/content/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(responseJson));

    }

    @Test
    void test_updateContent() throws Exception {
        Integer id = 1;
        String content = "newContent";
        Content expected = new Content(id, content);
        //Mockito.when(contentService.createContent(id, content)).thenReturn();

        Gson gson = new Gson();
        String expectedJson = gson.toJson(expected);

        MessageResponse response = MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Content updated")
                .build();

        String responseJson = gson.toJson(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/content/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(responseJson));

    }

    @Test
    void test_deleteContent() throws Exception {
        Integer id = 1;

        MessageResponse response = MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Content deleted")
                .build();

        Gson gson = new Gson();
        String responseJson = gson.toJson(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/content/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk()) // status code 200
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(responseJson));

    }


}
