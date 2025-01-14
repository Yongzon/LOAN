package loansystem;

import java.util.Scanner;

public class Customer { 
    public void addCustomer(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int cn;
        
        System.out.print("\nFirst Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Email: ");
        String eml = sc.nextLine();
        System.out.print("Contact Number: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid Phone Number.");
            sc.next();
            System.out.print("Enter Contact Number again: ");
        }
        cn = sc.nextInt();
        
        String sql = "INSERT INTO customer (c_fname, c_lname, c_add, c_eml, c_cn) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, address, eml, cn);
    }
    
    public void viewCustomer() {   
        String qry = "SELECT * FROM customer ";
        String[] hdrs = {"Customer ID", "First Name", "Last Name", "Address", "Email", "Contact Number"};
        String[] clms = {"c_id", "c_fname", "c_lname", "c_add", "c_eml", "c_cn"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateCustomer(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id, cn;
        
        System.out.print("Enter Customer ID to Update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Customer ID to Update: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT c_id FROM customer WHERE c_id = ?", id)) == 0){
            System.out.println("Customer ID doesn't exist!");
            System.out.print("Enter Customer ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Customer ID again: ");
            }
            id = sc.nextInt();
       }
       
        sc.nextLine();
        System.out.print("New Address: ");
        String address = sc.nextLine();
        System.out.print("New Email: ");
        String eml = sc.nextLine();
        System.out.print("New Contact Number: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid Phone Number.");
            sc.next();
            System.out.print("Enter Contact Number again: ");
        }
        cn = sc.nextInt();
        
        String qry = "UPDATE customer SET c_add = ?, c_eml = ?, c_cn = ? WHERE c_id = ?";
        conf.updateRecord(qry, address, eml, cn, id);       
    }
    
    public void deleteCustomer(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter Customer ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Customer ID to Delete: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT c_id FROM customer WHERE c_id = ?", id)) == 0){
            System.out.println("Customer ID doesn't exist!");
            System.out.print("Enter Customer ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Customer ID again: ");
            }
            id = sc.nextInt();
       }
       
        String qry = "DELETE FROM customer WHERE c_id = ?";
        conf.deleteRecord(qry, id);
    }
    
    public void customer(){
    Scanner sc = new Scanner(System.in);
    Customer cmt = new Customer();
        boolean exit = true;
        int opt;
        
        do{
            System.out.println("\n---------- WELCOME TO THE SYSTEM ---------");
            System.out.println("1. ADD CUSTOMER");
            System.out.println("2. VIEW LIST OF CUSTOMER");
            System.out.println("3. UPDATE CUSTOMER DETAILS");
            System.out.println("4. DELETE CUSTOMER");
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
                   cmt.addCustomer();
                break;
                    
                case 2:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                break;
                
                case 3:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                    cmt.updateCustomer();
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                break;
                
                case 4:
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
                    cmt.deleteCustomer();
                    System.out.println("\n---- CUSTOMER INFORMATION ----");
                    cmt.viewCustomer();
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
