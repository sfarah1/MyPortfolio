# Team 12-Mental Health and Wellbeing

Run

mysql -u root -p

in cmd. Then use

CREATE DATABASE mental_health CHARACTER SET utf8 COLLATE utf8_unicode_ci;

After which database operations will be available

The properties required for MySQL database to be available in the project. This will work in University laptops with MySQL installed. In other systems make sure MySQL is installed and change the username and password accordingly

Pleas create these new tables in your database for resource and gallery part:

create table g_photo
(
id        int auto_increment
primary key,
photoPath varchar(255)                         null,
comment   text                                 null,
upTime    datetime default current_timestamp() null,
owner     varchar(50)                          null,
flag      int      default 0                   not null,
liked     int      default 0                   not null
);

create table g_video
(
id               int auto_increment
primary key,
galleryVideoPath varchar(255)                         null,
comment          text                                 null,
upTime           datetime default current_timestamp() null,
owner            varchar(50)                          null,
flag             int      default 0                   not null,
liked            int      default 0                   not null
);

create table g_pmessage
(
id        int auto_increment
primary key,
message   text null,
g_photoID int  null
);

create table g_vmessage
(
id        int auto_increment
primary key,
message   text null,
g_videoID int  null
);

create table videos
(
id         int auto_increment
primary key,
videoPath  varchar(255)  null,
coverPath  varchar(255)  null,
videoTitle varchar(255)  null,
flag       int default 0 not null
);


For RegisterUser

CREATE TABLE IF NOT EXISTS `RegisterUser` (
`userID`		INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
`firstName`	TEXT NOT NULL,
`lastname`	TEXT NOT NULL,
`username`	TEXT NOT NULL,
`password`  TEXT NOT NULL,
`age`  integer NOT NULL,
`email`  TEXT NOT NULL
);

For events

CREATE TABLE IF NOT EXISTS `Events` (
`event_id`		INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
`event_name`	TEXT NOT NULL,
`event_content`	TEXT NOT NULL,
`startDate`	date NOT NULL,
`endDate`  datetime NOT NULL,
`imgpath` text not null
);

For Participation

CREATE TABLE IF NOT EXISTS `Participation` (
`participation_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user_name` text NOT NULL,
`event_id` integer not null,
`participation_date` date not null
);

Thyme leaf fragments:
for head put inside head tag: <th:block th:include="fragments/common :: head"></th:block> 
for navbar: <header th:replace="fragments/common :: header"></header>
for footer: <footer th:replace="fragments/common :: footer"></footer>
for script (jquery, js, popper) dependencies: <script th:replace="fragments/common :: script"></script>

Admin pages login:
 user name: admin; password: password (you can change these in the WebSecurityConfig.java)