package test;

import java.util.ArrayList;

import game.system.parsing.PlayerCommandParser;

public class HelloWorld {
    public static void main(String[] args) {

        String str = ",One ,day, Bob's, 'hat' (open door) 'sing song asdf' it's \"tomato asdf\" t.ot";
        ArrayList<String> l = PlayerCommandParser.lexicalAnalysis(str);

        System.out.println(l);
        for (String s : l) {
            System.out.println(s);
        }
    }
}
