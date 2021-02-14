package com.galvanize.discuss.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/topics")
public class TopicController {
    private final TopicRepository repository;

    @Autowired
    public TopicController(TopicRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topic createNewTopic(@RequestBody Topic topic) {
        return repository.save(topic);
    }
}
