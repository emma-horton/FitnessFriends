# Fitness Friends

**Fitness Friends** is a virtual pet application designed to make fitness tracking fun and engaging. By integrating with the Strava API, the app allows users to track their fitness activities, set goals, and care for a virtual pet whose health and behavior are influenced by the user's progress. Whether you're using a Garmin device, another fitness tracker, or no tracker at all, Fitness Friends provides a unique way to stay motivated on your fitness journey.


## **Features**
- **Virtual Pet Interaction**: Create and care for a virtual pet (e.g., Parrot, Turtle) whose health and behavior change based on your fitness progress.
- **Fitness Goal Tracking**: Set weekly goals for distance, duration, or frequency across various sports (e.g., running, cycling, swimming).
- **Strava Integration**: Connect your Strava account to sync fitness activities from multiple trackers.
- **Dynamic Pet Behavior**: Pets respond to your progress with different states (e.g., Healthy, Sick, Dead).
- **User-Friendly Interface**: Register, log in, and interact with your pet through a simple console-based interface.


## **Technologies Used**
- **Java**: Core application logic and object-oriented design.
- **SQLite**: Database for storing user data, fitness goals, and pet details.
- **Python**: Strava API integration for retrieving fitness activity data.
- **Strava API**: Sync fitness data from multiple trackers.
- **PlantUML**: UML diagrams for design documentation.
- **Maven**: Build and dependency management.


## **Design Patterns Implemented**
This project leverages several software design patterns to ensure a robust, maintainable, and scalable architecture:
1. **Factory Pattern**: For creating virtual pets based on user preferences.
2. **Abstract Factory Pattern**: For generating sport-specific fitness goals (e.g., Running, Cycling, Swimming).
3. **State Pattern**: For managing dynamic pet behaviors (e.g., Healthy, Sick, Dead).
4. **Template Pattern**: For defining core pet actions (e.g., move, eat) with customizable steps for each pet type.
5. **DAO Pattern**: For streamlining data interaction with the SQLite database.


## **Setup and Installation**

### **Prerequisites**
- Java 8 or higher
- Python 3.11 or higher
- Maven
- SQLite

### **Steps to Run the Project**
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd GarminVirtualPetProject
   ```
2. **Build the Project:**
    ```bash
    mvn clean install
    ```
3. **Run the Application:**
    ```bash
    mvn exec:java
    ```
4. **Start the Redirect Server:**
    ```bash
    node redirectServer.js
    ```

## **How to Use**
1. Register or Log In:
* Register a new account or log in with an existing one.

2. Connect Strava:
* Follow the prompts to authorize your Strava account.

3. Create a Pet:
* Choose a pet type (e.g., Parrot, Turtle) and name your pet.

4. Set Fitness Goals:
* Define weekly goals for distance, duration, or frequency in your preferred sport.

5. Track Progress:
* Sync your fitness activities from Strava and monitor your pet's health and behavior.

6. Interact with Your Pet:
* View your pet's status, play with it, and ensure its health by meeting your fitness goals.

### **Future Plans**
* Add more pet types (e.g., Dog, Cat) with unique behaviors.
* Introduce new fitness goal categories (e.g., calories burned).
* Implement a graphical user interface (GUI) for a more interactive experience.
* Add real-time notifications for pet health updates.
* Explore additional design patterns, such as the Observer Pattern for real-time updates.

## **Contributing**
Contributions are welcome! If you’d like to contribute, please fork the repository and submit a pull request.
