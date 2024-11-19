package loansystem;

import java.util.Scanner;

public class Payment {
    public void payLoan() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        double rembal = 0;
        String status = "Partially Paid";
        
        System.out.print("\nEnter Loan ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int id2 = sc.nextInt();
        sc.nextLine();
        System.out.print("Payment Date: ");
        String pdate = sc.nextLine();
        System.out.print("Payable Amount: ");
        double payableamt = sc.nextDouble();
        System.out.print("Payment Amount: ");
        double pamt = sc.nextDouble(); 

        rembal = payableamt - pamt;
        
        if (rembal == 0) {
            status = "Paid off"; 
        }

        
        String sql = "INSERT INTO payment (p_lid, p_cid, p_pdate, p_payableamt, p_pamt, p_rembal, p_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, id, id2, pdate, payableamt, pamt, rembal, status);
    }
    
    public void viewPayment() {   
        String qry = "SELECT payment.p_id, customer.c_lname, payment.p_pdate, loan.l_payableamt, payment.p_pamt, payment.p_rembal, payment.p_status "
                + "FROM payment INNER JOIN customer ON customer.c_id = payment.p_cid INNER JOIN loan ON loan.l_id = payment.p_lid";
        String[] hdrs = {"Payment ID", "Customer Name", "Payment Date", "Payable Amount", "Payment Amount", "Remaining Balance", "Payment Status"};
        String[] clms = {"p_id", "c_lname", "p_pdate", "l_payableamt", "p_pamt", "p_rembal", "p_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void viewPayment2() {   
        String qry = "SELECT customer.c_lname, payment.p_pdate, loan.l_payableamt, payment.p_rembal, payment.p_status "
                + " FROM payment INNER JOIN customer ON customer.c_id = payment.p_cid INNER JOIN loan on loan.l_id = payment.p_lid";
        String[] hdrs = {"Customer Name", "Payment Date", "Payable Amount", "Remaining Balance", "Payment Status"};
        String[] clms = {"c_lname", "p_pdate", "l_payableamt", "p_rembal", "p_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updatePayment(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        double pamt;
        
        System.out.print("Enter Payment ID to Update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Payment ID to Update: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT p_id FROM payment WHERE p_id = ?", id)) == 0){
            System.out.println("Payment ID doesn't exist!");
            System.out.print("Enter Payment ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Payment ID again: ");
            }
            id = sc.nextInt();
       }
        sc.nextLine();
        System.out.print("New Payment Date: ");
        String pdate = sc.nextLine();
        System.out.print("New Payment Amount: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid Payment Amount.");
            sc.next();
            System.out.print("Enter Payment Amount again : ");
        }
        pamt = sc.nextInt();
        
        String qry = "UPDATE payment SET p_pdate = ?, p_pamt = ? WHERE p_id = ?";
        conf.updateRecord(qry, pdate, pamt, id);       
    }
    
    public void deletePayment(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter Payment ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Payment ID to Delete: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT p_id FROM payment WHERE p_id = ?", id)) == 0){
            System.out.println("Payment ID doesn't exist!");
            System.out.print("Enter Payment ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Payment ID again: ");
            }
            id = sc.nextInt();
       }
       
        String qry = "DELETE FROM payment WHERE p_id = ?";
        conf.deleteRecord(qry, id);
    }
    
    public void payment(){
    Scanner sc = new Scanner(System.in);
    Payment pay = new Payment();
    Loan ln = new Loan();
    Customer cm = new Customer();
        boolean exit = true;
        int opt;
        
        do{
            System.out.println("\n---------- WELCOME TO THE SYSTEM ---------");
            System.out.println("1. PAY LOAN");
            System.out.println("2. VIEW PAYMENT DETAILS AND REPORT");
            System.out.println("3. UPDATE PAYMENT");
            System.out.println("4. DELETE PAYMENT RECORD");
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
                    System.out.println("\n---- LOAN REPORT ----");
                    ln.viewLoan();
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cm.viewCustomer();
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment2();
                    pay.payLoan();
                break;
                    
                case 2:
                    System.out.println("\n---- PAYMENT DETAILS ----");
                    pay.viewPayment();
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment2();
                break;
                
                case 3:
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment();
                    pay.updatePayment();
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment();
                break;
                
                case 4:
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment();
                    pay.deletePayment();
                    System.out.println("\n---- PAYMENT REPORT ----");
                    pay.viewPayment();
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
