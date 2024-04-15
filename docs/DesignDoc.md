---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics but do so only **after** all team members agree that the requirements for that section and current Sprint have been met. **Do not** delete future Sprint expectations._

## Team Information
* Team name: Albatross
* Team members
  * Joseph Pilon
  * Matthew Peck
  * Andy Lin
  * Naif Alanazi

## Executive Summary

This project is considered to be a website that allows users to sign in and donate needs.
It allows for a manager to sign in as well in order to update the cupboard of needs that the
customers will be able to see.

### Purpose

To create a Web-Based Ufund system, where users are able to select from a Cupboard of "Needs" to provide to a Specific Charity. Users are able to add/remove "Needs" from their unique Funding Basket, and choose to checkout their basket, locking in their "Purchase".

### Glossary and Acronyms
> _**[Sprint 2 & 4]** Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| SPA  | Single Page|
| MVP  | Minimum Viable Product|


## Requirements

This section describes the features of the application.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

### Definition of MVP

Charity donation website, where users are able to login and view their unique Funding Basket and the currently needed "Needs" from the Cupboard. Users are able to checkout the Needs in their basket and thus removes those Needs from the Cupboard.

### MVP Features
>  _**[Sprint 4]** Provide a list of top-level Epics and/or Stories of the MVP._

"As a Helper, I want to store the needs that I want to contribute to in one place so that I can keep track of what needs I plan to contribute to."

"As a User, I want to be able to "login" as an Admin, so that I can manage the website."

"As a User, I want to be able to "login" as a Helper, so that I can donate to the charity"

### Enhancements
> _**[Sprint 4]** Describe what enhancements you have implemented for the project._

"As a Helper, I want to be able to contact Managers so that I can receive help on any issues I have."

Our enhancements involve a Request System, which allows users to make requests to the Admins about certain things they may want, and Admins are able to view and resolve those requests made by users.


## Application Domain

This section describes the application domain.

![Domain Model](DomainModel.png)


There are 2 types of Users: Admin and Helper. 

Both types of users are able to view Needs and the "Dashboard", while the Admin is able to edit/modify the Needs in the Inventory/Cupboard.

Both types of users has access to the Request Service, where Helpers are able to send out requests to the Admins and the Admins are able to view all requests and mark them as fulfilled.

Each helper has a Funding Basket, where they can Add/Remove Needs (from the Cupboard). From the Funding Basket, a Helper can "Checkout", which empties the Funding Basket and decreases the quantity of those needs in the Cupboard.

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture. 
**NOTE**: detailed diagrams are required in later sections of this document.
> _**[Sprint 1]** (Augment this diagram with your **own** rendition and representations of sample system classes, placing them into the appropriate M/V/VM (orange rectangle) tier section. Focus on what is currently required to support **Sprint 1 - Demo requirements**. Make sure to describe your design choices in the corresponding _**Tier Section**_ and also in the _**OO Design Principles**_ section below.)_

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts with the web application.

Users start at the Login Page, where they are presented two textboxes to take an entered username + password, as well as a Login button. Once Login is pressed, depending on if the user is an Admin or regular User, they are presented with either the Admin page or the Cupboard page.

 From the Admin page, the user can add, delete, or update needs to/from the Cupboard. From the Cupboard page, a regular user is able to view details of each Need and have the ability to click into a specific Need's details, and there they are able to add the Need to their funding basket. An Admin is also able to edit/delete needs from the Cupboard page.

 As a regular User, they are able to view their funding basket by clicking the Funding Basket button from the navbar on top of the page, where they are moved to the Funding Basket page. From the Funding Basket page the User is able to checkout the needs in their basket, adding the total amount of money spent to their total donations, which is also shown on the page.

 As either a regular User or Admin, the Requests button in the navbar will take the user to their respective Requests page, where if the user is an Admin, they are able to see incoming requests made by regular users, and if the user is a regular user, they able to create requests for Admins to view.




### View Tier
> _**[Sprint 4]** Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _**[Sprint 4]** You must  provide at least **2 sequence diagrams** as is relevant to a particular aspects 
> of the design that you are describing.  (**For example**, in a shopping experience application you might create a 
> sequence diagram of a customer searching for an item and adding to their cart.)
> As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side 
> to help illustrate the end-to-end flow._

> _**[Sprint 4]** To adequately show your system, you will need to present the **class diagrams** where relevant in your design. Some additional tips:_
 >* _Class diagrams only apply to the **ViewModel** and **Model** Tier_
>* _A single class diagram of the entire system will not be effective. You may start with one, but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below._
 >* _Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important._
 >* _Include other details such as attributes and method signatures that you think are needed to support the level of detail in your discussion._

![Add to Funding Basket](AddToFBSeqDiagram.drawio.png)
![Remove from Funding Basket](RemFBSeqDiagram.drawio.png)


### ViewModel Tier
    Need.java is used to keep the atributes of the needs that will be shown in the cupboard.
    CupboardController.java is used to read in the commands and calls the commands to make adjusments to the cupboard or specific instructions from the user.
    CupboardDAO.java defines different functions that the cupboard is able to use and perform while manipulating different needs.
    CupboardFileDAO.java is used to define each of the functions that cupboardDAO uses and actually writes the functions actions that they will be performing.

> _**[Sprint 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your ViewModel Tier class diagram 1, etc.](model-placeholder.png)

### Model Tier
    Need.java is used to keep the atributes of the needs that will be shown in the cupboard.
    CupboardController.java is used to read in the commands and calls the commands to make adjusments to the cupboard or specific instructions from the user.
    CupboardDAO.java defines different functions that the cupboard is able to use and perform while manipulating different needs.
    CupboardFileDAO.java is used to define each of the functions that cupboardDAO uses and actually writes the functions actions that they will be performing.
    
> _**[Sprint 2, 3 & 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your Model Tier class diagram 1, etc.](model-placeholder.png)

## OO Design Principles

    While getting started with this project and during the first sprint, we need to make sure that we are focusing on the idea of single responsibility in the hopes that it will disperse the 'weight' of the functionality across multiple classes and functions.

    Throughout the project we had to keep in mind the idea of encapsulation as we have to combine many methods and data to 
    single classes in order to manipulate data and hide the internal state of the object from the outside. This helps in maintaining the integrity of data and prevents unintended interference
`
> _**[Sprint 2, 3 & 4]** Will eventually address upto **4 key OO Principles** in your final design. Follow guidance in augmenting those completed in previous Sprints as indicated to you by instructor. Be sure to include any diagrams (or clearly refer to ones elsewhere in your Tier sections above) to support your claims._

> _**[Sprint 3 & 4]** OO Design Principles should span across **all tiers.**_

## Static Code Analysis/Future Design Improvements
> _**[Sprint 4]** With the results from the Static Code Analysis exercise, 
> **Identify 3-4** areas within your code that have been flagged by the Static Code 
> Analysis Tool (SonarQube) and provide your analysis and recommendations.  
> Include any relevant screenshot(s) with each area._

> _**[Sprint 4]** Discuss **future** refactoring and other design improvements your team would explore if the team had additional time._

    We would be able to delete a lot of code that is not used anymore. A decent amount of code got left behind when trying
    to meet deadlines for some sprints, as we went down a teammate, and this could be pruned through to get out unneeded code. 

![Static Code Analysis](Static-Code-Analysis.png)

Areas Flagged by SonarQube
- Reliability
- Maintainability
- Coverage

For Reliability and Code Coverage, apart of the reason why Reliability was flagged was because of old implementations of methods in UsersFileDAO, that have not been removed / edited due to lack of time. Tests for these methods were also not implemented as they currently give a compile-time error.

For Maintainability, we are given an A rating, however various issues are marked under Intentionality and Maintainability, for example more efficient formatting methods through the use of constants that are used repeatedly throughout the files.

For future refactoring and design improvements, we would make use of the Static Code Analysis Tool to find the weak points / inefficient parts of the application and makes changes for optimization, like better formatting practices. With additional time, we would also be able to revise redundant/useless code that may have been missed during other times of refactoring during a Sprint.



## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _**[Sprint 2 & 4]** Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

Sprint 4 - 

16 Passed Tests
11 Failed Tests -> (Mostly features that had to be cut to meet the Sprint 3 Deadline)
0 User Stories not

Initially there was an issue with persistency on the front-end where a User's basket would be cleared everytime they re-login, however this was resolved by the deadline of Sprint 3.


### Unit Testing and Code Coverage
> _**[Sprint 4]** Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets._

>_**[Sprint 2 & 4]** **Include images of your code coverage report.** If there are any anomalies, discuss
> those._

![Upper Tier](Code-Coverage.png)
![Model Tier](Code-Coverage-Model.png)
![Persistence Tier](Code-Coverage-Persistence.png)
![Controller Tier](Code-Coverage-Controller.png)

Our Unit testing strategy involved testing both successful cases and unsuccessful cases of each method, for example NullExceptions and IOExceptions. As shown above, each tier has generally pretty high code coverage, where Unit Tests of the UserController class is the only class with less than 50% coverage.



## Ongoing Rationale
>_**[Sprint 1, 2, 3 & 4]** Throughout the project, provide a time stamp **(yyyy/mm/dd): Sprint # and description** of any _**mayor**_ team decisions or design milestones/changes and corresponding justification._

Sprint 3 2024/04/01 - Simplify Request functionality, by not having requests go back and forth from User to Admins, but just have Requests go to Admins and Admins can just mark them as resolved.
Includes not having the ability to modify a request after it's sent, or a search area for requests, since only Admins are able to view requests once its sent. (Saves time of implementation by deadline of Sprint 3)

Sprint 3 2024/04/05 - In order to achieve the MVP, doing only one enhancement instead of two to save time (Requests Enhancement > Statistics Enhancement)
