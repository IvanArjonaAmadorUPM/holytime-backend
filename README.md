# holytime-backend
Holytime backend for holytime movile app project

Welcome to the HolyTime backend project! This project uses an ACO (Ant Colony Optimization) algorithm to generate personalized itineraries for tourists based on their input preferences.

An ACO (Ant Colony Optimization) algorithm is a metaheuristic algorithm that is used to find approximate solutions to optimization problems. It is inspired by the behavior of ant colonies, which are able to find the shortest path between their nest and a food source through the use of pheromone trails. In ACO algorithms, artificial ants are used to search for solutions to a given problem, and the pheromone trails are used to guide the search towards the most promising solutions. ACO algorithms have been used in a variety of applications, including routing, scheduling, and data clustering.
To use the HolyTime service, you will need to provide a JSON object containing information about your preferences, such as the amount of time available for the trip, your location, and your interests. The ACO algorithm will use this information to determine the optimal route and generate a list of recommended stops for you to visit.

The input JSON object should have the following format:


eleccionesDelUsuario:{

    time:
    
    hourSelected:
    
    lat:
    
    long:
    
    preferences: [Array] (PIT preferences)
    
    accesible:
    
    food: [ ] 
    
    events [ ]
}

The output of the HolyTime service will be a JSON object containing your itinerary, including details about each stop such as its location, recommended activities, and estimated travel time. This will make it easier for you to plan your trip and make the most of your time in a new destination.

Thank you for choosing to use HolyTime!
