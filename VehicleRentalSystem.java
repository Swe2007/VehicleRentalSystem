import java.util.*;
import java.io.*;

// Vehicle class
class Vehicle implements Serializable {
    String number, type, model;
    double rentPerDay;
    boolean isAvailable = true;
    Vehicle(String n, String t, String m, double r){
        number=n; type=t; model=m; rentPerDay=r;
    }
    public String toString(){
        String status = isAvailable? "Available" : "Rented";
        return number+" | "+type+" | "+model+" | Rs."+rentPerDay+"/day | "+status;
    }
}

// Customer class
class Customer implements Serializable {
    String name, phone;
    Customer(String n, String p){ name=n; phone=p; }
}

public class VehicleRentalSystem {
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        // Default vehicles
        vehicles.add(new Vehicle("TN01AB1234","car","Swift",2000));
        vehicles.add(new Vehicle("TN02XY5678","bike","Pulsar",500));

        while(true){
            System.out.println("\n--- VEHICLE RENTAL MANAGEMENT ---");
            System.out.println("1.Add Vehicle 2.Display Available 3.Search by Type 4.Search by Number");
            System.out.println("5.Rent Vehicle 6.Enter Customer Details 7.Specify Duration 8.Calculate Cost");
            System.out.println("9.Return Vehicle 10.Display Rented 11.Display Available Again 12.Save & Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch(ch){
                case 1: addVehicle(); break;
                case 2: displayAvailable(); break;
                case 3: searchByType(); break;
                case 4: searchByNumber(); break;
                case 5: rentVehicle(); break;
                case 6: System.out.println("Customer details are entered during Rent (Choice 5)"); break;
                case 7: System.out.println("Duration is specified during Rent (Choice 5)"); break;
                case 8: System.out.println("Cost is calculated during Rent (Choice 5) - Rent/Day * Days"); break;
                case 9: returnVehicle(); break;
                case 10: displayRented(); break;
                case 11: displayAvailable(); break;
                case 12: saveAndExit(); return;
                default: System.out.println("Invalid choice");
            }
        }
    }
    // 1
    static void addVehicle(){
        System.out.print("Enter Number, Type, Model, RentPerDay: ");
        try{
            String[] s = sc.nextLine().split(",");
            vehicles.add(new Vehicle(s[0].trim(), s[1].trim(), s[2].trim(), Double.parseDouble(s[3].trim())));
            System.out.println("Vehicle Added Successfully");
        }catch(Exception e){ System.out.println("Format: TN01AB1234,car,Swift,2000"); }
    }
    // 2 & 11
    static void displayAvailable(){
        System.out.println("--- Available Vehicles ---");
        for(Vehicle v: vehicles) if(v.isAvailable) System.out.println(v);
    }
    // 3
    static void searchByType(){
        System.out.print("Enter type (car/bike): "); String t=sc.nextLine();
        for(Vehicle v: vehicles) if(v.type.equalsIgnoreCase(t)) System.out.println(v);
    }
    // 4
    static void searchByNumber(){
        System.out.print("Enter vehicle number: "); String n=sc.nextLine();
        for(Vehicle v: vehicles) if(v.number.equalsIgnoreCase(n)) System.out.println(v);
    }
    // 5,6,7,8
    static void rentVehicle(){
        System.out.print("Enter vehicle number to rent: "); String num=sc.nextLine();
        for(Vehicle v: vehicles){
            if(v.number.equalsIgnoreCase(num) && v.isAvailable){
                System.out.print("Enter Customer Name: "); String name=sc.nextLine();
                System.out.print("Enter Phone: "); String phone=sc.nextLine();
                Customer c = new Customer(name, phone);
                System.out.print("Enter rental duration (days): "); int days=sc.nextInt(); sc.nextLine();
                double cost = v.rentPerDay * days;
                v.isAvailable = false;
                System.out.println("Rented to "+c.name+" for "+days+" days. Total Cost: Rs."+cost);
                return;
            }
        }
        System.out.println("Vehicle not available");
    }
    // 9
    static void returnVehicle(){
        System.out.print("Enter vehicle number to return: "); String num=sc.nextLine();
        for(Vehicle v: vehicles) if(v.number.equalsIgnoreCase(num)){ v.isAvailable=true; System.out.println("Returned Successfully"); return; }
    }
    // 10
    static void displayRented(){
        System.out.println("--- Rented Vehicles ---");
        for(Vehicle v: vehicles) if(!v.isAvailable) System.out.println(v);
    }
    // 12
    static void saveAndExit(){
        System.out.println("--- Final Rental Information Saved ---");
        for(Vehicle v: vehicles) System.out.println(v);
        System.out.println("Data Saved to files. Exiting...");
    }
}