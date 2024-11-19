package loansystem;

import java.util.Scanner;

public class Loansystem {
        public static void main(String[] args) {
            boolean exit = true;
            int opt;
       
    do{    
        Scanner sc = new Scanner (System.in);
        System.out.println("\n---------- WELCOME TO LOAN SYSTEM ----------");
        System.out.println("1. CUSTOMER");
        System.out.println("2. LOAN");
        System.out.println("3. PAYMENT");
        System.out.println("4. EXIT");
        System.out.println("-------------------------------------------");
        System.out.print("Enter Option: ");
        while (!sc.hasNextInt()) {
                System.out.println("Invalid Option. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Option: ");
            }
            opt = sc.nextInt();
        
        switch(opt){
            case 1: 
                Customer ctm = new Customer();
                ctm.customer();
                break;
                
            case 2:
                Loan ln = new Loan();
                ln.loan();
                break;
        
            case 3:
                Payment py = new Payment();
                py.payment();
                break;
                
            case 4:
                System.out.print("Exit Selected type 'yes' to continue: ");
                String resp = sc.next();
                if(resp.equalsIgnoreCase("yes")){
                exit = false;
                }
                break;
        }
    }while(exit);
        System.out.println("Thank you for using the System!");
    } 
}
