import java.util.*;

class Main {
    public static void main(String[] args) {
        String string;
        String key;
        String answer;
        String encrypted;
        int check = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome! This program will encrypt any string utilizing the Playfair cipher.");

        while (true)
        {
            while (true)
            {
                //If it's the first time running, run one-time text
                if (check == 0)
                {
                    System.out.print("Would you like to start? (Y/N) ");
                }
                else
                {
                    System.out.print("Would you like to try again? (Y/N) ");
                }

                answer = input.nextLine();

                //check for valid input; if yes, --> next step. if no, stop program
                if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes"))
                {
                    break;
                }
                else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No"))
                {
                    System.exit(0);
                }
                else
                {
                    System.out.println("\nPlease enter a valid input.\n");
                }
            }

            //if first time running, print notice
            if (check == 0)
            {
                System.out.println("\nBefore we start, you should know in this specific case, all \"j\"s will be replaced by \"i\"s, and all null values will be set as \"x\"s. Accepted values for the string and key are letters only; please no numbers, spaces or special characters.");
            }

            while (true)
            {
                //get string
                System.out.print("\n\nPlease enter a string to be encrypted: ");
                string = input.nextLine();

                //get key
                System.out.print("\nPlease enter a key to encrypt the string: ");
                key = input.nextLine();
                
                //run method
                Cipher test = new Cipher(string, key);
                encrypted = test.playfair();

                if (encrypted.equals("1"))
                {
                    System.out.println("\nPlease enter valid string, and/or key values");
                }
                else
                {
                    System.out.println("\nYour encrypted string is now " + encrypted + "\n\n");
                    break;
                }
            }
            check = 1;
        }
    }
}