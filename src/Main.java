import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList myArrList = new ArrayList();
        Scanner in = new Scanner(System.in);
        String instructions =
                "A – Add an item to the list\n" +
                        "D – Delete an item from the list\n" +
                        "I – Insert an item into the list\n" +
                        "P – Print (i.e. display) the list\n" +
                        "H – Help (will display these options again)\n" +
                        "C – Clear (clears the screen)\n" +
                        "Q – Quit the program (This should do an are you sure? type query before exiting.)";

        help(instructions);
        while (true) {
            String input = SafeInput.getRegExString(in, " ", "[AaDdIiPpHhCcQq]");

            if (input.equalsIgnoreCase("a")) {
                addItem(in, myArrList);
            } else if (input.equalsIgnoreCase("d")) {
                deleteItem(in, myArrList);
            } else if (input.equalsIgnoreCase("i")) {
                insertItem(in, myArrList);
            } else if (input.equalsIgnoreCase("p")) {
                printList(myArrList);
            } else if (input.equalsIgnoreCase("h")) {
                help(instructions);
            } else if (input.equalsIgnoreCase("c")) {
                clearScreen();
            } else if (input.equalsIgnoreCase("q")) {
                if (quit(in)) {
                    break;
                }
            }
        }
    }

    private static void addItem(Scanner in, ArrayList list) {
        System.out.println("Please type what you would like to add: ");
        if (in.hasNext()) {
            String item = in.next();
            list.add(item);
            System.out.printf("\nSuccessfully added '%s'\n", item);
        }
    }

    private static void deleteItem(Scanner in, ArrayList list) {
        if (list.size() > 0) {
            int listLength = list.size() - 1;
            String message = "Please enter the item index between 0 and " + listLength;
            int indexInput = SafeInput.getRangedInt(in, message, 0, listLength);
            String deletedItem = list.get(indexInput).toString();
            list.remove(indexInput);
            System.out.printf("\nSuccessfully deleted '%s'\n", deletedItem);
        } else {
            System.out.println("You do not have items on your list that you can delete");
        }
    }

    private static void insertItem(Scanner in, ArrayList list) {
        System.out.println("Please type what you would like to insert: ");
        if (in.hasNext()) {
            String item = in.next();
            int listLength = list.size();
            String message = "Please enter the index where you would like to insert your item, between 0 and " + listLength;
            int indexInput = SafeInput.getRangedInt(in, message, 0, listLength);
            list.add(indexInput, item);
            System.out.printf("\nSuccessfully inserted '%s'\n", list.get(indexInput));
        }
    }

    private static void printList(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d: %s\n", i, list.get(i));
        }
    }

    private static boolean quit(Scanner in) {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }

    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void help(String message) {
        System.out.print(message);
    }
}