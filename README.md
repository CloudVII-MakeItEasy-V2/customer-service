

# Customer API

This is a RESTful API for managing customer data, including features for registration, login, retrieving customer information, and balance extraction.

## Base URL

```
/api/customers
```

## Endpoints

### 1. Register a Customer

**Endpoint:**
 `POST /register`

**Description:**
 Registers a new customer.

**Request Body:**

```json
{
  "name": "string",
  "email": "string",
  "password": "string",
  "balance": "number"
}
```

**Response:**

- **200 OK:** Returns the registered customer object.
- **400 Bad Request:** Customer registration failed due to invalid input or business logic.

------

### 2. Customer Login

**Endpoint:**
 `POST /login`

**Description:**
 Logs in a customer using email and password.

**Request Parameters:**

- `email` (string): Customer's email address.
- `password` (string): Customer's password.

**Response:**

- **200 OK:** `Login Successful!`
- **401 Unauthorized:** `Invalid credentials`

------

### 3. Get All Customers

**Endpoint:**
 `GET /getAllCustomers`

**Description:**
 Retrieves a list of all customers.

**Response:**

- **200 OK:** Returns a list of customer objects.

------

### 4. Get Customer Information by ID

**Endpoint:**
 `GET /getInformationById/{id}`

**Description:**
 Fetches customer information by their ID.

**Path Variable:**

- `id` (integer): ID of the customer.

**Response:**

- **200 OK:** Returns the customer object if found.
- **404 Not Found:** Returns an empty customer object if the ID does not exist.

------

### 5. Extract Balance

**Endpoint:**
 `POST /extractBalance`

**Description:**
 Extracts a specific amount from a customer's balance.

**Request Parameters:**

- `id` (integer): ID of the customer.
- `extractNum` (integer): Amount to be extracted.

**Response:**

- **200 OK:** Balance extraction was successful.
- **400 Bad Request:** The customer ID does not exist.

------

## Dependencies

This API uses:

- **Spring Boot** for REST API development.
- **HTTP Status Codes** to represent success or failure of operations.

## Notes

1. Ensure the database schema aligns with the `Customer` entity class.
2. Validate inputs on the client side to minimize bad requests.
3. Consider adding authentication/authorization for production.

------

Feel free to update or expand upon this README based on your project's specific requirements or additional features.

## Address Validation

This system integrates with the SmartyStreets API for address validation. The validation logic ensures customer addresses are valid during the registration process.
If customer address is not valid, it would ignore the request


## Project Structure

```
src/main/java/org/group/customer
├── controller
│   └── CustomerController.java   # Handles HTTP requests
├── entity
│   └── Customer.java             # Entity class for customer data
├── repository
│   └── CustomerRepository.java   # JPA repository for customer data
├── service
│   └── CustomerService.java      # Business logic layer
└── util
    └── AddressValidator.java     # Address validation utility
```

---

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/customer-management-api.git
   cd customer-management-api
   ```

2. **Install Dependencies**:
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Configure Database**:
   Update `application.properties` with your database credentials.

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   Visit `http://localhost:8080/api/customers`.

---

## Dependencies

- **Spring Boot**: For RESTful API development.
- **Spring Data JPA**: For database interactions.
- **SmartyStreets API**: For address validation.
- **Jakarta Persistence**: For entity modeling.

