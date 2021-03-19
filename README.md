# Choonz-Starter

Starter code for SDET final project specification - music website "Choonz"

## Concept

Final project for the 20SDET2 cohort. Using an OOP-based premade code, refactor it into a functional webapp to meet a hypothetical clients requirements within two weeks along with documentation and testing. The webapp is a music database which stores tracks which are then organised by albums, genres, and artists. Users could also make playlists of their tracks but are required to be logged in to access any create, update, or delete functionality.

This should be scaleable from 3 to 5+ entities:

- **MUST HAVE** - Track, Artist, Album, Genre, Playlist, Users
- **SHOULD HAVE** - Ability to search by album/artist/track, Load/Soak/Stess/Spike testing, Static Analysis, 80% Code Coverage
- **COULD HAVE** - Links to Kanban board to repo, Ultilisation of versioning
- **WON'T HAVE** - External Links, Audio Files

## Specifications

Users should be able to make an account and log in. On the home page users can CRUd albums, artists, tracks, and genres as well as see their playlists. Each album etc would have their own page with links to related entries (e.g. an album would have links to its tracks, artist, and the genre it belongs to). Pages would also have relevant text such as a description or lyrics.

## ERD

![](https://cdn.discordapp.com/attachments/761214563660070922/822268595219464192/CGP_ERD_FINAL.png)

## UML

![](https://cdn.discordapp.com/attachments/761214563660070922/822268579692281937/CGP_UML_FINAL.png)

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive) - **Technical Support**
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- [**Cameron Guthrie**](https://github.com/CGuthrieQA) - **User Acceptance Testing, Planning documentation**
- [**Gie-Anne Fortuna**](https://github.com/giefortunaQA) -**Service Unit Testing, Integration Testing, Front End Dev**
- [**Emmy Kidd**](https://github.com/ekiddqa) - **Domain Unit Testing, Controller Unit Testing, Integration Testing, README**
- [**Sehun Babatunde**](https://github.com/SehunB-QA) - **Controller Unit Testing, Integration Testing, Static analysis**

## Acknowledgements

- https://stackoverflow.com/questions/52077711/spring-boot-test-execute-different-sql-scripts-in-tests-depending-on-the-active
- https://www.baeldung.com/oauth-api-testing-with-spring-mvc
- https://gomakethings.com/using-oauth-with-fetch-in-vanilla-js/
- https://www.telerik.com/blogs/what-is-json-how-to-handle-unexpected-token-error#:~:text=%E2%80%9CUnexpected%20token%20%3C%20in%20JSON%20at,it%20is%20parsed%20to%20JSON
- https://www.baeldung.com/spring-data-rest-relationships
- https://stackoverflow.com/questions/13027214/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association
- https://www.logicbig.com/tutorials/misc/jackson/json-managed-reference.html