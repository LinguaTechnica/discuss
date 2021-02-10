# Discuss Forum

Discuss is a web app that allows users to discuss any topic they want. Users can create an account, start new discussions and join current ones.

## Stories

The task is to write tests to add new features to the Discuss service. 

As a user, I want to participate in discussions so that I can share my ideas.

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