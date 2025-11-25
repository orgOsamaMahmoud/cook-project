🍳 Cook Project — Recipe & Meal Management API

The Cook Project is a backend API designed to manage recipes, ingredients, and cooking steps in a clean and scalable way.
It allows users to store, update, search, and organize cooking data using modern development practices.

Built with Node.js, Express.js, and SQL database, the API follows REST structure, uses authentication, and provides a structured kitchen-management experience.

🚀 Tech Stack

Backend: Node.js, Express.js

Database: MySQL / MariaDB

Query Builder / ORM: Sequelize / Raw SQL

Authentication: JWT (Login/Register)

Validation: Express Middleware

Tools: Postman, Git, GitHub Projects

🎯 Features
🍽 Recipe Management

Add, edit & delete recipes

Attach ingredients, categories, steps

Upload recipe images (optional)

🧂 Ingredients System

Store ingredients with measurements

Link ingredients to recipes

Quantity & units control

🔍 Search & Filtering

Search recipes by:
✔ Name
✔ Category
✔ Ingredient
✔ Difficulty

👤 User Accounts

Signup / Login

JWT-based authentication

Saved recipes (optional)

📦 Extras

Error-handling middleware

Database seeders

Modular folder structure

Clean, scalable code

📁 Project Structure
cook-project/
 ├── controllers/
 ├── routes/
 ├── models/
 ├── middlewares/
 ├── config/
 ├── utils/
 └── app.js

⚡ Installation & Startup
git clone https://github.com/orgOsamaMahmoud/cook-project
cd cook-project
npm install
npm start

🔑 Environment Variables

Create .env:

DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_PASS=yourpassword
DB_NAME=cookdb

JWT_SECRET=your_jwt_key

🧪 Testing API (Postman)

Create recipes

Add ingredients

Filter by category

Test JWT login routes
