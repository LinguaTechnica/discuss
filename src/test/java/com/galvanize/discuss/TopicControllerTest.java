package com.galvanize.discuss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.discuss.topics.Topic;
import com.galvanize.discuss.topics.TopicController;
import com.galvanize.discuss.topics.TopicRepository;
import com.galvanize.discuss.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
@AutoConfigureWebMvc
public class TopicControllerTest {
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicRepository repository;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    void createTopic_success() throws Exception {
        Topic topic = new Topic("My Ideas", "All of my ideas!");
        topic.setId(100L);
        when(repository.save(any(Topic.class))).thenReturn(topic);

        mvc.perform(post("/api/v1/topics")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(topic)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100L));
    }
}
