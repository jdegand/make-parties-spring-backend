# Make Parties Spring Backend

This is a conversion of a [past MakeSchool tutorial I completed](https://github.com/jdegand/makeparties) to Spring Boot. 

## Screenshots

![](screenshots/make-parties-spring-backend-1.png)

***

![](screenshots/make-parties-spring-backend-2.png)

***

![](screenshots/make-parties-spring-backend-3.png)

***

![](screenshots/make-parties-spring-backend-4.png)

***

![](screenshots/make-parties-spring-backend-5.png)

***

![](screenshots/make-parties-spring-backend-6.png)

***

![](screenshots/make-parties-spring-backend-7.png)

***

## Built with

- Java 17
- Spring Boot 3.1
- H2 Console
- Lombok

## Thoughts 

- Doing the mappings between entities is easy but there can be a few gotchas
- With Event and Rsvp, there exists a cyclic dependency and you get a stack overflow if you try to get Event - either use an annotation or a dto. See [Stack Overflow](https://stackoverflow.com/questions/61993704/stack-overflow-error-for-hibernate-one-to-one-mapping-with-spring-data-jpa) for more.  
- Having to use DTOs with mappings (ManyToOne etc) is glossed over and obscured in a lot of implementations
- Using DTOs requires extra work -> easier to create a bug
- @JsonBackReference can't be used on a collection.
- Could add a third table for the list of rsvps -> @JoinTable
- It can be annoying to use Integer in place of Long for ids as it can require extra work i.e. overriding method signatures
- JPARepository works best with Long ids
- Using Builder on an entity with a mapping warns - "@Builder will ignore the initializing expression entirely. If you want the initializing expression to serve as default, add @Builder.Default. If it is not supposed to be settable during building, make the field final." -> Need to investigate further for a solution
- Added takesPlaceOn to Event Entity (stretch challenge in original MakeSchool tutorial)
- Added timestamps as well but I did not use them in the frontend
- Added validation after I had the frontend working with the backend
- The tutorial did not feature validation 
- Kept the validation fairly minimal -  a few required properties and date validation for takesPlaceOn

## Continued Development

- Use ResponseEntity for all routes?
- Better exception handling / Custom exceptions
- The validation for event put requests needs improvement - need to check if takesPlaceOn for valid date 
- Testing

## Useful Resources

- [YouTube](https://www.youtube.com/watch?v=qIRm6hXlghs) - OneToMany | ManyToOne Relationship In Hibernate | SpringBoot | JPA | Java
- [Stack Overflow](https://stackoverflow.com/questions/37541168/code-architecture-of-service-interface-and-service-impl-classes-spring) - code architecture of service interface and service implementation
- [Stack Overflow](https://stackoverflow.com/questions/49254104/spring-boot-timestamp) - timestamps
- [YouTube](https://www.youtube.com/watch?v=j8J9Cx8pEsI) - Build REST API with Spring Boot and JPA [2021] - 25 Create timestamps by JPA
- [YouTube](https://www.youtube.com/watch?v=OMmxqc9zdSg) - Spring Boot For Beginners - One-To-Many in Spring Data
- [Baeldung](https://www.baeldung.com/circular-dependencies-in-spring) - circular dependencies
- [Stack Overflow](https://stackoverflow.com/questions/47640698/com-fasterxml-jackson-databind-ser-beanserializer-serialize-spring-jpa)
- [Github](https://github.com/teddysmithdev/pokemon-review-springboot) - pokemon review springboot
- [Stack Overflow](https://stackoverflow.com/questions/64246525/onetomany-column-not-showing) - one to many column not showing
- [Stack Overflow](https://stackoverflow.com/questions/61993704/stack-overflow-error-for-hibernate-one-to-one-mapping-with-spring-data-jpa) - stack overflow for hibernate one to one mapping
- [Stack Overflow](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion) - bidirectional relationships and infinite recursion
- [YouTube](https://www.youtube.com/watch?v=5HAzLAnJPKU) - 40 Enable CORS in Spring Boot
- [Stack Overflow](https://stackoverflow.com/questions/53431292/spring-implementation-of-cors-is-not-rejecting-messages-when-i-expected-it-to) - cors is not rejecting messages when i expect
- [Stack Overflow](https://stackoverflow.com/questions/18385361/return-json-for-responseentitystring) - return json for response entity
- [YouTube](https://www.youtube.com/watch?v=JmMXQaSYl4Y) - Spring Boot tutorials | Spring boot full course - How to use ResponseEntity in REST API?
- [Stack Overflow](https://stackoverflow.com/questions/54438313/implement-status-200-when-delete-is-send) - implement status 200 when delete is sent
- [Stack Overflow](https://stackoverflow.com/questions/5478328/in-which-case-do-you-use-the-jpa-jointable-annotation) - jpa jointable annotation
- [Baeldung](https://www.baeldung.com/java-string-valid-date) - java string valid date
- [How to Do in Java](https://howtodoinjava.com/java/date-time/date-validation/) - date validation