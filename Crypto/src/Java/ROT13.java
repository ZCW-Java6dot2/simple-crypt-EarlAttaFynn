import java.io.*;

public class ROT13 {

    private static int shift;
    private char lowerBound;
    private char upperBound;


    ROT13(Character cs, Character cf) {
        this.lowerBound = cs;
        this.upperBound = cf;
        shift = (int) cf - (int) cs;
    }

    ROT13() {

    }


    public String crypt(String text) throws UnsupportedOperationException {
        int val;
        String encrypted;
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            val = chars[i];

            //if .isUpperCase
            if (Character.isUpperCase(val)) {

                val += 13;

                //if out of Uppercase alphabet bounds
                if (val > 90) {

                    //Cycle back into Uppercase
                    val -= 26;
                }

                chars[i] = (char) val;

                //if .LowerCase
            } else if (Character.isLowerCase(val)) {

                val += 13;

                //if out of lowerCase alphabet bounds
                if (val > 122) {
                    //Cycle back into lowercase alphabet and set
                    val -= 26;
                }
                chars[i] = (char) val;
            }
        }
        encrypted = new String(chars);
        return encrypted;
    }

    public String encrypt(String text) {
        return crypt(text);
    }

    public String decrypt(String text) {
        return crypt(text);
    }

    public String rotate(String s, Character c) {
        //Declarations
        int val;
        int cycle;
        int lowerBoundary = s.charAt(0);
        int upperBoundary = s.charAt(s.length() - 1);
        int offset;
        String encrypted;
        StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray();

        if (Character.isUpperCase(c)) {
            offset = c - 'A';
        } else {
            offset = c - 'a';
        }

        for (Character e : chars) {
            val = (int) e;
            //if .isUpperCase
            if (Character.isUpperCase(val)) {
                val += offset;
                //if out of Uppercase alphabet bounds
                if (val > Character.toUpperCase(upperBoundary)) {
                    //Cycle back into Uppercase
                    cycle = val % upperBoundary;
                    val = cycle + lowerBoundary - 1;
                }

                result.append((char) val);

                //if .LowerCase
            } else if (Character.isLowerCase(val)) {
                val += offset;
                //if out of lowerCase alphabet bounds
                if (val > Character.toLowerCase(upperBoundary)) {
                    //Cycle back into lowercase alphabet and set
                    cycle = val % upperBoundary;
                    val = cycle + lowerBoundary - 1;
                }
                result.append((char) val);
            }
        }
        return result.toString();
    }

    public void encryptFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("file.enc");
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(crypt(line));
                fileWriter.write(crypt(line) + "\n");
            }
            fileWriter.close();

        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ROT13 rot13 = new ROT13();
        rot13.encryptFile(new File("/Users/earl/Dev/Labs/simple-crypt-EarlAttaFynn/sonnet18.txt"));
    }
}


