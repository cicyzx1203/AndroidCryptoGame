# Test Plan_v3

## Version description

For Deliverable 3: Add manual testing in the overall startegy. And update results for patial test cases.

For Deliverable 4: Add more manual test cases. Finish all manual testing.


**Author**: \<6300Summer19Team08\>

## 1 Testing Strategy

### 1.1 Overall strategy

Manual Testing will be use at begging to search for syntax errors.

Black-box testing will be used to look for logic defects and generating test cases.

All the team members are involved in the discussion and the testing. 

### 1.2 Test Selection

Manual tests are selected based on the design requirements.  

### 1.3 Adequacy Criterion

- For manual testing, there exists at least one manual test case for each of the design requirements.

### 1.4 Bug Tracking

For bug and enhancement tracking, we will use GitHub's "Issues" feature.

### 1.5 Technology

- Tester will perform manual tests on behalf of the end users.

## 2 Test Cases

All test cases are tested by manually.


| purpose | steps | expected result | actual result |  pass/fail | Additional information |
|--|--|--|--|--|--|
| Test that application opens properly | Click application icon | Open application UI | Yes | pass | |
| Bet amount not a number | After choosing to solve a new game the player is asked to selected an amount to bet.  | Error | only accept numbers | pass |  |
| Bet amount less than 1 | After choosing to solve a new game the player is asked to selected an amount to bet.  | Error | put "0", warning pop out | pass | put "05", no warnings |
| Bet amount larger than 10 | After choosing to solve a new game the player is asked to select an amount to bet. | Error | Error message "Invalid bet range..." | pass | Tested when the player's total points is larger than 10 |
| Bet amount larger than total points | Bet an amount that larger than the player's total points after choosing to solve a new game | Error | Error message "Invalid bet range..." | pass | Tested when the total points is smaller than 10 |
| Random cryptogram not generate | After choosing to solve a new game a random cryptogram should generated. | Error |  | pass |  |
| Player unable to modify game | When playing the game the player is unable to modify the game | Error  |  | pass |  |
| Player unable to submit game | When playing the game the player is unable to submit the game | Error  |  | pass |  |
| Total number of attempts in a game goes below 0 | When playing the game the player's number of attempts and the number of attempts goes below zero | Error  | player loses the game when attempts reaching 0 | pass |  |
| Total number of attempts in a game is above 5 | When playing the game the player's number of attempts is more than 5 | Error | the number of attempts is less/equal than 5 | pass |  |
| Hint does not shows when two attempts left   | While playing the game if the number of attempts left is two and hint is not displayed to the player | Error | Hint shows when 2 attempts left | pass |  |
| The number of attempts will be incremented after a player finishing one cryptogram game | After a player finishes one game, check the number of attempts in player list | True | The number of attempts in player list is added by 1 | pass | Attempts will not be added by 1 if player leave the game unfinished with remaining attempts |
| The number of completed will be incremented after a cryptogram game | After a game is finished, the number of completed in the game statistic will be added by 1 | True | | pass | |
| The %Wins will be updated after a finished game | The %Wins will be udpated according to the win/lose of the finished game | True | | pass | |
| Two cryptograms with the same title | When creating a cryptogram,if two cryptogram have the same title then an error should be thrown  | Error | "Title is already in use by another cryptogram , try another" | pass |  |
| Letter encoded to itself | When creating a cryptogram,if a letter is encoded to itself then error should be thrown  | Error | Warning:"A letter is encoded to itself" | pass |  |
| Duplicate letters are not allowed in encoding letters | Try to input duplicate letters in encoding letters when creating a new cryptogram | Error| Error message "Each encoding character must be unique" | pass | |
| Encoding letters must has same length with replaced letters | Try to save a cryptogram with different length of encoding letters and replaced letters | Error | Error message "Encoding length must match replacement letters length" | pass | |
| New cryptogram cannot be save without hint provided | Try to save a cryptogram without hint | Error | Error message "Hint must not be empty" | pass | | 
| Player unable to view confirmation| After creating a cryptogram,if all fields are filled correctly and player is unable to view confirmation, then throw error  | Error | Player could view confirmation and with game name and points | pass |  |
| Username is empty | When creating a new player if username is empty when saving the player throw error | Error | Warning: "you cannot have an empty username" | pass |  |
| New player cannot register with a taken username | Enter a taken username when creating a new player | Error message "Your chosen username is already taken" | pass | |
| Case insensitive for username and email address | Try existing player login with corret letters but different combinations of case | True | Login successfully | pass | |
| Email is empty | When creating a new player if email is empty when saving the player throw error | Error | "Email must be a valid email address" | pass |  |
| Email contain @ | When creating a new player if email should contain @ sign when saving the player | True |  | pass |  |
| Email does not contain @ | When creating a new player if email does not contain @ sign when saving the player | Error | "Email must be a valid email address" | pass |  |
| Email length less than or equal 4 characters | When creating a new player if email contains less than more than 4 characters when saving the player | Error | "Email must be a valid email address" | pass |  |
| All existing players shown in the player list | Login as an existing player and choose "View Player List" | True | All existing players listed | pass | |
| Players ranked by points in descending order | Login as an existing player and choose "View Player List" | True | Players are ranked by points in descending order | pass | | 
| Administrator disables game and penalized creator b/w 0-10 | After logging in an admin may view the list of cryptogram and disable unfair cryptograms between 0-10 points | True |  | pass |  |
| Player gets 20 points when first created | when creating a player the player should get 20 points by default. If not throw error | True |  | pass |  |
| Administrator can't set penalty less than 0 | When an administrator is disabling a cryptogram, it's creator should not have negative points subtracted from their total points | Error |  | pass  |  |
| Administrator can't set penalty more than 10 | When an administrator is disabling a cryptogram, it's creator should not have greater than 10 points subtracted from their total points | Error |  | pass | Tested when the total points of the creator is larger than 10  |
| Administrator can't set penalty more than the total points of the creator | When an administrator disableing a cryptogram, set penalty larger than the creator's total points | Error | Error message "The creator only has X point(s) that can be penalized" | pass | |
| Administrator able to disable game  | After logging in as an administrator, and admin is able to disable a cryptogram and penalize creator b/w 0 - 10 points| True |  | pass |  |
| Creator's points will be deducted if the game is disabled | After loggin as an administrator and disable a game, the creator's total points will be deducted | True | pass | |
| Player can login as new Player and enter username and email | After opening the application a user can create a new player by entering username and email | True |  | pass |  |
| Existing Player able to login and create cryptogram | After logging a player is able to create a cryptogram by correctly entering a unique title, and a non matching encoding and saves it | True |  | pass |  |
| When player solves game with attempts > 2 point will change | After an existing player logs in he can solve a random game, if he solves it with more than 2 attempts his points will increase | True |  | pass |  |
| When player does not solves a game with attempts > 0, points  not change | After an existing player logs in he can attempts to solve a random game, if he does not solves it he will have 5 attempts before his points are deducted | True |  | pass |  |
