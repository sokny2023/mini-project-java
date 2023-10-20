package cstad;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class MenuLogo {
     void logo(){
         String ASCII = "\t\t\t ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                 "\t\t\t██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                 "\t\t\t██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                 "\t\t\t██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                 "\t\t\t╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                 "\t\t\t ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" ;
         System.out.println(ASCII);
     }
     void menu(){
         String text = "stock management system";
         String text1 = text.toUpperCase();
         System.out.println(" ".repeat(25)+text.toUpperCase());
         Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
         table.setColumnWidth(0,15,30);
         table.setColumnWidth(1,15,30);
         table.setColumnWidth(2,15,30);
         table.setColumnWidth(3,15,30);
         table.setColumnWidth(4,15,30);
         table.setColumnWidth(5,15,30);
         table.setColumnWidth(6,15,30);

         table.addCell(" ".repeat(3)+"*)Display");
         table.addCell(" ".repeat(3)+"| W)rite");
         table.addCell(" ".repeat(3)+"| R)ead");
         table.addCell(" ".repeat(3)+"| U)pdate");
         table.addCell(" ".repeat(3)+"| D)elete");
         table.addCell(" ".repeat(3)+"| F)irst ");
         table.addCell(" ".repeat(3)+"| P)revious");
         table.addCell(" ".repeat(3)+"| N)ext");
         table.addCell(" ".repeat(3)+"| L)ast");
         table.addCell(" ".repeat(3)+"| S)earch");
         table.addCell(" ".repeat(3)+"| G)oto");
         table.addCell(" ".repeat(3)+"| S)et row");
         table.addCell(" ".repeat(3)+"| H)Help");
         table.addCell(" ".repeat(3)+"| E)xit");
         System.out.println(table.render());
     }

     void help(){
         System.out.println("+"+"-".repeat(75)+"+");
         System.out.println("! 1.   Press  * :  Display all record of products");
         System.out.println("! 2    Press  w :  Add new product");
         System.out.println("       Press  w->#prodname-unitprice-qty: sortcut for add new product");
         System.out.println("! 3.   Press  r : Read Content andy content");
         System.out.println("       Press  r#proId: Sortcut for rad product by Id");
         System.out.println("       Press  u : Update data");
         System.out.println("! 5.   Press  d : Delete Data");
         System.out.println("       Press  d#proId: Delete product by Id");
         System.out.println("! 6.   Press  f : Display First page");
         System.out.println("! 7.   Press  p : Display Previous page");
         System.out.println("! 8.   Press  n : Display Next page");
         System.out.println("! 9.   Press  l : Display Last page");
         System.out.println("! 10.  Press  s : Search product by name");
         System.out.println("! 11.  Press  se : Set row");
         System.out.println("! 12.  Press  h : Help");
         System.out.println("+"+"-".repeat(75)+"+");
     }
}