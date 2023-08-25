# jdbc-application


A Java Spring Boot application that interacts with databases using JDBC for data access and manipulation.

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [Maintainers](#maintainers)

## Install

<b>1. Prerequisites: </b> Ensure you have the following tools installed on your system:

* Postgres and PgAdmin
* Java 17
* Postgres SQL driver dependency

<b>2. Clone the repository: </b> Clone this repository to your local machine using the following command: </b>
```
git clone https://github.com/your-username/your-project.git
```

<b>3.Spring Boot Application Setup: </b> 

1. Import the project as a Gradle project.
2. Configure the database connection in application.properties with your PostgreSQL credentials.


<b> 4. Running the Application </b>    

1. Navigate to src/main/java directory
2. Run ChinookApplication 

## Usage

To interact with the data, you can use the provided CustomRepositoryTestRunner class. 
Some methods can accept arguments or parameters that allow you to customize the displayed data.
Here's how you can use it:

<b>1. Fetch all customers: </b>

```
List<Customer> customers = customerRepository.findAll();
```

<b>2. Fetch customer by ID: </b>

```
Customer customer = customerRepository.findById('ID');
```

<b>3. Fetch customer by name: </b>

```
List<Customer> customerFindByName = customerRepository.findByName("name");
```

<b>4. List a page of customers by specified limit and offset:</b>

```
List<Customer> customersPage = customerRepository.getCustomerSubset('limit', 'offset');
```

<b>5. Add new customer:</b>

```
Customer customerAddition = new Customer();
customerAddition.setFirstName("name");
customerAddition.setLastName("lastname");
customerAddition.setCountry("country");
customerAddition.setPostalCode("postalcode");
customerAddition.setPhoneNumber("phone");
customerAddition.setEmail("email");
```

<b>6.Fetch country with the most customers:</b>

```
CustomerCountry countryWithMostCustomers = customerRepository.getCountryWithMostCustomers();
```

<b>7. Update customer by ID:</b>

```
Customer customerUpdate = customerRepository.findById('ID');
customerUpdate.setFirstName("name"); 
customerUpdate.setLastName("lastname"); 
customerUpdate.setCountry("country"); 
customerUpdate.setPhoneNumber("phone");
customerUpdate.setPostalCode("postalcode");
```

<b>8.Fetch the customer who is the highest spender:</b>

```
CustomerSpender highestSpender = customerRepository.findHighestSpender();
```

<b>9.Fetch a customers most popular genre(s):</b>

```
Long customerId = 'ID';
List<CustomerGenre> mostPopularGenre = customerRepository.findMostPopularGenres(customerId);
```


## Maintainers

[@OzKara](https://github.com/OzKara), [@MichalPajestka](https://github.com/MichalPajestka)

