# StarbucksCoffeeShopLocator

Web Application in Servlet which gives the information about the Starbucks Coffee. 

# How to run ?

--> First, install the latest tomcat server and set it to target runtime for new Dynemic Web Project.

--> Make sure, You put the starbucks.csv file under the /WebContent/WEB-INF folder.

# Location Search

When the User selects Location from the drop-down select box on the search form, you can expect a latitude/longitude pair to be present in the search form.

For example, a User would enter 33.9733, -118.244 into the search box. Your servlet will be responsible for parsing the latitude and longitude values out of the submitted value.

Your servlet can assume a default search radius of 10 miles.

#  City and Zip Code Search

When the User selects City or Zip form the drop-down select box, you can expect a city name (or partial name), or a zip code to be submitted as the query.

A coffee shop location can be reported when the city, or zip code is a match.

#  Data Source

Your data source will be a CSV file located in your /WEB-INF/ directory.

# Source for the Great Circle Distance formula

 the search radius Using the Great Circle Distance formula outlined at https://en.wikipedia.org/wiki/Great-circle_distance.

