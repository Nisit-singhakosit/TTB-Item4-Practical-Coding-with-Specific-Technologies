# Item 4 : Practical Coding with Specific Technologies


### Indicators
Practical Coding with Specific Technologies (40%)
Preferred Skill: Java Spring Boot และ API Integration


### Questions
Scenario:
Our Product Owner has challenged our department to create a CRM platform
that helps bank staffs to input customers' service requests such as credit card amount for payment, remaining amount on account, etc. via Call Center or Branch. As a Software Engineer member, your main responsibility is to deliver
microservices for this platform. The team will start by developing the CRM microservices.

Task:
As part of the Software Engineer team for this test, your goal is to deliver:
1. CRM Microservices (Mockup code):
- Use Spring Boot framework implement REST API.
- Include these features:
  a.Receive and process customers' request.
  b.Forward requests to back-end office who need to fix the issue.
  c.Update request status (In-progress, completed, canceled).
- Create endpoints to:
  a.Receive triggers from other microservices.
  b.Trigger other microservices (optional: include a simple diagram to show the flow).

2. Documentation:
   a.Document the Ordering microservices specifications.
   b.Use Swagger format or submit a Postman collection.

3. Unit testing:
   a.Unit testing is required.

# CRM Microservices Specifications

**Introduction**

The CRM platform is designed to help bank staff efficiently input and manage customer service requests through a Call Center or Branch. The platform supports tracking service requests, their statuses, and updates.

This document outlines the API specifications for the **CRM microservices**.

**Endpoints Overview**

The CRM microservice exposes the following RESTful APIs under the base URL /crm.

| **HTTP Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| GET | /requests | Fetch all customer service requests. |
| POST | /request | Create a new service request. |
| GET | /{id} | Get the status of a specific service request. |
| PUT | /{id} | Update the status of an existing service request. |

**Endpoint Specifications**

**1\. Fetch All Customer Service Requests**

- **Endpoint:** /crm/requests
- **Method:** GET
- **Description:** Retrieves a list of all customer service requests.
- **Response Format:** application/json
- **Response Code:** 200 OK

**Response Example:**
```
[
	{
		"id": 1,
		"customerName": "John Doe",
		"requestType": "Credit Card Payment Inquiry",
		"description": "Check available balance for payment",
		"status": "Pending"
	},
	{
		"id": 2,
		"customerName": "Jane Smith",
		"requestType": "Account Balance Inquiry",
		"description": "Check remaining account balance",
		"status": "Completed"
	}
]
```
**2\. Create a New Service Request**

- **Endpoint:** /crm/request
- **Method:** POST
- **Description:** Creates a new service request with initial details provided by the user.
- **Request Body Format:** application/json
- **Response Format:** application/json
- **Response Code:** 200 OK

**Request Example:**
```
{
	"customerName": "Alice Brown",
	"requestType": "New Credit Card Application",
	"description": "Request for issuing a new credit card"
}
```
**Response Example:**
```
{
	"id": 3,
	"customerName": "Alice Brown",
	"requestType": "New Credit Card Application",
	"description": "Request for issuing a new credit card",
	"status": "Pending"
}
```
**3\. Get Status of a Service Request**

- **Endpoint:** /crm/{id}
- **Method:** GET
- **Description:** Fetches the status of a specific service request by its unique ID.
- **Path Parameter:** id - ID of the service request (type: Long).
- **Response Format:** application/json
- **Response Code:** 200 OK

**Response Example:**
```
{
	"id": 1,
	"customerName": "John Doe",
	"requestType": "Credit Card Payment Inquiry",
	"description": "Check available balance for payment",
	"status": "Pending"
}
```
**4\. Update Status of a Service Request**

- **Endpoint:** /crm/{id}
- **Method:** PUT
- **Description:** Updates the status of an existing service request.
- **Path Parameter:** id - ID of the service request (type: Long).
- **Query Parameter:** status - New status to be assigned (type: String).
- **Response Format:** application/json
- **Response Code:** 200 OK

**Request Example:**
```
PUT /crm/1?status=Completed
```
**Response Example:**
```
{
	"id": 1,
	"customerName": "John Doe",
	"requestType": "Credit Card Payment Inquiry",
	"description": "Check available balance for payment",
	"status": "Completed"
}
```
**ServiceRequest Data Model**

| **Field Name** | **Type** | **Description** |
| --- | --- | --- |
| id  | Long | Unique identifier for the service request. |
| customerName | String | Name of the customer associated with the request. |
| requestType | String | Type of the service request (e.g., Inquiry). |
| description | String | Detailed description of the service request. |
| status | String | Status of the request (Pending, Completed, etc.). |

**Error Handling**

All errors will return an appropriate HTTP status code along with a JSON response describing the error.

| **HTTP Status Code** | **Description** |
| --- | --- |
| 400 Bad Request | Invalid input or missing parameter. |
| 404 Not Found | Resource not found (e.g., invalid id). |
| 500 Internal Server Error | Server-side error. |





