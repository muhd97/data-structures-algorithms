import java.util.*;

public class T9Spelling{

    Hashtable<String, String> phoneKeypad = new Hashtable<String, String>();
    String[] message;

    public T9Spelling(String[] message){
        this.message = message;
    }

    private void createKeypad(){
        phoneKeypad.put("a", "2");
        phoneKeypad.put("b", "22");
        phoneKeypad.put("c", "222");
        phoneKeypad.put("d", "3");
        phoneKeypad.put("e", "33");
        phoneKeypad.put("f", "333");
        phoneKeypad.put("g", "4");
        phoneKeypad.put("h", "44");
        phoneKeypad.put("i", "444");
        phoneKeypad.put("j", "5");
        phoneKeypad.put("k", "55");
        phoneKeypad.put("l", "555");
        phoneKeypad.put("m", "6");
        phoneKeypad.put("n", "66");
        phoneKeypad.put("o", "666");
        phoneKeypad.put("p", "7");
        phoneKeypad.put("q", "77");
        phoneKeypad.put("r", "777");
        phoneKeypad.put("s", "7777");
        phoneKeypad.put("t", "8");
        phoneKeypad.put("u", "88");
        phoneKeypad.put("v", "888");
        phoneKeypad.put("w", "9");
        phoneKeypad.put("x", "99");
        phoneKeypad.put("y", "999");
        phoneKeypad.put("z", "9999");
        phoneKeypad.put(" ", "0");
    }

    public void numTranslation(){
        createKeypad();

        int caseNum = 1; //starts from 1 due to outputformat
        String translation = "";
        String previousKey = ""; //stores the previous char to compare it with the current char
        for (String str : message) {
            String charArr[] = str.split(""); //splits the message of the input case into characters array
            for (String key : charArr) {
                if (phoneKeypad.get(key).split("")[0].compareTo(previousKey.split("")[0]) == 0){ //checks if the previous char and the current char are belonging to the same mobile keypress 
                    translation += " ";                                                          //to create a pause
                }
                translation += phoneKeypad.get(key);
                previousKey = phoneKeypad.get(key);
            }
            System.out.println("Case #" + caseNum + ": " + translation);

            previousKey = ""; //resets the previous key and the number translation for the next case
            translation = "";
            caseNum++;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numOfCases = Integer.parseInt(sc.nextLine());
        String message[] = new String[numOfCases];

        for (int i = 0; i < numOfCases; i++) {
            message[i] = sc.nextLine();
        }

        T9Spelling problemA = new T9Spelling(message);
        problemA.numTranslation();
    }

}
