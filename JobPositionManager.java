import java.util.*;

public class JobPositionManager {

    static class JobPosition implements Comparable<JobPosition> {
        String positionName;
        int priority;  // Lower value = higher priority
        String department;
        int requiredExperience; // in years

        public JobPosition(String positionName, int priority, String department, int requiredExperience) {
            this.positionName = positionName;
            this.priority = priority;
            this.department = department;
            this.requiredExperience = requiredExperience;
        }

        @Override
        public int compareTo(JobPosition other) {
            // Compare by priority first, then by experience
            if (this.priority != other.priority) {
                return Integer.compare(this.priority, other.priority);
            }
            return Integer.compare(other.requiredExperience, this.requiredExperience); // Higher experience preferred
        }

        @Override
        public String toString() {
            return "JobPosition{" +
                    "positionName='" + positionName + '\'' +
                    ", priority=" + priority +
                    ", department='" + department + '\'' +
                    ", requiredExperience=" + requiredExperience +
                    '}';
        }
    }

    public static void main(String[] args) {
        PriorityQueue<JobPosition> jobQueue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        // Predefined job positions
        Map<String, JobPosition> predefinedPositions = new HashMap<>();
        predefinedPositions.put("developer", new JobPosition("Developer", 1, "IT", 3));
        predefinedPositions.put("manager", new JobPosition("Manager", 2, "HR", 5));
        predefinedPositions.put("analyst", new JobPosition("Analyst", 3, "Finance", 2));
        predefinedPositions.put("intern", new JobPosition("Intern", 5, "IT", 0));

        System.out.println("Welcome to the Job Position Manager!");
        System.out.println("Available job positions:");
        for (String positionName : predefinedPositions.keySet()) {
            System.out.println(predefinedPositions.get(positionName));
        }

        System.out.println("\nCommands: add [positionName], next, show, exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split("\\s+");

            switch (parts[0].toLowerCase()) {
                case "add":
                    if (parts.length != 2) {
                        System.out.println("Usage: add [positionName]");
                        break;
                    }
                    String positionName = parts[1].toLowerCase();
                    if (predefinedPositions.containsKey(positionName)) {
                        jobQueue.add(predefinedPositions.get(positionName));
                        System.out.println("Added job position: " + predefinedPositions.get(positionName));
                    } else {
                        System.out.println("Error: Job position not found.");
                    }
                    break;

                case "next":
                    if (jobQueue.isEmpty()) {
                        System.out.println("No job positions in the queue.");
                    } else {
                        System.out.println("Next job position to be filled: " + jobQueue.poll());
                    }
                    break;

                case "show":
                    if (jobQueue.isEmpty()) {
                        System.out.println("No job positions in the queue.");
                    } else {
                        System.out.println("Job positions in the queue:");
                        for (JobPosition job : jobQueue) {
                            System.out.println(job);
                        }
                    }
                    break;

                case "exit":
                    System.out.println("Exiting Job Position Manager.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Unknown command. Try again.");
            }
        }
    }
}