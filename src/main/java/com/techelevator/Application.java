package com.techelevator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Application {

    private List<Department> departments = new ArrayList<Department>();
    private List<Employee> employees = new ArrayList<>();
    private Map<String, Project> projects = new HashMap<>();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();

    }

    private void run() {
        // create some departments

        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();
    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
       Department department1 =  new Department(1, "Marketing");
       Department department2 = new Department(2, "Sales");
       Department department3 = new Department(3, "Engineering");
       departments.add(department1);
       departments.add(department2);
       departments.add(department3);
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");

        for (Department name : departments) {
            System.out.println(name.getName());
        }

    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        LocalDate today = LocalDate.now();
        Employee dean = new Employee();
        dean.setDepartment(getDepartmentByName("Engineering"));
        dean.setEmployeeId(1);
        dean.setFirstName("Dean");
        dean.setLastName("Johnson");
        dean.setEmail("djohnson@teams.com");
        dean.setHireDate(today);

        Employee angie = new Employee(2, "Angie",
                "Smith", "asmith@teams.com",
                getDepartmentByName("Engineering"), today);

        Employee margaret = new Employee(3, "Margaret",
                "Thompson", "mthompson@teams.com",
                getDepartmentByName("Marketing"), today);

        angie.raiseSalary(.10);

        employees.add(dean);
        employees.add(angie);
        employees.add(margaret);
    }

    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");

        NumberFormat currency = NumberFormat.getCurrencyInstance();


        for (Employee person: employees) {
            System.out.println(person.getFullName() + " (" + currency.format(person.getSalary())
                    + ") " + person.getDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {
        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = startDate.plusDays(30);
        Project teamsProject = new Project("TEams", "Project Management Software",
                startDate, dueDate);
        List<Employee> engineers = new ArrayList<>();

        for (Employee e: employees) {
            if (e.getDepartment().getName().equals("Engineering")) {
                engineers.add(e);
            }
        }

        teamsProject.setTeamMembers(engineers);
        projects.put(teamsProject.getName(), teamsProject);
    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        LocalDate startDate = LocalDate.now().plusDays(31);
        LocalDate dueDate = startDate.plusDays(7);
        Project landingPageProject = new Project ("Marketing Landing Page", "Lead Capture Landing Page for Marketing", startDate, dueDate);
        List<Employee> marketers = new ArrayList<>();

        for (Employee e: employees) {
            if(e.getDepartment().getName().equals("Marketing")) {
                marketers.add(e);
            }
        }

        landingPageProject.setTeamMembers(marketers);
        projects.put(landingPageProject.getName(), landingPageProject);
    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");

        for (Map.Entry<String, Project> entry: projects.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getTeamMembers().size());
            System.out.println("Starts on " + entry.getValue().getStartDate());
            System.out.println(("Due on " + entry.getValue().getDueDate()));
        }

    }

    private Department getDepartmentByName(String departmentName) {

        for (Department name : departments) {
           if(name.getName().equals(departmentName)) {
               return name;
           }
        }
        return null;

    }

}
