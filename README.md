# 🚀 AI Mail Sender App

An intelligent job application mail generator that uses AI to create personalized emails based on job role and company details.

---

## ✨ Features

- 🤖 AI-generated job application emails (via Groq LLM)
- 📄 Resume attachment support
- 📩 Automated email sending using Spring Boot Mail
- ⚡ Fallback email system if AI fails
- 🔐 Secure API key handling using environment variables
- 🌐 Deployed on AWS EC2

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 4
- REST APIs
- Groq API (LLM)
- Maven
- AWS EC2 (Deployment)

---

## 📂 Project Structure

mailSender/
├── controller/
├── service/
│   ├── AIService.java
│   ├── MailService.java
├── dto/
├── resources/
│   ├── application.properties
│   ├── static/index.html
│   ├── resume.pdf
└── main/

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
git clone https://github.com/your-username/AiMailSenderApp.git  
cd mailSender  

---

### 2️⃣ Set Environment Variable (IMPORTANT)

#### Mac/Linux:
export GROQ_API_KEY="your_api_key_here"

#### Windows:
set GROQ_API_KEY=your_api_key_here

---

### 3️⃣ Build the project
./mvnw clean package

---

### 4️⃣ Run the application
java -jar target/mailSender-0.0.1-SNAPSHOT.jar

---

### 5️⃣ Open in browser
http://<your-server-ip>:8080

---

## 📧 How It Works

1. User enters:
   - Company name
   - Job role  

2. AI generates a custom job application email  

3. Email is sent automatically with:
   - Generated content  
   - Resume attachment  

---

## 🔐 Security

- API keys are NOT stored in code  
- Uses environment variables (GROQ_API_KEY)  
- GitHub push protection followed  

---

## ⚠️ Fallback Mechanism

If AI fails:
- A default email template is sent  
- Ensures system reliability  

---

## 🚀 Deployment

- Hosted on AWS EC2  
- Accessible via public IP  
- Can be extended with:
  - Domain (Route53)  
  - HTTPS (Nginx + SSL)  

---

## 📌 Future Improvements

- Upload resume dynamically  
- Parse resume using AI  
- Multi-company bulk mail sender  
- UI improvements  
- Email tracking  

---

## 👨‍💻 Author

Jayesh Rathi  

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
