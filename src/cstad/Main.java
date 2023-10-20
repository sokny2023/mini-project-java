package cstad;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<Product> products = new ArrayList<>();
    static MenuLogo menu = new MenuLogo();
    static Scanner scanner = new Scanner(System.in);
    static double setRow=6;
    static double currentPage=1;
    static  double totalPage = 0;
    static double totalpage1;
    static double totalRecord;
    static double lastPage;
    static double nextPage;

    static void calculate(){
        totalRecord = products.size();
        totalPage = (totalRecord/setRow);
        totalpage1 = Math.ceil(totalPage);
    }
    static void option(){
        System.out.print(" Command -> ");
        String options = scanner.next();
        switch (options) {
            case "*" ->  displayall();
            case "W", "w" -> input();
            case "W#", "w#" -> inputSortcut();
            case "R", "r" ->  read(products);
            case "R#proId", "r#proId" -> readShortcut();
            case "U", "u" -> update(products);
            case "D", "d" -> delete(products);
            case "D#proId", "d#proid" -> {
                System.out.println("Delete sortcut");
            }
            case "F", "f" -> {
                System.out.println("First page");
            }
            case "P", "p" -> {
                System.out.println("Previous");
            }
            case "N", "n" ->  nextPage();
            case "L", "l" ->  lastPage();
            case "S", "s" ->  search(products);
            case "SE", "se", "Se", "sE" ->  setRow();
            case "H", "h" ->  menu.help();
            case "E", "e" -> {
                System.out.println("Good bye teacher...!");
                System.exit(0);
            }
            default -> System.out.println("Invalid...!");
        }
    }

    static void defaultValue() {
        System.out.println("Write");
        products.add(new Product(1, "Krud", 2500.00, 5, "13-10-2023"));
        products.add(new Product(2, "Hanuman", 2200.00, 25, "13-10-2023"));
        products.add(new Product(3, "Tiger", 2400.00, 10, "13-10-2023"));
        products.add(new Product(4, "Vatthanak", 2600.00, 12, "14-10-2023"));
        products.add(new Product(5, "Anchor", 2600.00, 50, "18-10-2023"));
    }

    static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product Id : ");
        int id = scanner.nextInt();
        System.out.print("Enter Product's Name : ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Product's Price : ");
        double unitPrice = scanner.nextDouble();
        System.out.print("Enter Product's Qty : ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date : ");
        String date = scanner.nextLine();

        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.setColumnWidth(0, 20,75);
        table.addCell("Id            : "+String.valueOf(id));
        table.addCell("Name          : "+name);
        table.addCell("unitPrice     : "+String.valueOf(unitPrice));
        table.addCell("Qty           : "+String.valueOf(qty));
        table.addCell("Imported date : "+date);
        System.out.println(table.render());

        System.out.println("Are you sure to add this record? [Y/y] or [N/n]: ");
        String ch = scanner.nextLine();
        if (ch.equals("Y") || ch.equals("y")) {
            Product product = new Product(id, name, unitPrice, qty, date);
            products.add(product);
            System.out.println("Write successfully");
        } else {
            return;
        }
    }
    static void inputSortcut() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press w->#prodname-unitprice-qty: shortcut for adding a new product");
        String input = scanner.nextLine();

        if (input.startsWith("w->")) {
            String[] parts = input.substring(3).split("-");
            if (parts.length == 4) {
                String name = parts[0];
                double unitPrice = Double.parseDouble(parts[1]);
                int qty = Integer.parseInt(parts[2]);
                String date = parts[3];

                Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
                table.setColumnWidth(0, 20, 75);
                table.addCell("Name          : " + name);
                table.addCell("unitPrice     : " + String.valueOf(unitPrice));
                table.addCell("Qty           : " + String.valueOf(qty));
                table.addCell("Imported date : " + date);
                System.out.println(table.render());

                System.out.println("Are you sure you want to add this record? [Y/y] or [N/n]: ");
                String ch = scanner.nextLine();
                if (ch.equalsIgnoreCase("Y")) {
                    int id = generateProductId(); // Assuming you have a method to generate a unique product ID
                    Product product = new Product(id, name, unitPrice, qty, date);
                    products.add(product);
                    System.out.println("Write successful");
                } else {
                    System.out.println("Record not added");
                }
            } else {
                System.out.println("Invalid input format");
            }
        }
    }
    private static int generateProductId() {
        Random random = new Random();
        int minId = 1000;
        int maxId = 9999;
        return random.nextInt(maxId - minId + 1) + minId;
    }

    private static void displayall() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.setColumnWidth(0, 15, 30);
        table.setColumnWidth(1, 15, 30);
        table.setColumnWidth(2, 15, 30);
        table.setColumnWidth(3, 15, 30);
        table.setColumnWidth(4, 15, 30);
        table.addCell("id");
        table.addCell("Name");
        table.addCell("unitPrice");
        table.addCell("Qty");
        table.addCell("Imported date");

        calculate();
        for (int i = 0; i < products.size()/totalpage1; i++) {
            Product pro = products.get(i);
            table.addCell(String.valueOf(pro.getId()));
            table.addCell(pro.getName());
            table.addCell(String.valueOf(pro.getUnitPrice()));
            table.addCell(String.valueOf(pro.getQty()));
            table.addCell(pro.getDate());
        }
        System.out.println(table.render());

        // pagination
        System.out.println("+" + "-".repeat(89) + "+");
        System.out.println(" Page " + (int)currentPage +" of "+ (int)totalpage1  + " ".repeat(62) + "Total record: " + (int)totalRecord);
        System.out.println("+" + "-".repeat(89) + "+");
    }

    static void nextPage(){
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.setColumnWidth(0, 15, 30);
        table.setColumnWidth(1, 15, 30);
        table.setColumnWidth(2, 15, 30);
        table.setColumnWidth(3, 15, 30);
        table.setColumnWidth(4, 15, 30);
        table.addCell("id");
        table.addCell("Name");
        table.addCell("unitPrice");
        table.addCell("Qty");
        table.addCell("Imported date");

        calculate();
        nextPage = setRow;
        currentPage+=1;
        for (int i = (int)nextPage; i < (products.size()/currentPage)*setRow; i++) {
            Product pro = products.get(i);
            table.addCell(String.valueOf(pro.getId()));
            table.addCell(pro.getName());
            table.addCell(String.valueOf(pro.getUnitPrice()));
            table.addCell(String.valueOf(pro.getQty()));
            table.addCell(pro.getDate());
        }
        System.out.println(table.render());

        // pagination
        System.out.println("+" + "-".repeat(89) + "+");
        System.out.println(" Page " +(int)currentPage+ " of "+ (int)totalpage1  + " ".repeat(62) + "Total record: " + (int)totalRecord);
        System.out.println("+" + "-".repeat(89) + "+");
    }
    static void lastPage(){
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        table.setColumnWidth(0, 15, 30);
        table.setColumnWidth(1, 15, 30);
        table.setColumnWidth(2, 15, 30);
        table.setColumnWidth(3, 15, 30);
        table.setColumnWidth(4, 15, 30);
        table.addCell("id");
        table.addCell("Name");
        table.addCell("unitPrice");
        table.addCell("Qty");
        table.addCell("Imported date");

        calculate();
        totalRecord = products.size();
        totalPage = (totalRecord/setRow);
        totalpage1 = Math.ceil(totalPage);
        lastPage = (totalpage1);
        for (int i = (int)setRow; i < products.size(); i++) {
            Product pro = products.get(i);
            table.addCell(String.valueOf(pro.getId()));
            table.addCell(pro.getName());
            table.addCell(String.valueOf(pro.getUnitPrice()));
            table.addCell(String.valueOf(pro.getQty()));
            table.addCell(pro.getDate());
        }
        System.out.println(table.render());

        // pagination
        System.out.println("+" + "-".repeat(89) + "+");
        System.out.println(" Page " + (int)lastPage +" of "+ (int)totalpage1  + " ".repeat(62) + "Total record: " + (int)totalRecord);
        System.out.println("+" + "-".repeat(89) + "+");
    }
    static void setRow(){
        System.out.print("Enter row for display: ");
        double row = scanner.nextInt();
        setRow = row;
        System.out.println("Set row Successfully...!");
    }
    static void search(List<Product> products){
        boolean isFound = false;
        scanner.nextLine();
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();
        for (Product productSearch : products){
            if(name.equals(productSearch.getName())){
                System.out.println(productSearch);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println(" Not found");
        }

    }
    static void read(List<Product> products){
        boolean isRead = false;
        System.out.print("Read by Id : ");
        int readId = scanner.nextInt();
        for (Product productRead : products){
            if(readId == productRead.getId()){
                Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
                table.setColumnWidth(0, 20,75);
                table.addCell("Id            : "+productRead.getId());
                table.addCell("Name          : "+productRead.getName());
                table.addCell("unitPrice     : "+productRead.getUnitPrice());
                table.addCell("Qty           : "+productRead.getQty());
                table.addCell("Imported date : "+productRead.getDate());
                System.out.println(table.render());
                isRead = true;
            }
        }
        if (!isRead) System.out.println("Not found, can't read...!");
    }
    static void readShortcut() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        String input = scanner.nextLine();

        if (input.startsWith("#")) {
            try {
                int productId = Integer.parseInt(input.substring(1));
                read(productId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid product ID format");
            }
        } else {
            System.out.println("Invalid input format");
        }
    }
    static void read(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Unit Price: " + product.getUnitPrice());
                System.out.println("Quantity: " + product.getQty());
                System.out.println("Imported Date: " + product.getDate());
                return;
            }
        }

        // If the product with the given ID is not found
        System.out.println("Product not found");
    }

    static void update(List<Product> products){
        System.out.println("Enter Id to update: ");
        int idUpdate = scanner.nextInt();
        boolean isUpdate = false;
        for (Product productRead : products){
            if(idUpdate == productRead.getId()){
                Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
                table.setColumnWidth(0, 20,75);
                table.addCell("Id            : "+productRead.getId());
                table.addCell("Name          : "+productRead.getName());
                table.addCell("unitPrice     : "+productRead.getUnitPrice());
                table.addCell("Qty           : "+productRead.getQty());
                table.addCell("Imported date : "+productRead.getDate());
                System.out.println(table.render());
                System.out.println("What do you want to update: ");
                Table table1 = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
                table1.setColumnWidth(0, 20,120);
                table1.addCell("1.All\t 2.Name\t 3.Quantity\t 4.Unit price\t 5.Back to menu");
                System.out.println(table1.render());
                System.out.print("chose(1-5): ");
                int ch = scanner.nextInt();
                switch (ch){
                    case 1->{
                        System.out.print("Enter new Id :");
                        productRead.setId(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter new Name :");
                        productRead.setName(scanner.nextLine());
                        System.out.print("Enter new Unitprice :");
                        productRead.setUnitPrice(scanner.nextDouble());
                        System.out.print("Enter new Qty :");
                        productRead.setQty(scanner.nextInt());
                        System.out.print("Enter new Date :");
                        productRead.setDate(scanner.nextLine());
                        isUpdate = true;
                        System.out.println("Update all success...!");
                    }
                    case 2->{
                        scanner.nextLine();
                        System.out.print("Enter new Name :");
                        productRead.setName(scanner.nextLine());
                        isUpdate = true;
                        System.out.println("Update Name success...!");
                    }
                    case 3->{
                        System.out.print("Enter new Qty :");
                        productRead.setQty(scanner.nextInt());
                        isUpdate = true;
                        System.out.println("Update Qty success...!");
                    }
                    case 4->{
                        scanner.nextLine();
                        System.out.print("Enter new Unitprice :");
                        productRead.setUnitPrice(scanner.nextDouble());
                        isUpdate = true;
                        System.out.println("Update Unitprice success...!");
                    }
                    case 5 -> {
                        return;
                    }
                }
            }
        }
        if (!isUpdate) System.out.println("Not found, can't update...!");
    }
    static void delete(List<Product> products){
        boolean isDelete = false;
        System.out.print("Enter Id to delete: ");
        int idDelete = scanner.nextInt();
        for (var product : products){
            if(idDelete == product.getId()){
                Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
                table.setColumnWidth(0, 20,75);
                table.addCell("Id            : "+product.getId());
                table.addCell("Name          : "+product.getName());
                table.addCell("unitPrice     : "+product.getUnitPrice());
                table.addCell("Qty           : "+product.getQty());
                table.addCell("Imported date : "+product.getDate());
                System.out.println(table.render());
                isDelete = true;
            }
        }
        if (!isDelete) {
            System.out.println("Not found, can't delete...!");
        }
        else {
            scanner.nextLine();
            System.out.println("Do you want to delete [Y/y] or [N/n]: ");
            String op = scanner.nextLine();
            if (op.equals("Y") || op.equals("y")){
                products.removeIf(pro -> pro.getId() == idDelete);
            }
        }
    }

    // main
    public static void main(String[] args){
        defaultValue();
        menu.logo();
        do{
            menu.menu();
            option();
        }while (true);
    }
}