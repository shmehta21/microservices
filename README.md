# microservices

Small microservices like UserService, HotelService and RatingService which interact with each other.

1.) UserService provides capability to define new users and get information about existing users.
2.) HotelService provides capability to define new hotels and get information about existing hotels. 
3.) RatingService provides capability to get all ratings for a hotel and  all ratings given by a user for a specific hotel

Requests hit a common API Gateway from which they are routed to specific micro services
Common config used by each microservice is read from application.properties stored in GIT. This feature is enabled by using ConfigServer and ConfigClient functionality.
