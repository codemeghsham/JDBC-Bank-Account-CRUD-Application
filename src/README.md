# Simple Banking System

This is a simple Java program for managing bank accounts using JDBC (Java Database Connectivity). The program allows users to perform various operations such as creating a customer, showing all customers, showing bank account types, showing customer by ID, updating customer balance, and deleting customer records.

## Features

- **Create Customer**: Allows users to create a new customer by providing their first name, last name, and account type.
- **Show All Customers**: Displays a list of all customers along with their account details.
- **Show Bank Account Type**: Allows users to filter and display customers based on their account type (Salary or Savings).
- **Show Customer By ID**: Retrieves and displays customer details based on their unique ID.
- **Update Customer Balance**: Enables users to update the balance of a customer's account by either crediting or debiting.
- **Delete Customer Record**: Permits users to delete a customer's record based on their ID.

## Setup

1. **Database Configuration**: Ensure that you have a MySQL database set up. You can modify the database connection URL, username, and password in the code to match your database configuration.

2. **JDBC Driver**: This program uses the MySQL JDBC driver. Make sure you have the driver added to your project's dependencies.

3. **Running the Program**: Compile and run the `Main.java` file to execute the program. Follow the on-screen instructions to navigate through the menu and perform various operations.

## Usage

1. Upon running the program, you will be presented with a menu displaying different options.
2. Choose an option by entering the corresponding number and follow the prompts to perform the desired operation.
3. For example, to create a new customer, select option 1 and provide the required information such as first name, last name, and account type.
4. To view all customers, select option 2. Similarly, follow the same pattern for other operations.

## Dependencies

- Java Development Kit (JDK)
- MySQL JDBC Driver

## Note

- This program assumes a basic understanding of Java programming and JDBC concepts.
- Ensure that you have proper database permissions set up to perform CRUD operations.
- Make sure to handle exceptions appropriately, especially when dealing with database operations.
