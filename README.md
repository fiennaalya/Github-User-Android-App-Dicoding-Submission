# Dicoding First Submission : Creating a Github User Android Application
**GitHub Search App** is the first assignment of dicoding.com [**Belajar Fundamental Aplikasi Android**](https://www.dicoding.com/academies/14) from the Android developer learning path. This application implements the WebAPI Networking using LoopJ and Parsing JSON. The application displays the list of Github users and a search bar on the top of the application. Once we press the user account, a user page will show the information of username, name, profile picture, company, country, the total, and the list of the following and followers.

<div align="center">

<h3>Project Overview</h3>
  
| Learning Path           | 
| ------------------------| 
| [Android Developer](https://www.dicoding.com/learningpaths/7)|
<p>Technologies :</p>
<p align="center">
<img src="https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" alt="Android Studio"/>
<img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
</p>
</div>

## Implementations and Improvements

### 📌 Splash Screen
Before the home page appear, the application features a Splash Screen that shows for a duration of 3 seconds.

### 📌 Home Page 
The home page presenting a list of GitHub users. This list is fetched from the GitHub REST API using LoopJ and parsed as JSON data. Each GitHub user in the list is represented by their image, name, and username. Users have the capability to search for any GitHub user's username they desire. Once a username is entered, the corresponding user GitHub’s data will be displayed on the screen. Additionally, users can access more detailed information about a specific user by selecting them from the list.

### 📌 Detail User Page 
Detail user page provides users with comprehensive information about a specific GitHub user, such as the number of followers, number of people they are following, company affiliation, and location. Furthermore, the Detail Page also displays the list of users the selected user is following and their followers, utilizing a RecyclerView to present this information and fetching it from the GitHub API.

## Results
#### ⭐⭐⭐ 3/5 stars achieved!
