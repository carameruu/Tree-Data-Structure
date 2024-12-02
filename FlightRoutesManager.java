import java.util.*;

public class FlightRoutesManager {
    // A map to represent the graph where cities are nodes and routes are edges
    private Map<String, List<String>> flightMap;

    // Constructor to initialize the flight map
    public FlightRoutesManager() {
        flightMap = new HashMap<>();
    }

    // Method to add a city to the flight map
    public void addCity(String city) {
        // Check if the city already exists in the map
        if (!flightMap.containsKey(city)) {
            flightMap.put(city, new ArrayList<>()); // Add city with an empty list of connections
            System.out.println(city + " added to the network.");
        } else {
            System.out.println(city + " already exists.");
        }
    }

    // Method to add a flight route between two cities
    public void addRoute(String city1, String city2) {
        // Ensure both cities exist in the map
        if (!flightMap.containsKey(city1) || !flightMap.containsKey(city2)) {
            System.out.println("Both cities must exist in the network.");
            return;
        }
        // Add each city to the other's list of connections (bidirectional route)
        flightMap.get(city1).add(city2);
        flightMap.get(city2).add(city1);
        System.out.println("Route added between " + city1 + " and " + city2);
    }

    // Method to display all cities in the flight network
    public void displayCities() {
        System.out.println("Cities in the Network:");
        for (String city : flightMap.keySet()) {
            System.out.println("- " + city);
        }
    }

    // Method to display all routes in the flight network
    public void displayRoutes() {
        System.out.println("Flight Routes:");
        for (Map.Entry<String, List<String>> entry : flightMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Method to find the shortest path between two cities using BFS
    public void findShortestRoute(String start, String destination) {
        // Ensure both cities exist in the map
        if (!flightMap.containsKey(start) || !flightMap.containsKey(destination)) {
            System.out.println("Both cities must exist in the network.");
            return;
        }

        // Initialize BFS components
        Queue<String> queue = new LinkedList<>(); // Queue for BFS traversal
        Map<String, String> parent = new HashMap<>(); // Map to store the parent of each node
        Set<String> visited = new HashSet<>(); // Set to track visited nodes

        queue.add(start); // Start BFS from the starting city
        visited.add(start);
        parent.put(start, null); // Root node has no parent

        // Perform BFS
        while (!queue.isEmpty()) {
            String current = queue.poll(); // Get the current city

            // If the destination is found, print the route
            if (current.equals(destination)) {
                printRoute(destination, parent);
                return;
            }

            // Traverse all neighbors of the current city
            for (String neighbor : flightMap.get(current)) {
                if (!visited.contains(neighbor)) { // Process unvisited neighbors
                    visited.add(neighbor);
                    parent.put(neighbor, current); // Record the parent
                    queue.add(neighbor);
                }
            }
        }

        // If the queue is empty and the destination is not found
        System.out.println("No route found between " + start + " and " + destination);
    }

    // Helper method to print the shortest route
    private void printRoute(String destination, Map<String, String> parent) {
        List<String> route = new ArrayList<>();
        for (String at = destination; at != null; at = parent.get(at)) {
            route.add(at); // Trace back from destination to start
        }
        Collections.reverse(route); // Reverse the route to get the correct order
        System.out.println("Shortest Route: " + String.join(" -> ", route));
    }

    // Main method to run the application
    public static void main(String[] args) {
        FlightRoutesManager manager = new FlightRoutesManager(); // Create an instance
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Menu loop
        while (true) {
            // Display menu options
            System.out.println("\nFlight Routes Manager:");
            System.out.println("1. Add City");
            System.out.println("2. Add Route");
            System.out.println("3. Display Cities");
            System.out.println("4. Display Routes");
            System.out.println("5. Find Shortest Route");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle user choice
            switch (choice) {
                case 1:
                    // Add a city
                    System.out.print("Enter city name: ");
                    String city = scanner.nextLine();
                    manager.addCity(city);
                    break;
                case 2:
                    // Add a route between two cities
                    System.out.print("Enter first city: ");
                    String city1 = scanner.nextLine();
                    System.out.print("Enter second city: ");
                    String city2 = scanner.nextLine();
                    manager.addRoute(city1, city2);
                    break;
                case 3:
                    // Display all cities
                    manager.displayCities();
                    break;
                case 4:
                    // Display all routes
                    manager.displayRoutes();
                    break;
                case 5:
                    // Find the shortest route
                    System.out.print("Enter start city: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter destination city: ");
                    String destination = scanner.nextLine();
                    manager.findShortestRoute(start, destination);
                    break;
                case 6:
                    // Exit the application
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
