public class Cipher {
    public String PFairCipher[][] = new String[5][5];
    public String alphabetJ[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public String used[] = new String[25];
    public String key;
    public String brokenKey[];
    public String newKey;
    public String fail = "1";
    public int brokenKeyCount = 0;
    public int check = 0;
    public int check2 = 0;
    public int usedLength = 0;

    //setting string
    public String brokenString[];
    public String string;
    public int doubles = 0;
    public int nextIndex = 0;
    public int newStringLen;

    //encrypting
    public int y1;
    public int x1;
    public int y2;
    public int x2;
    public int encrypt;
    public String newString;


    public Cipher(String a, String b)
    {
        key = b.toLowerCase();
        newKey = key + "abcdefghijklmnopqrstuvwxyz";
        brokenKey = newKey.split("(?!^)");

        string = a.toLowerCase();
        brokenString = string.split("(?!^)");
        
    }

    public String playfair()
    {   
        /*General notes for Playfair Cipher
           -all j's will be replaced by i's
           -default/null values will be x
           */

        //changes all j's to i's in key
        for (int a = 0; a < brokenKey.length; a++)
        {
            if (brokenKey[a].equals("j"))
            {
                brokenKey[a] = "i";
            }
        }

        //changes all j's to i's in string
        for (int a = 0; a < brokenString.length; a++)
        {
            if (brokenString[a].equals("j"))
            {
                brokenString[a] = "i";
            }
        }

        //check to see if string is illegal
        for (int a = 0; a < brokenString.length; a++)
        {
            for (int b = 0; b < 25; b++)
            {
                if (brokenString[a].equalsIgnoreCase(alphabetJ[b]))
                {
                    break;
                }
                if (b == 24 && !brokenString[a].equalsIgnoreCase(alphabetJ[b]))
                {
                    check = 1;
                }
            }
        }

        //check to see if key is illegal
        for (int a = 0; a < brokenKey.length; a++)
        {
            for (int b = 0; b < 25; b++)
            {
                if (brokenKey[a].equalsIgnoreCase(alphabetJ[b]))
                {
                    break;
                }
                if (b == 24 && !brokenKey[a].equalsIgnoreCase(alphabetJ[b]))
                {
                    check = 1;
                }
            }
        }

        //if any are illegal, have user re-try
        if (check == 1)
        {
            return fail;
        }


        //next 2 for loops assign elements to the 2D array
        for (int y = 0; y < 5; y++)
        {
            for (int x = 0; x < 5; x++)
            {
                //add key to 2D array
                if (brokenKeyCount < brokenKey.length)
                {
                    check = 0;
                    //check if key letter is already used; if letter is in, keep same index and move to next letter
                    do
                    {
                        if (brokenKeyCount == brokenKey.length)
                        {
                            break;
                        }
                        for (int a = 0; a < used.length; a++)
                        {
                            //as it cycles through used, if letter is in used, go on to next 
                            if (brokenKey[brokenKeyCount].equalsIgnoreCase(used[a]))
                            {
                                brokenKeyCount++;
                                check = 1;
                                break;
                            }
                            else
                            {
                                check = 0;
                            }
                        }
                    } while (check == 1);
                    
                    if (brokenKeyCount == brokenKey.length)
                    {
                        continue;
                    }
                    //Once confirmed letter isn't used, add to array
                    PFairCipher[y][x] = brokenKey[brokenKeyCount];
                    used[usedLength] = brokenKey[brokenKeyCount];
                    usedLength++;
                    brokenKeyCount++;
                }
            }
        }


        //delete later; to print the cryption grid
        // for (int a = 0; a < 5; a++)
        // {
        //     System.out.print("\n");
        //     for (int b = 0; b < 5; b++)
        //     {
        //         System.out.print(PFairCipher[a][b] + ", ");
        //     }
        // }

        //get length of new array, including doubles
        check = 0;
        for (int a = 0; a < brokenString.length; a++)
        {
            if (a == brokenString.length - 1)
            {
                break;
            }
            //start here; if a double is detected, move on to next letter (run else below)
            else if (check == 0)
            {
                if (a % 2 == 0)
                {
                    if (brokenString[a].equalsIgnoreCase(brokenString[a + 1]))
                    {
                        doubles++;
                        check = 1;
                    }
                }
            }
            //if double is detected again, move on to next letter (run else if above)
            else
            {
                if (a % 2 == 1)
                {
                    if (brokenString[a].equalsIgnoreCase(brokenString[a + 1]))
                    {
                        doubles++;
                        check = 0;
                    }
                }
            }
        }

        //make brokenString2 length set to length of brokenString with room for the "x"s
        newStringLen = brokenString.length + doubles;

        //if length is odd, add 1 to add "x" at the end
        if (newStringLen % 2 == 1)
        {
            newStringLen++;
            check2 = 1;
        }

        //make new array for string to be encrypted and encrypted string
        String brokenString2[] = new String[newStringLen];
        String encryptString[] = new String[newStringLen];

        //add the x's after every repeat letter and at the end if length is odd
        check = 0;
        for (int a = 0; a < brokenString.length; a++)
        {
            brokenString2[a + nextIndex] = brokenString[a];
            if (a == brokenString.length - 1)
            {
                break;
            }
            //start here; if double is detected, replace the next letter with "x" instead
            else if (check == 0)
            {
                if (a % 2 == 0)
                {
                    if (brokenString[a].equalsIgnoreCase(brokenString[a + 1]))
                    {
                        brokenString2[a + 1 + nextIndex] = "x";
                        nextIndex++;
                        check = 1;
                    }
                }
            }
            else
            {
                if (a % 2 == 1)
                {
                    if (brokenString[a].equalsIgnoreCase(brokenString[a + 1]))
                    {
                        brokenString2[a + 1 + nextIndex] = "x";
                        nextIndex++;
                        check = 0;
                    }
                }
            }
        }


        //delete later; filler
        // System.out.println("\n");

        //if length of initial key + doubles is odd, add the extra x at the end
        if (check2 == 1)
        {
            brokenString2[brokenString2.length - 1] = "x";
        }

        //delete later; print out brokenString2
        // for (int a = 0; a < brokenString2.length; a++)
        // {
        //     System.out.print(brokenString2[a]);
        // }


        //start encrypting string
        for (int a = 0; a < brokenString.length; a++)
        {
            //take every 2 letters
            if (a % 2 == 0)
            {
                //get the indices of the pairs of letters
                for (int y = 0; y < 5; y++)
                {
                    for (int x = 0; x < 5; x++)
                    {
                        if (PFairCipher[y][x].equals(brokenString2[a]))
                        {
                            y1 = y;
                            x1 = x;
                        }
                    }
                }
                for (int y = 0; y < 5; y++)
                {
                    for (int x = 0; x < 5; x++)
                    {
                        if (PFairCipher[y][x].equals(brokenString2[a + 1]))
                        {
                            y2 = y;
                            x2 = x;
                        }
                    }
                }

                //different cases of encryption
                if (y1 == y2)
                {
                    encrypt = 1;
                }
                else if (x1 == x2)
                {
                    encrypt = 2;
                }
                else
                {
                    encrypt = 3;
                }

                //encrypting
                switch(encrypt)
                {
                    //same row
                    case 1:
                    {
                        if (x1 == 4)
                        {
                            x1 = -1;
                        }
                        if (x2 == 4)
                        {
                            x2 = -1;
                        }
                        encryptString[a] = PFairCipher[y1][x1 + 1];
                        encryptString[a + 1] = PFairCipher[y1][x2 + 1];
                        break;
                    }
                    //same collumn
                    case 2:
                    {
                        if (y1 == 4)
                        {
                            y1 = -1;
                        }
                        if (y2 == 4)
                        {
                            y2 = -1;
                        }
                        encryptString[a] = PFairCipher[y1 + 1][x1];
                        encryptString[a + 1] = PFairCipher[y2 + 1][x1];
                        break;
                    }
                    //square
                    case 3:
                    {
                        encryptString[a] = PFairCipher[y1][x2];
                        encryptString[a + 1] = PFairCipher[y2][x1];
                        break;
                    }
                }
            }
        }

        //delete later; filler
        // System.out.println("\n");

        //delete later; print encrypted string
        // for (int a = 0; a < encryptString.length; a++)
        // {
        //     System.out.print(encryptString[a]);
        // }

        //last lines take the encrypted string array and turns it into a string
        StringBuffer buffer = new StringBuffer();

        for (int a = 0; a < encryptString.length; a++)
        {
            buffer.append(encryptString[a]);
        }

        newString = buffer.toString();

        return newString;

    }
}