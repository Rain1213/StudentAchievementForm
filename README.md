# **STUDENT ACHIEVEMENT FORM**

## **OBJECTIVE**
The main idea behind the Student Achievement Portal is to build a functioning android application that would manage details like Event Participation, Internship, or any Student Achievement in general.
Whenever, you as a student participate in an event during the working college hours and you don’t wish that your recent achievements hamper your attendance criteria, forbidding you from taking your internal exams, you may make use of this application.

## **REASONING BEHIND THE PROJECT**
Until now, the managing of attendance was mostly manual. Students had to approach faculty whenever they were unable to attend classes due to some Technical/Non-Technical event. Proof of Events were sent via mail to the faculties. In short, there was a lack of consistency.
Prof. Kruti Dhyani was the one to manage the attendance and she wished to create an application portal where students would upload their information about their events in general along with the proof. Then all she has to do was to accept or reject their form as to only allow attendance leniency
to those whose forms were accepted.

## **PROJECT SUMMARY**
When you first open the application, you are presented with two options, admin log in and student log in. When you try to login as a student, you will be only allowed if you have a charusat student email id to begin with. After successful sign in you are presented with 2 options; either to view your past submitted forms or to submit a new one. If you submit a new form, your form will be sent to the admin for verification. You can now view your newly submitted form in the ‘Submitted Form’ window. In case you are a teacher and also an admin, you will be able to sign in as an admin. When a successful sign in, you will be presented with 2 options; either to view all the pending requests or to view simply the approved ones. You can approve or reject a requested form by simply swiping right or left on the particular form from the ‘Requested Form’ window.

## **APPLICATIONS OF PROJECT**
* As a student you would use the application to report any event you have participated in or simply to check on the status of your previous forms that have been submitted by you.
* As an admin you would use the application to check the details of the forms that have been submitted by your students. You can either swipe left and reject the submission or swipe right and accept it. Whatever you choose to do, the student will be notified about it. You will be able to view the accepted forms in a separate window.


## **SOFTWARE DESIGN**

### **MAIN PAGE**
The main page would consist of two buttons “Admin” and “Student” respectively. Clicking any would result the user in getting a google sign in prompt.

<img align="center" alt="mainPage" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/1.JPG?raw=true" />
<img align="center" alt="googleSignIn" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/2.JPG?raw=true" />
<br/><br/>

### **STUDENT MAIN PAGE**
When the user logs in as a student, he/she would be only be able to proceed if they have a Charusat email id. This prevents any one from outside from accessing the application and also prevents any outside email id from toying with the application.

As an added benefit the user can also see their name and their email id to be 100% certain they are using a correct email id.

There are two buttons, namely ‘Achievement Form’ and 'Submitted Form’. Achievement Form is where the user is expected to click on to access the form to be filled and Submitted Form is where the user can check on their past submitted forms.

A Logout is located at top-right corner to safely sign out when the user wishes to.

<img align="center" alt="StudentMainPage" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/3.JPG?raw=true" />

<br/><br/>

### **ACHIEVEMENT FORM**
Achievement Form contains all the details the user has to be fill. The user has to provide the details of the event he/she has taken part in. While its not compulsory, it is certainly preferable that the user provides a link to their google drive folder containing a proof of their participation, it could be a photograph or a certification.

<img align="center" alt="achievementForm" width="98%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/4_bg.JPG?raw=true" />
<br/><br/>

### **SUBMITTED FORM**
It contains a recycler view that provides the user with their past submissions. Each one may vary in colour for each colour depicts a meaning. To be precise, if your submission is coloured red it means, the faculty overseeing the achievement has rejected your form, while green means your form has been accepted and you are eligible for the  attendance relaxation. If the colour is a default blue, your form has still not been verified by the faculty.

As an added feature, you can open any submission to review all the details you have once entered.

<img align="center" alt="submissionPage" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/5.JPG?raw=true" />
<img align="center" alt="submissionPage" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/6.JPG?raw=true" />
<br/><br/>

### **ADMIN MAIN PAGE**
Admin for this particular application would be the teaching faculty who are maintaining the attendance of the students and giving a leniency to the deserving lot. As an admin when you log in, you will be presented with two options, either to click on Requested Form or Approved Form.
If you want to log out, you could do that clicking on the button located at top-right corner.

<img align="center" alt="adminMainPage" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/7.JPG?raw=true" />
<br/><br/>

### **REQUESTED FORMS**
When a student submits a form, it’s up to you as a faculty (admin) to make sure that you view all the details and see to it if they are authentic and if the student deserves an attendance leniency. All the forms would be available to the admin when they access the Requested Form. You could click on each item to access the entire information. Later you can swipe left or right on the item to either reject the particular form or accept the form respectively. A dialogue box would pop every time an item is swiped to prevent any mistakes. All Accepted forms will go to the ‘Approved
Form’.

Whether you accept or reject a form, the student would see the status by accessing their ‘submitted
form’.

<img align="center" alt="requestForm" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/8.JPG?raw=true" />
<img align="center" alt="requestReview" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/9.JPG?raw=true" />
<img align="center" alt="formArchieve" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/10.JPG?raw=true" />
<img align="center" alt="formDelete" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/11.JPG?raw=true" />
<br/><br/>

### **APPROVED FORMS**
If you swipe right an item and accept it, the item will be transferred to the recycler view contains all approved forms. This data is stored only to be accessed at the time of actually providing the attendance leniency. A feature of live search is provided at the top. The admin can easily search for a particular id number, student semester or the type of event/achievement the student has participated in. This makes sorting much easier.

<img align="center" alt="approvedForm" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/11.JPG?raw=true" />
<img align="center" alt="liveSearch" width="30%" src="https://github.com/Rain1213/StudentAchievementForm/blob/master/screenshots/11.JPG?raw=true" />
<br/><br/>
