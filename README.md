# woodland mansion 🌲🏚️

[![Download Latest Release](https://img.shields.io/github/v/release/andrejkoller/woodland-mansion?label=Download&style=for-the-badge&logo=windows&color=blue)](https://github.com/andrejkoller/woodland-mansion/releases/latest)
[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-green?style=for-the-badge&logo=java)](https://openjfx.io/)

"You find yourself trapped in what appears to be an abandoned mansion,  
with only your name in your memory."  
"It's time to escape this eerie place."

Woodland Mansion is a text-adventure game built with Java and JavaFX.  
Explore a mysterious mansion, interact with objects, fight monsters, and find your way out!

## Stack

- Java 21+
- JavaFX
- Maven

## JavaFX Setup

This project uses JavaFX.  
If your JDK does not include JavaFX, you need to install it separately.

- Download JavaFX from [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Follow the setup instructions for your OS
- Make sure to set the `PATH_TO_FX` environment variable or update your Maven configuration if needed

If you use Maven, the required JavaFX dependencies are already included in `pom.xml`.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/andrejkoller/woodland-mansion.git
cd woodland-mansion
```

2. Build the project:

```bash
mvn clean install
```

3. Run the game:

```bash
mvn javafx:run
```

##  Screenshots

<img width="1920" height="1080" alt="Screenshot 1" src="https://github.com/user-attachments/assets/680a8e3b-26c7-4bfa-ac56-2a14a144b6c4" />
<img width="1920" height="1080" alt="Screenshot 2" src="https://github.com/user-attachments/assets/02df170a-75b2-4d1e-8f0b-546c35a420d9" />

## License

[MIT](LICENSE)
