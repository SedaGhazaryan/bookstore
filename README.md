# Book Store project
## 1. Overview
A Maven project of Spring MVC application example using Spring Framework.

## 2. Functionalities:

*User registration

*Loging in

*Loging out

*Adding books

*Editing books

*Deleteing books

*Blocking or unblocking users

*Viewing booklist

*Viewing userlist


## 3. To get the code:

Clone the repository:

    $ git clone https://github.com/SedaGhazaryan/bookstore.git

## 4. Development environment
To run this application, please do the following steps below.

1. Install the following items.
	* JDK 8 (or later)
	* IDEs (IntelliJ) (require Lombok and Maven plugins)
	* MySQL


2. Check out this source project on the IDE.

3. Change the  parameters of the config file [application.properties](/src/main/resources/application.properties) as necessary.

| Paramater Name      | Description                                        |
|:--------------------|:---------------------------------------------------|
| host                | database host                                      |
| database            | database name                                      |
| jdbc.username       | database user name                                 |
| jdbc.password       | database password                                  |
| jdbc.url            |                                                    |

4. Create database and user in MySQL according to parameters in [application.properties](/src/main/resources/application.properties).
