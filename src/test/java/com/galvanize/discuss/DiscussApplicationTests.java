package com.galvanize.discuss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.discuss.topics.Topic;
import com.galvanize.discuss.topics.TopicRepository;
import com.galvanize.discuss.users.User;
import com.galvanize.discuss.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DiscussIntegrationTests {
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    void createTopic_success() throws Exception {
        Topic topic = new Topic("My Ideas", "All of my ideas!");
        User user = new User("babyYoda");
        userRepository.save(user);

        mvc.perform(post("/api/v1/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(topic)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author").value(user));
    }

}
