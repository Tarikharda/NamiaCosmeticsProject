# Oriental Group Mobile App

## Project Background
The company currently lacks a mobile application, making it challenging for users to search for products and save their favorites for future reference. Direct customer interactions can also be cumbersome. To address these issues and streamline payment methods, the company has initiated this mobile app project.

## Project Specifications
The company primarily communicates with users via email and WhatsApp, which can be cumbersome for both the company and users. To improve the user experience, we have integrated PayPal into our mobile app for users outside of Morocco. For users within Morocco, we offer a cash-on-delivery payment option based on the manager's decision.

We have also incorporated a delivery pricing table, sourced from an Excel sheet, into the database to calculate total order costs based on the user's location.

## Tools and Reasons
- **Java & XML**: We chose Java and XML for frontend development because of our proficiency and comfort with these languages.

- **MySQL**: The company uses MySQL as its primary database, hence our use of MySQL to facilitate seamless data management.

- **SQLite**: We opted for SQLite as it's the ideal choice for Android applications and offers ease of use.

- **Git & GitHub**: Git and GitHub are our project management tools. While we encountered initial challenges, we became more adaptable over time, enabling us to collaborate effectively using commits.

- **Volley Library & API**: Volley was chosen for its ease of use and reliability in fetching data from the database securely.

- **Server**: We host our PHP files on the 000webhost website, which also houses our database. This free and fast hosting platform serves as our server.

- **Android Studio**: For Android app development, we rely on Android Studio, a powerful tool tailored for the task.

## Project Structure
Below is a visual representation of the project directory tree:

```plaintext
project-root/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── com/
│   │   │   │   │   ├── orientalgroup/
│   │   │   │   │   │   ├── ...   # Your Java source files
│   │   │   │   │   │
│   │   │   │   ├── ...
│   │   │   │
│   │   ├── res/
│   │   │   ├── ...   # Your resource files
│
├── ...
│
├── README.md
