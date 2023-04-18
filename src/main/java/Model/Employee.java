package Model;

public class Employee {


    private String employeeFirstName;
    private String employeeLastName;
    private String employeeUsername;
    private String employeePassword;
    private String employeeID;
    private Double employeeBalance;
    private String employeeDepartment;

    public Employee(String employeeFirstName, String employeeLastName, String employeeUsername, String employeePassword, String employeeID, Double employeeBalance, String employeeDepartment) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
        this.employeeID = employeeID;
        this.employeeBalance = employeeBalance;
        this.employeeDepartment = employeeDepartment;
    }

    public Employee(String userName, String password, int employeeID) {
        this.employeeUsername = userName;
        this.employeePassword = password;
        this.employeeID = String.valueOf(employeeID);
    }

    public Employee(String userName, String password, int employeeID, Double employeeBalance) {
        this.employeeUsername = userName;
        this.employeePassword = password;
        this.employeeID = String.valueOf(employeeID);
        this.employeeBalance = employeeBalance;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public Double getEmployeeBalance() {
        return employeeBalance;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }






}
