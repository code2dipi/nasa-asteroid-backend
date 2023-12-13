
### 
# NASA Asteroids Explorer


This application consists of a Spring Boot backend and a React frontend for exploring NASA's asteroid data.
The backend application(REST API) will integrate the NASA APIs endpoints(Neo -Feed and Neo-Lookup).

## Backend (Spring Boot)

### Prerequisites

- Java 11 or later 
- Maven
- Spring boot version 3.2.0

### Installation

1. Clone the backend repository:

   ```bash
   git clone https://github.com/code2dipi/nasa-asteroid-backend.git

2. Open the backend project in your preferred IDE (IntelliJ, Eclipse, etc.).
3. Make sure you have the necessary dependencies installed by running `mvn clean install` in the terminal.
4. Start the backend server by running the main class or using the IntelliJ run configuration.
5. The backend will be available at http://localhost:8080.
5. The REST endpoints to integrate with NASA APIs :
     1. http://localhost:8080/api/asteroids/landing?startDate=2023-08-01&endDate=2023-08-08&apiKey=dhYabotopiVNsXMDMyLkGLE3EjomwBi8jMVipGzr
     2. http://localhost:8080/api/asteroids/details/54376887?apiKey=dhYabotopiVNsXMDMyLkGLE3EjomwBi8jMVipGzr

### Testing.
1. The application can be tested on Postman by sending an HTTP GET request.

#### The Query Parameters for first endpoint(asteroids/landing):


- startDate:                 YYYY-MM-DD (eg.2023-08-01)
- endDate:                   YYYY-MM-DD  (2023-08-08) (7 days after start_date)
- apiKey:                   dhYabotopiVNsXMDMyLkGLE3EjomwBi8jMVipGzr 

#### The Path Parameters for second endpoint(asteroids/details) for testing in postman:
The asteroid-Id from the previous endpoint response can be used to loop up details of specific asteroid.
asteroid_id, api key

## Frontend(React)

### Prerequisites

- Node.js (version 14 or later) 
- npm (Node Package Manager)
 

### Installation

1. Clone the frontend repository:

   ```bash
   https://github.com/code2dipi/nasa-asteroid-frontend-app.git

1. Open the frontend project in Visual Studio Code.
2. Select root directory and Install the required dependencies by running `npm install` in the terminal.
3. Start the frontend development server by running `npm start`. 
4. Make in sure backend server should be running before sending requests from frontend server(http://localhost:3000)

### Configuration
- Backend: Configure NASA API key in application.properties file.

- Frontend: Configure backend API URL in src/config.js file.
### Usage
Access the React app at http://localhost:3000.
Explore asteroids data using the provided date picker(Date limit is 7 days from the start date)
Click on "Details" to view more information about a specific asteroid.


   
