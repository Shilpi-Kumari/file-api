FILE VAULT

Project 1: FILE VAULT
University Name: http://www.sjsu.edu/
Course: Cloud Technologies
Professor: Sanjay Garje
ISA: Anushri Srinath Aithal
Student: Shilpi Kumari

File Vault is a web application which is hosted on AWS cloud. This application provides services to store and manage files on cloud securely. An authorized user can upload, update, download and delete their files. It also has one admin role which has access to all the user’s data. Admin can monitor the content of uploaded files by different users and also can delete the files from the system if anything suspicious is there for the files.

This is a 3-tier web application which uses AWS cloud services to provide a highly scalable, available and cost-effective solution. It uses AWS S3 to store the data. It is also using AWS autoscaling which will help in resource elasticity during peak and low user load on File Vault application. AWS Lambda and SNS is used to inform admin if users are uploading files with more than 8MB size. So that admin can take appropriate action on increasing the limit on file size upload if average is going more than 8MB. Cloud Watch is logging this activity and admin can check the details in the logs.

Features List:
1.	Upload files to S3 
2.	Delete files from S3
3.	Download files from S3 using Cloud Front
4.	Update the already uploaded file
5.	List the files
6.  Admin View

Admin Flow:

![adminflow](https://user-images.githubusercontent.com/42687329/47689578-ae97b780-dba7-11e8-92f1-6e7b26dacf3b.png)

User Flow:
![userflow](https://user-images.githubusercontent.com/42687329/47689583-b0fa1180-dba7-11e8-8079-b8d55e5cbe34.png)
File Vault Architecture Diagram:

![filevaultarchitecturediagram](https://user-images.githubusercontent.com/42687329/47689363-e0f4e500-dba6-11e8-945a-61190c5e37ad.png)

AWS Services Used:

•	EC2: Created an EC2 instance to use it as a webserver for the application. Deployed all the projects artifacts and use that for creating an AMI for Auto scale group.
•	ELB: Classic load balancer is used to distribute the load on all the EC2 instances. It points to auto scale group to handle the load. One alarm is set on load balancer if minimum number of healthy states is less than 1.
•	Lambda: A python program is written in lambda function on S3 upload event. Logs can be seen on CloudWatch.
•	SNS: Configured in lambda function to trigger mail to admin whenever there will be file upload of more than 8MB size. So that admin can take appropriate action on increasing the limit on file size upload if average is going more than 8MB.Created another SNS topic to trigger an email if healthy host is less than 1.
•	AutoScale Group: This configuration is to achieve highly available and highly scalable solution. I have set the desired instance 1 and maximum 2 for my application. This Auto Scale group help the application in adjusting the EC2 instances to meet the changing condition of traffic. It will be also maintaining the desired capacity by checking health of EC2 instance periodically.

Auto scaling policy is set on request queue in ELB. Whenever the request queue is above the threshold, the auto scaling group will launch a new EC2 instance using the AMI defined in launch configuration and start the application using the config properties.
•	IAM users with appropriate roles with S3, CloudWatch, CloudFront etc. are in place to ensure a secure cloud experience.
•	R53: It’s a domain name server which resolves the ip address of the application www.shilpisingh.online . Created a domain name on GoDaddy and created a hosted zone on R53 to redirect to GoDaddy DNS.
•	CloudFront: File download will be done via CloudFront, minimum TTL is set to 60 seconds.
•	RDS: MySQL instance is created to maintain user profile, user data and user role.
•	S3: It is used to upload and management of files.
•	S3 Transfer Acceleration: S3 bucket used for this application is enabled with transfer acceleration features to ensure faster and secure file transfer to S3.
•	S3 Infrequent Access (IA): Life cycle policies are updated on S3 so that files will be moved to S3 IA after 75 days of object creation.
•	Amazon Glacier: Files will be moved to Amazon Glacier after 365 days of object creation as content will be not used frequently. It will be archived for one additional year for legal/compliance reasons before it can be deleted from the system.
•	CloudWatch: CloudWatch is used in this application to see logs for S3 upload on lambda function invocation. It will give the file name and file size of the uploaded files and SNS will be triggered if uploaded file size is more than 8 MB.
