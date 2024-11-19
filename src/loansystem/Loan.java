package loansystem;

import java.util.Scanner;

public class Loan {
    public void applyLoan(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        double payableAmount, totalInt;
        
        System.out.print("\nEnter Customer ID: ");
        int id = sc.nextInt();
        System.out.print("Loan Amount: ");
        double lamt = sc.nextDouble();
        String rate = "3";
        System.out.print("Loan Term (in months): ");
        int lterm = sc.nextInt();
        
        totalInt = lamt * 0.03 * lterm;
        payableAmount = lamt + totalInt;
        
        String lstatus = "Pending...";
        
        String sql = "INSERT INTO loan (c_id, l_amt, l_rate, l_term, l_payableamt, l_status) VALUES (?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, id, lamt, rate, lterm, payableAmount, lstatus);
    }

    
    public void viewLoan() {   
    String qry = "SELECT loan.l_id, customer.c_lname, loan.l_amt, loan.l_rate, loan.l_term, loan.l_payableamt, loan.l_status "
            + "FROM loan INNER JOIN customer ON customer.c_id = loan.c_id "; 
    String[] hdrs = {"Loan ID", "Customer Name", "Loan Amount", "Loan Interest Rate", "Loan Term In Months", "Payable Amount", "Loan Status"};
    String[] clms = {"l_id", "c_lname", "l_amt", "l_rate", "l_term", "l_payableamt", "l_status"};

    config conf = new config();
    conf.viewRecords(qry, hdrs, clms);
}
    
    public void updateLoan(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter Loan ID to Update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Loan ID to Update: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT l_id FROM loan WHERE l_id = ?", id)) == 0){
            System.out.println("Loan ID doesn't exist!");
            System.out.print("Enter Loan ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Loan ID again: ");
            }
            id = sc.nextInt();
       }
       
        sc.nextLine();
        System.out.print("New Loan Status: ");
        String lstatus = sc.nextLine();
        
        String qry = "UPDATE loan SET l_status = ? WHERE l_id = ?";
        conf.updateRecord(qry, lstatus, id);       
    }
    
    public void deleteLoan(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter Loan ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Loan ID to Delete: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT l_id FROM loan WHERE l_id = ?", id)) == 0){
            System.out.println("Loan ID doesn't exist!");
            System.out.print("Enter Loan ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Loan ID again: ");
            }
            id = sc.nextInt();
       }
       
        String qry = "DELETE FROM loan WHERE l_id = ?";
        conf.deleteRecord(qry, id);
    }
    
    public void loan(){
    Scanner sc = new Scanner(System.in);
    Loan ln = new Loan();
    Customer cm = new Customer();
        boolean exit = true;
        int opt;
        
        do{
            System.out.println("\n---------- WELCOME TO THE SYSTEM ---------");
            System.out.println("1. APPLY LOAN");
            System.out.println("2. VIEW LOAN REPORT");
            System.out.println("3. UPDATE LOAN");
            System.out.println("4. DELETE LOAN");
            System.out.println("5. EXIT");
            System.out.println("------------------------------------------");
            System.out.print("Enter Option: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid Option. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Option: ");
            }
            opt = sc.nextInt();
            
            switch(opt){
                case 1:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cm.viewCustomer();
                    ln.applyLoan();
                break;
                    
                case 2:
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                break;
                
                case 3:
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                    ln.updateLoan();
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                break;
                
                case 4:
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                    ln.deleteLoan();
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                break;
                
                case 5:
                    System.out.print("Do you want to exit? (yes/no): ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Option Error, Try Again");      
            }
        }while(exit);
    }
}
