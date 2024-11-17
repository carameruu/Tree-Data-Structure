import java.util.*;

class DecisionTreeNode {
    String questionOrDecision; // The question or decision at this node
    List<Branch> branches; // List of branches from this node

    public DecisionTreeNode(String questionOrDecision) {
        this.questionOrDecision = questionOrDecision;
        this.branches = new ArrayList<>();
    }

    public void addBranch(String answer, DecisionTreeNode child) {
        branches.add(new Branch(answer, child));
    }
}

class Branch {
    String answer; // Label for this branch (e.g., "Yes", "No")
    DecisionTreeNode child; // Child node for this branch

    public Branch(String answer, DecisionTreeNode child) {
        this.answer = answer;
        this.child = child;
    }
}

public class DecisionMaker {
    public static void main(String[] args) {
        // Root of the decision tree
        DecisionTreeNode root = new DecisionTreeNode("Do you want to pursue your dream course or a practical course?");

        // Dream Course branch
        DecisionTreeNode dreamCourse = new DecisionTreeNode("Are you financially stable to support it?");
        dreamCourse.addBranch("Yes", new DecisionTreeNode("Pursue your dream course."));
        dreamCourse.addBranch("No", new DecisionTreeNode("Consider saving money first."));
        root.addBranch("Dream Course", dreamCourse);

        // Practical Course branch
        DecisionTreeNode practicalCourse = new DecisionTreeNode("Does it align with your long-term goals?");
        practicalCourse.addBranch("Yes", new DecisionTreeNode("Proceed with the practical course."));
        practicalCourse.addBranch("No", new DecisionTreeNode("Reevaluate your priorities."));
        root.addBranch("Practical Course", practicalCourse);

        // Evaluate the decision tree
        evaluateTree(root);
    }

    private static void evaluateTree(DecisionTreeNode root) {
        Scanner scanner = new Scanner(System.in);
        DecisionTreeNode currentNode = root;

        while (!currentNode.branches.isEmpty()) {
            System.out.println(currentNode.questionOrDecision);
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine();

            DecisionTreeNode nextNode = null;
            for (Branch branch : currentNode.branches) {
                if (branch.answer.equalsIgnoreCase(answer)) {
                    nextNode = branch.child;
                    break;
                }
            }

            if (nextNode == null) {
                System.out.println("Invalid answer. Please try again.");
            } else {
                currentNode = nextNode;
            }
        }

        // Decision reached
        System.out.println("Decision reached: " + currentNode.questionOrDecision);
    }
}
