package com.beaconfire.springsecurityauth;

import com.beaconfire.springsecurityauth.dao.ContentDao;
import com.beaconfire.springsecurityauth.entity.Content;
import com.beaconfire.springsecurityauth.exception.ContentNotFoundException;
import com.beaconfire.springsecurityauth.exception.InvalidIdException;
import com.beaconfire.springsecurityauth.service.ContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {
    @Mock
    private ContentDao contentDao;

    @InjectMocks
    private ContentService contentService;

    @Test
    void test_getAllProducts_success() {
        List<Content> expected = new ArrayList<>();
        expected.add(new Content(1, "content1"));
        expected.add(new Content(2, "content2"));
        expected.add(new Content(3, "content3"));
        Mockito.when(contentDao.getAll()).thenReturn(expected);
        //contentService.getAllContent().stream().forEach(c -> System.out.println(c.getMessage()));
        assertEquals(expected, contentService.getAllContent());
    }

    @Test
    void test_getAllProducts_successWhenEmpty() {
        List<Content> expected = new ArrayList<>();
        Mockito.when(contentDao.getAll()).thenReturn(expected);
        assertEquals(expected, contentService.getAllContent());
    }

    @Test
    void test_getContentById_success() throws ContentNotFoundException {
        Content expected = new Content(
                1,
                "content1");
        Mockito.when(contentDao.findById(1)).thenReturn(expected);
        assertEquals(expected, contentService.getContentById(1));
    }
    @Test
    void test_getContentById_failed() {
        Mockito.when(contentDao.findById(-1)).thenReturn(null);
        assertThrows(ContentNotFoundException.class, () -> contentService.getContentById(-1));
    }

    @Test
    void test_createContent_success() throws InvalidIdException {
        Integer id = 4;
        String content = "content4";
        // check if the content id is valid
        Mockito.when(contentDao.isContentIdValid(id)).thenReturn(true);

        // add the content, check argument captor
        ArgumentCaptor<Integer> valueCapture = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(contentDao).addNewContent(valueCapture.capture(), any(String.class));
        contentService.createContent(id, content);
        assertEquals(id, valueCapture.getValue());

        // check number of times invoked
        Mockito.verify(contentDao, Mockito.times(1)).addNewContent(id, content);

    }

    @Test
    void test_createContent_failedOnInvalidId() {
        Integer id = 1;
        String content = "content1";

        Mockito.when(contentDao.isContentIdValid(id)).thenReturn(false);
        assertThrows(InvalidIdException.class, () -> contentService.createContent(id, content));
    }

    @Test
    void test_updateContent_success() {
        Integer id = 1;
        String content = "newContent";

        // add the content, check argument captor
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(contentDao).updateContent(any(Integer.class), valueCapture.capture());
        contentService.updateContent(id, content);
        assertEquals(content, valueCapture.getValue());

        // check number of times invoked
        Mockito.verify(contentDao, Mockito.times(1)).updateContent(id, content);

    }

    @Test
    void test_deleteContent_success() {
        Integer id = 1;

        // delete the content, check argument captor
        ArgumentCaptor<Integer> valueCapture = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(contentDao).delete(valueCapture.capture());
        contentService.deleteContent(id);
        assertEquals(id, valueCapture.getValue());

        // check number of times invoked
        Mockito.verify(contentDao, Mockito.times(1)).delete(id);

    }


}
