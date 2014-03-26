package me.reckter.Entity.Category.Matching;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Logic.*;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Hannes on 2/23/14.
 *
 * @author Hannes Güdelhöfer
 */
public class Matching {

    static final HashMap<String, Category> categories = new HashMap<String, Category>();
    static final HashMap<String, BaseLogic> matches = new HashMap<String, BaseLogic>();

    public Matching(BaseLogic logic) {
        this.logic = logic;
    }

    public Matching(String match) {
        this.logic = parse(match);
    }

    BaseLogic logic;

    public boolean resolve(TreeSet<Category> categories) {
        if(logic == null){
            return true;
        }
        return logic.resolve(categories);
    }

    static public BaseLogic AND(BaseLogic a, BaseLogic b) {
        return new AND(a,b);
    }

    static public BaseLogic AND(Category a, Category b) {
        return new AND(a,b);
    }

    static public BaseLogic AND(BaseLogic a, Category b) {
        return new AND(a,MATCH(b));
    }

    static public BaseLogic AND(Category a, BaseLogic b) {
        return new AND(MATCH(a), b);
    }

    static public BaseLogic OR(BaseLogic a, BaseLogic b) {
        return new OR(a,b);
    }

    static public BaseLogic OR(Category a, Category b) {
        return new OR(a,b);
    }

    static public BaseLogic OR(BaseLogic a, Category b) {
        return new OR(a,MATCH(b));
    }

    static public BaseLogic OR(Category a, BaseLogic b) {
        return new OR(MATCH(a), b);
    }

    static public BaseLogic XOR(BaseLogic a, BaseLogic b) {
        return new XOR(a,b);
    }

    static public BaseLogic XOR(Category a, Category b) {
        return new XOR(a,b);
    }

    static public BaseLogic XOR(BaseLogic a, Category b) {
        return new XOR(a,MATCH(b));
    }

    static public BaseLogic XOR(Category a, BaseLogic b) {
        return new XOR(MATCH(a), b);
    }

    static public BaseLogic NOT(BaseLogic a) {
        return new NOT(a);
    }

    static public BaseLogic NOT(Category a) {
        return new NOT(a);
    }

    static public BaseLogic MATCH(BaseLogic a) {
        return new MATCH(a);
    }

    static public BaseLogic MATCH(Category a) {
        return new MATCH(a);
    }

    static private void fillCategories() {

	    for(Category category: Category.values()) {
		    categories.put(category.name(), category);
	    }
    }

    /**
     * parses a string into a matching expression
     *
     * example:
     * (MOVEMENT&GRAVITY) becomes AND(MOVEMENT,GRAVITY);
     * @param str
     * @return
     */
    static public BaseLogic parse(String str) {
        if(categories.isEmpty()) {
            fillCategories();
        }
        if(!str.contains("(")) {
            String[] temp;
            if(str.contains("&")){
                temp = str.split("&", 2);
                return AND(parse(temp[0]), parse(temp[1]));
            } else if(str.contains("|")){
                temp = str.split("\\|", 2);
                return OR(parse(temp[0]), parse(temp[1]));
            } else if(str.contains("^")){
                temp = str.split("\\^", 2);
                return XOR(parse(temp[0]), parse(temp[1]));
            } else if(str.contains("!")){
                temp = str.split("!", 2);
                return NOT(parse(temp[1]));
            } else {
                return MATCH(categories.get(str));
            }
        } else {
            int braketsCounter = 0;
            int firstBracket = -1;
            int i = 0;
            for(char c: str.toCharArray()) {
                if(c == '(') {
                    if(firstBracket == -1) {
                        firstBracket = i;
                    }
                    braketsCounter++;
                } else if(c == ')') {
                    braketsCounter--;
                    if(braketsCounter == 0) {
                        if(str.length() == i + 1) {
                            return parse(str.substring(firstBracket + 1, i));
                        }else if(str.charAt(i + 1) == '&'){
                            return AND(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
                        } else if(str.charAt(i + 1) == '|'){
                            return OR(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
                        } else if(str.charAt(i + 1) == '^'){
                            return XOR(parse(str.substring(firstBracket + 1, i)), parse(str.substring(i + 2, str.length())));
                        }
                    }
                } else if(braketsCounter == 0) {
                    if(str.charAt(i) == '&'){
                        return AND(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
                    } else if(str.charAt(i) == '|'){
                        return OR(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
                    } else if(str.charAt(i) == '^'){
                        return XOR(parse(str.substring(0, i)), parse(str.substring(i + 1, str.length())));
                    } else if(str.charAt(i) == '!'){
                        return NOT(parse(str.substring(i + 1, str.length())));
                    }
                }
                i++;
            }
        }
        return  null;
    }

    @Override
    public String toString() {
        return logic.toString();
    }
}
