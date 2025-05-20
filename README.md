# Woodland Mansion 🌲🏚️

## About This Game

"You find yourself trapped in what appears to be an abandoned mansion,  
with only your name in your memory."  
"It's time to escape this eerie place."

Woodland Mansion is a text-adventure game built with Java and JavaFX.  
Explore a mysterious mansion, interact with objects, fight monsters, and find your way out!

## Features

- Immersive text-based adventure gameplay
- Custom pixel fonts and themed UI
- Animated custom mouse cursors
- Multiple rooms, items, and monsters
- Command-based input system

## Screenshots

<div align="center">
  <div>
    <img src="https://github.com/user-attachments/assets/680a8e3b-26c7-4bfa-ac56-2a14a144b6c4">
  </div>
  <div>
    <img src="https://github.com/user-attachments/assets/02df170a-75b2-4d1e-8f0b-546c35a420d9">
  </div>
  <div>
    <img src="https://github.com/user-attachments/assets/aebd6625-cfc7-4fc6-87b1-d27d5d83abaf">
  </div>
  <div>
    <img src="https://github.com/user-attachments/assets/5d2f0870-8d38-4688-a561-5bfe38c5cfe1">
  </div>
</div>

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### JavaFX Setup

This project uses JavaFX.  
If your JDK does not include JavaFX, you need to install it separately.

- Download JavaFX from [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Follow the setup instructions for your OS
- Make sure to set the `PATH_TO_FX` environment variable or update your Maven configuration if needed

If you use Maven, the required JavaFX dependencies are already included in `pom.xml`.

### Build & Run

1. Clone the repository:
    ```sh
    git clone https://github.com/andrejkoller/woodland-mansion.git
    cd woodland-mansion
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the game:
    ```sh
    mvn javafx:run
    ```

## Controls

- Use the input field to type commands (e.g., `help`, `move`, `attack`, `status`, etc.)
- Follow on-screen prompts to interact with the game

## Credits

- Programming: Andrej, Maxi
- Design: Saschinka
