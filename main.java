import java.util.Scanner;

class Node { int coefficient; int exponent; Node next;

public Node(int coefficient, int exponent) {
    this.coefficient = coefficient;
    this.exponent = exponent;
    this.next = null;
}
}

class Polynomial { Node head; int degree; int numTerms;

public Polynomial() {
    head = null;
    degree = 0;
    numTerms = 0;
}

// Add a term to the polynomial
public void addTerm(int coefficient, int exponent) {
    if (coefficient == 0) return;

    Node newNode = new Node(coefficient, exponent);
    if (head == null || exponent > head.exponent) {
        newNode.next = head;
        head = newNode;
    } else {
        Node current = head;
        while (current.next != null && current.next.exponent >= exponent) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    degree = Math.max(degree, exponent);
    numTerms++;
}

// Add two polynomials to get a new polynomial
public Polynomial add(Polynomial other) {
    Polynomial result = new Polynomial();
    Node term1 = this.head;
    Node term2 = other.head;

    while (term1 != null || term2 != null) {
        if (term1 == null) {
            result.addTerm(term2.coefficient, term2.exponent);
            term2 = term2.next;
        } else if (term2 == null) {
            result.addTerm(term1.coefficient, term1.exponent);
            term1 = term1.next;
        } else {
            if (term1.exponent > term2.exponent) {
                result.addTerm(term1.coefficient, term1.exponent);
                term1 = term1.next;
            } else if (term1.exponent < term2.exponent) {
                result.addTerm(term2.coefficient, term2.exponent);
                term2 = term2.next;
            } else {
                int sum = term1.coefficient + term2.coefficient;
                result.addTerm(sum, term1.exponent);
                term1 = term1.next;
                term2 = term2.next;
            }
        }
    }

    return result;
}

// Subtract two polynomials
public Polynomial subtract(Polynomial other) {
    Polynomial result = new Polynomial();
    Node term1 = this.head;
    Node term2 = other.head;

    while (term1 != null || term2 != null) {
        if (term1 == null) {
            result.addTerm(-term2.coefficient, term2.exponent);
            term2 = term2.next;
        } else if (term2 == null) {
            result.addTerm(term1.coefficient, term1.exponent);
            term1 = term1.next;
        } else {
            if (term1.exponent > term2.exponent) {
                result.addTerm(term1.coefficient, term1.exponent);
                term1 = term1.next;
            } else if (term1.exponent < term2.exponent) {
                result.addTerm(-term2.coefficient, term2.exponent);
                term2 = term2.next;
            } else {
                int diff = term1.coefficient - term2.coefficient;
                result.addTerm(diff, term1.exponent);
                term1 = term1.next;
                term2 = term2.next;
            }
        }
    }

    return result;
}

// Multiply two polynomials
public Polynomial multiply(Polynomial other) {
    Polynomial result = new Polynomial();
    Node term1 = this.head;

    while (term1 != null) {
        Node term2 = other.head;
        while (term2 != null) {
            int coeff = term1.coefficient * term2.coefficient;
            int exp = term1.exponent + term2.exponent;
            result.addTerm(coeff, exp);
            term2 = term2.next;
        }
        term1 = term1.next;
    }

    return result;
}

// Display the polynomial
public void display() {
    Node current = head;
    while (current != null) {
        System.out.print(current.coefficient + "x^" + current.exponent);
        if (current.next != null) {
            System.out.print(" + ");
        }
        current = current.next;
    }
    System.out.println();
}
}

class PolynomialCalculator { public static void main(String[] args) { Scanner scanner = new Scanner(System.in);

    // Input for the first polynomial
    System.out.println("Enter the first polynomial:");
    Polynomial poly1 = new Polynomial();
    while (true) {
        System.out.print("Enter coefficient (or 0 to finish): ");
        int coefficient = scanner.nextInt();
        if (coefficient == 0) break;
        System.out.print("Enter exponent: ");
        int exponent = scanner.nextInt();
        poly1.addTerm(coefficient, exponent);
    }

    // Input for the second polynomial
    System.out.println("Enter the second polynomial:");
    Polynomial poly2 = new Polynomial();
    while (true) {
        System.out.print("Enter coefficient (or 0 to finish): ");
        int coefficient = scanner.nextInt();
        if (coefficient == 0) break;
        System.out.print("Enter exponent: ");
        int exponent = scanner.nextInt();
        poly2.addTerm(coefficient, exponent);
    }

    System.out.println("Polynomial 1:");
    poly1.display();

    System.out.println("Polynomial 2:");
    poly2.display();

    Polynomial additionResult = poly1.add(poly2);
    System.out.println("Addition Result:");
    additionResult.display();

    Polynomial subtractionResult = poly1.subtract(poly2);
    System.out.println("Subtraction Result:");
    subtractionResult.display();

    Polynomial multiplicationResult = poly1.multiply(poly2);
    System.out.println("Multiplication Result:");
    multiplicationResult.display();

    scanner.close();
}
}
