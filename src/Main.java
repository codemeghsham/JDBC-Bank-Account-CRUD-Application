import  java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

        try {
        int MenuChoice = 1;
        do {
        System.out.println("++++++++ MENU +++++++");
        System.out.println("1. Create Customer");
        System.out.println("2. Show All Customers");
        System.out.println("3. Show Bank Account Type");
        System.out.println("4. Show Customer By ID");
        System.out.println("5. Update Customer Balance");
        System.out.println("6. Delete Customer Record");

        System.out.print("Enter your choice ==> ");
        int optionChoice = sc.nextInt();sc.nextLine();

        switch (optionChoice){
            case 1:
            {   String Q = "INSERT INTO account (accFirstName, accLastName, accType)VALUES (?,?,?)";

                PreparedStatement ps = con.prepareStatement(Q);

                System.out.print("Enter First Name : ");
                String accFirstName =sc.nextLine();
                ps.setString(1, accFirstName );

                System.out.print("Enter Last Name : ");
                String accLastName =sc.nextLine();
                ps.setString(2, accLastName );

                System.out.println("Enter Account Type : ");
                System.out.print("Press 1 for Checking Salary Account \nPress 2 for Checking Savings Account \nEnter Choice :");
                int accChoice =sc.nextInt();
                String accType = accChoice==1?"Salary":"Savings";
                ps.setString(3, accType );

                int QueryStatus = ps.executeUpdate();
                System.out.println(QueryStatus);
            }
            break;

            case 2:
            {
                String Q = "SELECT * FROM account";
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(Q);

                System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", "accNumber", "accFirstName", "accLastName", "accType", "accBalance");
                System.out.println("--------------------------------------------------------------------------------------------------------");

                while (rs.next()){
                    int accNumber = rs.getInt(1);
                    String accFirstName = rs.getString(2);
                    String accLastName = rs.getString(3);
                    String accType = rs.getString(4);
                    int accBalance = rs.getInt(5);

                    System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", accNumber, accFirstName, accLastName, accType, accBalance);

                }

            }
            break;

            case 3: {
                System.out.print("Press 1 for Showing Salary Account Only \nPress 2 for Showing Savings Account Only \nEnter Choice :");
                int accChoice =sc.nextInt();
                String accTypeName = accChoice==1?"Salary":"Savings";

                String Q = "SELECT * FROM account WHERE accType=?";
                PreparedStatement ps = con.prepareStatement(Q);
                ps.setString(1,accTypeName);
                ResultSet rs = ps.executeQuery();

                System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", "accNumber", "accFirstName", "accLastName", "accType", "accBalance");
                System.out.println("--------------------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    int accNumber = rs.getInt(1);
                    String accFirstName = rs.getString(2);
                    String accLastName = rs.getString(3);
                    String accType = rs.getString(4);
                    int accBalance = rs.getInt(5);

                    System.out.printf("%-10s %-20s %-20s %-10s %-15s%n", accNumber, accFirstName, accLastName, accType, accBalance);
                }
            }
            break;

            case 4:
            {
                System.out.print("Enter ID to be searched==>");
                int searchID =sc.nextInt();

                String Q = "SELECT * FROM account WHERE accNumber=?";
                PreparedStatement ps = con.prepareStatement(Q);
                ps.setInt(1,searchID);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int accNumber = rs.getInt(1);
                    String accFirstName = rs.getString(2);
                    String accLastName = rs.getString(3);
                    String accType = rs.getString(4);
                    int accBalance = rs.getInt(5);
                    System.out.println("Account Found");
                    System.out.println("accNumber : "+accNumber);
                    System.out.println("accFirstName : "+accFirstName);
                    System.out.println("accLastName : "+accLastName);
                    System.out.println("accType : "+accType);
                    System.out.println("accBalance : "+accBalance);
                    System.out.println();

                }
                else {
                    System.out.println("No record found");
                }

            }
            break;

            case 5: {
                System.out.print("Enter ID to be searched==>");
                int searchID = sc.nextInt();

                String Q = "SELECT * FROM account WHERE accNumber=?";
                PreparedStatement ps = con.prepareStatement(Q);
                ps.setInt(1, searchID);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int accBalance = rs.getInt("accBalance");

                    System.out.println("Press 1 for Credit \nPress 2 for Debit");
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.print("Enter Credit Amount => ");
                        int creditAmount = sc.nextInt();

                        int newBalance = accBalance + creditAmount;

                        String Q2 = "UPDATE account SET accBalance=? WHERE accNumber=?";
                        PreparedStatement ps2 = con.prepareStatement(Q2);
                        ps2.setInt(1, newBalance);
                        ps2.setInt(2, searchID);
                        System.out.println(ps2.executeUpdate() + " row(s) updated.");
                    } else if (choice == 2) {
                        System.out.print("Enter Debit Amount => ");
                        int debitAmount = sc.nextInt();

                        if (debitAmount <= accBalance) {
                            int newBalance = accBalance - debitAmount;

                            String Q2 = "UPDATE account SET accBalance=? WHERE accNumber=?";
                            PreparedStatement ps2 = con.prepareStatement(Q2);
                            ps2.setInt(1, newBalance);
                            ps2.setInt(2, searchID);
                            System.out.println(ps2.executeUpdate() + " row(s) updated.");
                        } else {
                            System.out.println("Insufficient balance for debit.");
                        }
                    }
                } else {
                    System.out.println("Record Not Found");
                }

                break;
            }
            case 6:
            {
                System.out.print("Enter ID to be deleted==>");
                int deleteID =sc.nextInt();

                String Q = "DELETE FROM account WHERE accNumber=?";
                PreparedStatement ps = con.prepareStatement(Q);
                ps.setInt(1,deleteID);
                System.out.println(ps.executeUpdate());

            }
            break;
            default:
                System.out.println("Wrong Choice, Please try again...");

        }
            System.out.println("To continue press 1...");
            MenuChoice =sc.nextInt ();
        }while(MenuChoice==1);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
