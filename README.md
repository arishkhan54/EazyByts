## Chat Application with User Authentication

### Description

This is a real-time chat application featuring user authentication, private messaging, and chat room functionality. The app allows users to register, log in, and engage in secure, private conversations. It is designed for scalability and security, ensuring a seamless user experience.

### Features

- **Responsive User Interface:** Built with HTML, CSS, and JavaScript, ensuring accessibility and responsiveness across devices and screen sizes.
- **Real-Time Messaging:** Utilizes WebSockets for real-time communication between users.
- **User Authentication and Authorization:** Secure user registration and login using Spring Security and JWT (JSON Web Tokens).
- **Private Messaging and Chat Rooms:** Supports private conversations and multiple chat rooms for group interactions.

### Additional Features

- **Scalability:** The application is designed to scale easily, allowing for more users and chat rooms as needed.
- **Security Measures:** In addition to Spring Security and JWT, the app includes input validation and protection against common web vulnerabilities.

### Database Structure

The application uses MySQL to store user and message data. Below is the structure of the main tables: 

- **`users` Table:**
  - `id` (BIGINT, Primary Key, Auto-Increment): Unique identifier for each user.
  - `username` (VARCHAR(50), UNIQUE): The user's unique username.
  - `email` (VARCHAR(100)): User's email address.
  - `password` (VARCHAR(255)): Hashed password for secure authentication.

- **`messages` Table:**
  - `id` (BIGINT, Primary Key, Auto-Increment): Unique identifier for each message.
  - `sender` (VARCHAR(50)): Username of the message sender.
  - `receiver` (VARCHAR(50)): Username of the message receiver.
  - `content` (TEXT): The text content of the message.
  - `timestamp` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): The time when the message was sent.

### Installation

To set up the project locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone <repository-url>

### Technology Stack

- **Frontend:** HTML, CSS, JavaScript.
- **Backend:** Java and Spring Boot for server-side logic.
- **Security:** Spring Security integrated for robust authentication and authorization.
- **Database:** MySQL, with tables for storing user and message data.

### How to Run the Project

1. **Clone the Repository:** Clone this repository to your local machine.
2. **Frontend Setup:** Run the frontend using a static server or open the HTML file directly in your browser.
3. **Backend Setup:** Start the Spring Boot application and ensure MySQL is running with the required tables (`messages` and `users`).
4. **API Endpoints:** The backend includes RESTful APIs for user registration, login, and messaging.
5. **Start Chatting:** After setup, users can register, log in, and start secure, real-time conversations.

---


