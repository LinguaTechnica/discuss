# Discuss Forum

Discuss is a web app that allows users to discuss any topic they want. Users can create an account, start new discussions and join current ones.

## Instructions

Use your knowledge of object relationships to complete the tasks.

1. Create a unidirectional relationship between `User` and `Topic`.
2. Create a bidirectional relationship between `Topic` and `Reply`

### Assessment Prompts

These are inquiries that can be made at any point during the assessment. Use this as a guide for checking the learners understanding of features and concepts. Try to ask them at opportune moments as you're working together.

* How can we add exception handling to the controller?
* How could we add the database to this test?
* What is a unidirectional relationship? bidirectional?
* How can we verify the relationship between `User` and `Topic`, since topics require an author to be created? Unit or integration tests?

## Specs

| URI                      | HTTP Method | Response    | Description                       |
|--------------------------|-------------|-------------|-----------------------------------|
| /api/v1/topics           | GET         | 200 OK      | Returns a list of all topics.     |
| /api/v1/topics/{topicId} | GET         | 200 OK      | Returns a topic with its replies. |
| /api/v1/topics           | POST        | 201 CREATED | Creates a new topic.              |
| /api/v1/topics/{topicId} | POST        | 201 CREATED | Creates a new reply for a topic.  |

### `GET /api/v1/topics`
```json
[
    {
        "id": 1,
        "title": "Cooking with olive oil healthy?",
        "body": "What does everyone think? I hear different things about olive oil so its hard to decide whether I should use it or not."

    },
    {
        "id": 2,
        "title": "Top 10 best vacation spots",
        "body": "To get this discussion started, I think the top 10 best spots are Tahitti, Peru, Toronto, Finland ..."
    }
]
```

### `GET /api/v1/topics/{topicId}`
```json
{
    "id": 1,
    "title": "Cooking with olive oil healthy?",
    "author": 31,
    "body": "What does everyone think? I hear different things about olive oil so its hard to decide whether I should use it or not.",
    "replies": [
      {
        "id": 21,
        "author": 201,
        "body": "I think cooking with olive oil is very health because ..."
      },
      {
        "id": 22,
        "author": 202,
        "body": "Olive oil makes me feel fat so I dont use it at all ..."
      } 
    ]   
}
```

### `POST /api/v1/topics/{topicId}`
```json
{
    "body": "I like to use olive oil as a moisturizer, but I also cook ..." 
}
```

### `POST /api/v1/topics`
```json
{
    "title": "Share your most embarrassing moment at work!",
    "body": "This one time, I was at the water cooler when all of a sudden..."
}
```

## Stories

As a user, I want to participate in discussions so that I can share my ideas and experiences.

```gherkin
Given an existing discussion
When I submit a new reply to the discussion
Then I can see my reply among the others on the discussion.

Given I create a new discussion
When I submit it with a title and description
Then I can see my discussion in the list of discussions.

Given I've created 3 discussions in the past
When I view my profile
Then I can see all 3 discussions I've created
```

