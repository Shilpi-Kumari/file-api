# FILE VAULT

- Project 1: FILE VAULT<br>
- University Name: http://www.sjsu.edu/<br>
- Course: [Cloud Technologies](http://info.sjsu.edu/web-dbgen/catalog/courses/CMPE281.html)<br>
- Professor: [Sanjay Garje](https://www.linkedin.com/in/sanjaygarje/)<br>
- ISA: [Anushri Srinath Aithal](https://www.linkedin.com/in/anushri-aithal/)<br>
- Student: [Shilpi Kumari](www.linkedin.com/in/shilpi-kumari-58764a64)

## File Vault Introduction<br>
File Vault is a web application which is hosted on AWS cloud. This application provides services to store and manage files on cloud securely. An authorized user can upload, update, download and delete their files. It also has one admin role which has access to all the user’s data. Admin can monitor the content of uploaded files by different users and also can delete the files from the system if anything suspicious is there for the files.

This is a 3-tier web application which uses AWS cloud services to provide a highly scalable, available and cost-effective solution. It uses AWS S3 to store the data. It is also using AWS autoscaling which will help in resource elasticity during peak and low user load on File Vault application. AWS Lambda and SNS is used to inform admin if users are uploading files with more than 8MB size. So that admin can take appropriate action on increasing the limit on file size upload if average is going more than 8MB. Cloud Watch is logging this activity and admin can check the details in the logs.

### Features List:
- Login Page	
- Social Sign In with Facebook,Google and LinkedIn
- Upload files
-	Delete files
-	Download files
-	Update the files
-	List the files
- Admin Role 

 ### Admin Flow:

![adminflow](https://user-images.githubusercontent.com/42687329/47689578-ae97b780-dba7-11e8-92f1-6e7b26dacf3b.png)

### User Flow:

![userflow](https://user-images.githubusercontent.com/42687329/47689583-b0fa1180-dba7-11e8-8079-b8d55e5cbe34.png)

### File Vault Architecture Diagram:

![filevaultarchitecturediagram](https://user-images.githubusercontent.com/42687329/47689363-e0f4e500-dba6-11e8-945a-61190c5e37ad.png)

### AWS Services Used:

-	EC2: Created an EC2 instance to use it as a webserver for the application. Deployed all the projects artifacts and use that for creating an AMI for Auto scale group.<br>
-	ELB: Classic load balancer is used to distribute the load on all the EC2 instances. It points to auto scale group to handle the load. One alarm is set on load balancer if minimum number of healthy states is less than 1.<br>
-	Lambda: A python program is written in lambda function on S3 upload event. Logs can be seen on CloudWatch.<br>
-	SNS: Configured in lambda function to trigger mail to admin whenever there will be file upload of more than 8MB size. So that admin can take appropriate action on increasing the limit on file size upload if average is going more than 8MB.Created another SNS topic to trigger an email if healthy host is less than 1.<br>
-	AutoScale Group: This configuration is to achieve highly available and highly scalable solution. I have set the desired > instance 1 and maximum 2 for my application. This Auto Scale group help the application in adjusting the EC2 instances to meet the changing condition of traffic. It will be also maintaining the desired capacity by checking health of EC2 instance periodically.<br>
- Auto scaling policy is set on request queue in ELB. Whenever the request queue is above the threshold, the auto scaling group will launch a new EC2 instance using the AMI defined in launch configuration and start the application using the config properties.<br>
-	IAM users with appropriate roles with S3, CloudWatch, CloudFront etc. are in place to ensure a secure cloud experience.<br>
- R53: It’s a domain name server which resolves the ip address of the application www.shilpisingh.online . Created a domain name on GoDaddy and created a hosted zone on R53 to redirect to GoDaddy DNS.<br>
-	CloudFront: File download will be done via CloudFront, minimum TTL is set to 60 seconds.<br>
-	RDS: MySQL instance is created to maintain user profile, user data and user role.<br>
- S3: It is used to upload and management of files.
- S3 Transfer Acceleration: S3 bucket used for this application is enabled with transfer acceleration features to ensure faster and secure file transfer to S3.<br>
- Infrequent Access (IA): Life cycle policies are updated on S3 so that files will be moved to S3 IA after 75 days of object creation.<br>
 -	Amazon Glacier: Files will be moved to Amazon Glacier after 365 days of object creation as content will be not used frequently. It will be archived for one additional year for legal/compliance reasons before it can be deleted from the system.<br>
-	CloudWatch: CloudWatch is used in this application to see logs for S3 upload on lambda function invocation. It will give the file name and file size of the uploaded files and SNS will be triggered if uploaded file size is more than 8 MB.<br>

### Sample Screenshots:

- Login Page<br>
![loginpage](https://user-images.githubusercontent.com/42687329/47689757-7fce1100-dba8-11e8-9ee9-c04435ac7394.png)

- Sign Up Page<br>
![signup](https://user-images.githubusercontent.com/42687329/47689702-367dc180-dba8-11e8-9f23-b89847d7e775.png)

- List Files<br>
![homepage](https://user-images.githubusercontent.com/42687329/47689785-a5f3b100-dba8-11e8-828e-2b14de87f088.png)

- Upload File:<br>
<img width="1440" alt="screen shot 2018-10-30 at 2 28 51 pm" src="https://user-images.githubusercontent.com/42687329/47751877-29b4a880-dc50-11e8-9959-a1a3c0ae651f.png">

- Admin View<br>
![admin](https://user-images.githubusercontent.com/42687329/47751789-fa05a080-dc4f-11e8-9049-f0cf1294dc93.png)


