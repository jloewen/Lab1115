/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *      Uses advanced search for keywords 
 *</li><li>
 *      Will transform statements as well as react to keywords
 *</li></ul>
 * @author Jameson
 * @version November 2016
 *
 */
public class Magpie4
{
    /**
     * Get a default greeting   
     * @return a greeting
     */ 
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }
        else if (findKeyword(statement, "hi") >= 0)
        {
            response = "Get out.";
        }else if (findKeyword(statement, "Woodside") >= 0)
        {
            response = "That school sickens me...";
        }else if (findKeyword(statement, "thats hot") >= 0)
        {
            response = "You are too.";
        }
        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Yes.";
        }else if (findKeyword(statement, "yes") >= 0)
        {
            response = "No.";
        }
        else if (findKeyword(statement, "africa") >= 0)
        {
            response = "Africa isn't real.";
        }

        else if (findKeyword(statement, "random fact") >= 0)
        {
            response = getRandomFact();
        }
        else if (findKeyword(statement, "favorite") >= 0)
        {
            if (findKeyword(statement, "icecream") >= 0)
            {  
                response = "My favorite icecream is vanilla.";
            }else if (findKeyword(statement, "color")>= 0)
            {
                response = "My favorite color is Coquelicot.";
            }else{
                response = getRandomResponse(); 
            }
        }else if (findKeyword(statement, "wednesday") >= 0)
        {
            if (findKeyword(statement, "dudes") >= 0)
            {  
                response = "HuuuuuuuuuuuuuuuAAHAHAHAHAHAAAAAAA";
            }else
            {
                response = "HUUUUMP DAYYYY";
            }
        }else if (findKeyword(statement, "miss you") >= 0)
        {
            if (findKeyword(statement, "love you") >= 0)
            {  
                response = "Huuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu";
            }else
            {
                response = "You too :-(";
            }

        }
        else if (findKeyword(statement, "mother") >= 0
        || findKeyword(statement, "father") >= 0
        || findKeyword(statement, "sister") >= 0
        || findKeyword(statement, "brother") >= 0)
        {
            response = "Tell me more about your fam my dude.";
        }

        else if (findKeyword(statement, "I have a", 0) >= 0){
            statement = statement.trim();
            String lastChar = statement.substring(statement.length() - 1);
            if (lastChar.equals(".")) {
                statement = statement.substring(0, statement.length() - 1);
            }
            int psn = findKeyword(statement, "I have a", 0);
            String restOfStatement = statement.substring(psn + 8).trim();
            return haveResponse() + " " + restOfStatement + "?";
        }

        // Responses which require transformations
        else if (findKeyword(statement, "I want", 0) >= 0)
        {
            if (findKeyword(statement, "I want to", 0) >= 0) {
                if (findKeyword(statement, "you", 0) >= 0) {
                    response = transformMeYouStatement(statement);
                } else {
                    response = transformIWantToStatement(statement);
                }
            }
            else {
                response = transformIWantStatement(statement);
            }
        }

        else{

            int psn = findKeyword(statement, "you", 0);
            int psn2 = findKeyword(statement, "I");

            if (psn2 >= 0 && findKeyword(statement, "you", psn2) >= 0) {
                response = transformMeYouStatement(statement);
            }
            else if (psn >= 0 && findKeyword(statement, "me", psn) >= 0) {
                response = transformYouMeStatement(statement);
            }
            else {
                response = getRandomResponse();
            }
        }
        return response;
    }

    private String haveResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        if (whichResponse == 0)
        {
            response = "How long have you had a";
        }
        else if (whichResponse == 1)
        {
            response = "What color is your";
        }
        else if (whichResponse == 2)
        {
            response = "Why do you have a";
        }
        else if (whichResponse == 3)
        {
            response = "When did you get your";
        }
        return response;
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    private String transformIWantStatement(String statement) {
        //Remove final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement.length() - 1);
        }
        int psn = findKeyword(statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }


    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }

        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);

        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }

    private String transformMeYouStatement(String statement) {
        statement = statement.trim();
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement.length() - 1);
        }

        int psnOfI = findKeyword(statement, "I", 0);
        int psnOfYou = findKeyword(statement, "you", psnOfI + 1);

        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you "  + restOfStatement + " me?";
    }

    
    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     *
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal,
    int startPos)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                    psn + goal.length(),
                    psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                // letter
            && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);

        }

        return -1;
    }

    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 9;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }else if (whichResponse == 4)
        {
            response = "That's cool.";
        }else if (whichResponse == 5)
        {
            response = "Thanks pretty good.";
        }else if (whichResponse == 6)
        {
            response = "Wow.";
        }else if (whichResponse == 7)
        {
            response = "Really?";
        }else if (whichResponse == 8)
        {
            response = "Platypus. An animal with a bill like a duck, a tail like a beaver, and feet like an otter sounds like something a mad scientist would create. Add to the list the ability to lay lizard-like eggs and shoot poison out of your foot, and you have a unique creature indeed.";
        }

        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomFact()
    {
        final int NUMBER_OF_RESPONSES1 = 13;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES1);
        String response = "";

        if (whichResponse == 0)
        {
            response = "You cannot snore and dream at the same time.";
        }
        else if (whichResponse == 1)
        {
            response = "There is a species of spider called the Hobo Spider.";
        }
        else if (whichResponse == 2)
        {
            response = "Heart attacks are more likely to happen on a Monday";
        }
        else if (whichResponse == 3)
        {
            response = "Bikinis and tampons invented by men.";
        }else if (whichResponse == 4)
        {
            response = "King Henry VIII slept with a gigantic axe beside him.";
        }else if (whichResponse == 5)
        {
            response = "The average woman uses her height in lipstick every 5 years";
        }else if (whichResponse == 6)
        {
            response = "Pteronophobia is the fear of being tickled by feathers!";
        }else if (whichResponse == 7)
        {
            response = "Banging your head against a wall burns 150 calories an hour";
        }else if (whichResponse == 8)
        {
            response = "In Uganda, 50% of the population is under 15 years of age.";
        }else if (whichResponse == 9)
        {
            response = "Catfish are the only animals that naturally have an odd number of whiskers.";
        }else if (whichResponse == 10)
        {
            response = "";
        }else if (whichResponse == 11)
        {
            response = "95% of people text things they could never say in person.";
        }else if (whichResponse == 12)
        {
            response = "Birds don’t urinate.";
        }

        return response;
    }

}