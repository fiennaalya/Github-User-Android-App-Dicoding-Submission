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
<p>Before the home page appear, the application features a Splash Screen that shows for a duration of 3 seconds.</p>
<div align="center">
  <img height="500" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/3d5be0ba-3043-40ca-91cc-4b74e3338a06">
</div>


### 📌 Home Page 
The home page presenting a list of GitHub users. This list is fetched from the GitHub REST API using LoopJ and parsed as JSON data. Each GitHub user in the list is represented by their image, name, and username. Users have the capability to search for any GitHub user's username they desire. Once a username is entered, the corresponding user GitHub’s data will be displayed on the screen. Additionally, users can access more detailed information about a specific user by selecting them from the list.
<div align="center">
  <img height="500" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/1d63ea62-1fc2-4608-a3cb-f9d0833e7b7a">
  <img height="500" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/82a65243-2d70-409b-936e-7356a4386ef9">
</div>

### 📌 Detail User Page 
Detail user page provides users with comprehensive information about a specific GitHub user, such as the number of followers, number of people they are following, company affiliation, and location. Furthermore, the Detail Page also displays the list of users the selected user is following and their followers, utilizing a RecyclerView to present this information and fetching it from the GitHub API.

#### Following
<div align="center">
  <img height="500" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/6ade7b6f-0945-4949-b1e0-4eac9e41d36d">
</div>  

#### Followers
<div align="center">
  <img height="500" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/b3977341-4377-4e04-87a3-12291cb8f34d">
</div>

## Results
#### ⭐⭐⭐ 3/5 stars achieved!
<img width="323" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/49dce4c1-13af-485f-bbaf-bd5d73768284">
<img width="275" alt="image" src="https://github.com/fiennaalya/Github-User-Android-App-Dicoding-Submission/assets/99575596/4096a125-0da0-48ea-8f61-e69f7c99ef2e">
