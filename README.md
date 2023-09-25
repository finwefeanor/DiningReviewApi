# DiningReviewApi
DiningReviewApi using Spring Framework - WIP

## Overview
This API provides functionalities related to dining reviews, allowing users to submit their reviews, and administrators to approve or reject them. 
The system also provides allergy-specific reviews such as for peanuts, eggs, and dairy, to help users select restaurants that cater to their dietary needs.

## Features
- User registration and management with unique display names.
- Restaurant information management, including details like addresses and average allergy-specific scores.
- Dining review submission, including optional scores for peanut, egg, and dairy allergies.
- Administrator review and approval system.

## Setup & Installation
1. Clone this repository.
2. Navigate to the project directory.
3. Ensure you have Maven installed. If not, download and install (https://maven.apache.org/install.html).
4. Run "mvn clean install" to install inside your terminal.
5. Start the application with "mvn spring-boot:run".

## API Endpoints
**TODO will be implemented at further stages when API routes defined. For example:)
- `/api/users` - Endpoint for user-related operations.
- `/api/restaurants` - Endpoint for managing restaurants.
...etc

## Technology Stack
- Spring Boot for the application framework.
- Lombok to reduce boilerplate code.
- JPA (Java Persistence API) for database operations.
- **TODO Add other technologies, frameworks, or libraries and database as the project grows.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)

