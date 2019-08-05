# Test Plan_v2

## Version description

For Deliverable 3: Add manual testing in the overall startegy. And update results for patial test cases.


**Author**: \<6300Summer19Team08\>

## 1 Testing Strategy

### 1.1 Overall strategy

Manual Testing will be use at begging to search for  syntax errors.

Black-box testing will be used at unit and system testing, to look for logic defects and generating test cases. 

We will figure out the Equivalence Partitions for the application first, then carry out the Boundary Value Analysis. Then we will do some Error Guessing, and list all these condition in a text file. Eventually, we will use TSLgenerator to generate the Test Cases. 

All the team members are involved in the discussion and the testing. 

### 1.2 Test Selection

Category-partition method will be used to select test cases for black-box testing.
### 1.3 Adequacy Criterion

- On the system level, there exists system tests for each of the use cases of the application. 
- On the unit level, for each function in the code, there is a unit test to test that function as an independent self contained piece of code with no dependencies. And with code coverage above 90%. 

### 1.4 Bug Tracking

For bug and enhancement tracking, we will use GitHub's "Issues" feature.

### 1.5 Technology

Expresso will be used for system testing and JUnit for unit testing.

## 2 Test Cases

Test cases created using Categories Partition method, and automated test cases generated using TSL. [Test Specification](./ForTSL.txt)

Partial test cases are tested by manually.


| purpose | steps | expected result | actual result |  pass/fail | Additional information |
|--|--|--|--|--|--|
| Test that application opens properly | click application icon | opens application UI | Yes | pass | |
| Bet amount not a number | After choosing to solve a new game the player is asked to selected an amount to bet.  | Error | only accept numbers | pass |  |
| Bet amount less than 1 | After choosing to solve a new game the player is asked to selected an amount to bet.  | Error | put "0", warning pop out |  | put "05", no warnings |
| Random cryptogram not generate | After choosing to solve a new game a random cryptogram should generated. | Error |  | pass |  |
| Player unable to modify game | When playing the game the player is unable to modify the game | Error  |  | pass |  |
| Player unable to submit game | When playing the game the player is unable to submit the game | Error  |  | pass |  |
| Total number of attempts in a game goes below 0 | When playing the game the player's number of attempts and the number of attempts goes below zero | Error  | player loses the game when attempts reaching 0 | pass |  |
| Total number of attempts in a game is above 5 | When playing the game the player's number of attempts is more than 5 | Error | the number of attempts is less/equal than 5 | pass |  |
| Hint does not shows when two attempts left   | While playing the game if the number of attempts left is two and hint is not displayed to the player | Error | Hint shows when 2 attempts left | pass |  |
| Two cryptograms with the same title | When creating a cryptogram,if two cryptogram have the same title then and error should be thrown  | Error | "Title is already in use by another cryptogram , try another" | pass |  |
| Letter encoded to itself | When creating a cryptogram,if a letter is encoded to itself then error should be thrown  | Error | Warning:"A letter is encoded to itself" | pass |  |
|Player could not save the cryptogram after entering data correctly | When creating a cryptogram,if all fields are filled correctly and the cryptogram is unable to save, then throw error  | Error | Player could save the game | pass |  |
|player unable to view confirmation| After creating a cryptogram,if all fields are filled correctly and player is unable to view confirmation, then throw error  | Error | Player could view confirmation and with game name and points | pass |  |
| Username is empty | When creating a new player if username is empty when saving the player throw error | Error | Warning: "you cannot have an empty username" | pass |  |
| Email is empty | When creating a new player if email is empty when saving the player throw error | Error | "Email must be a valid email address" | pass |  |
| Email contain @ | When creating a new player if email should contain @ sign when saving the player | True |  | pass |  |
| Email does not contain @ | When creating a new player if email does not contain @ sign when saving the player | Error | "Email must be a valid email address" | pass |  |
| Email length less than or equal 4 characters | When creating a new player if email contains less than more than 4 characters when saving the player | Error | "Email must be a valid email address" | pass |  |
| Administrator disables game and penalized creator b/w 0-10 | After logging in an admin may view the list of cryptogram and disable unfair cryptograms between 0-10 points | True |  |  |  |
| Player gets 20 points when first created | when creating a player the player should get 20 points by default. If not throw error | True |  | pass |  |
| Administrator can't set penalty less than 0 | When an administrator is disabling a cryptogram, it's creator should not have negative points subtracted from their total points | Error |  |  |  |
| Administrator can't set penalty more than 10 | When an administrator is disabling a cryptogram, it's creator should not have greater than 10 points subtracted from their total points | Error |  |  |  |
| Administrator able to disable game  | After logging in as an administrator, and admin is able to disable a cryptogram and penalize creator b/w 0 - 10 points| True |  |  |  |
| Player can login as new Player and enter username and email | After opening the application a user can create a new player by entering username and email | True |  | pass |  |
| Existing Player able to login and create cryptogram | After logging a player is able to create a cryptogram by correctly entering a unique title, and a non matching encoding and saves it | True |  | pass |  |
| When player solves game with attempts > 2 point will change | After an existing player logs in he can solve a random game, if he solves it with more than 2 attempts his points will increase | True |  | pass |  |
| When player solves game with attempts <= 2 point will  not change | After an existing player logs in he can solve a random game, if he solves it with with less than 2 attempts his points will not change | True | bet points still be added | fail |  |
| When player does not solves a game with attempts > 0, points  not change | After an existing player logs in he can attempts to solve a random game, if he does not solves it he will have 5 attempts before his points are deducted | True |  | pass |  |
