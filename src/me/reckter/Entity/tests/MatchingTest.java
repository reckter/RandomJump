package me.reckter.Entity.tests;

import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Matching;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class MatchingTest {
    @Test
    public void testParse() throws Exception {
        Assert.assertEquals("'MOVEMENT&GRAVITY' not parsed", Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)), Matching.parse("MOVEMENT&GRAVITY"));
        Assert.assertEquals("'(MOVEMENT&GRAVITY)' not parsed", Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)), Matching.parse("(MOVEMENT&GRAVITY)"));
        Assert.assertEquals("'((MOVEMENT&GRAVITY)|(GRAVITY^!BOUNCES))' not parsed", Matching.OR(Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)), Matching.XOR(Matching.MATCH(Category.GRAVITY), Matching.NOT(Matching.MATCH(Category.BOUNCES)))), Matching.parse("((MOVEMENT&GRAVITY)|(GRAVITY^!BOUNCES))"));
        Assert.assertEquals(Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.OR(Matching.MATCH(Category.GRAVITY), Matching.NOT(Matching.OR(Matching.MATCH(Category.BOUNCES), Matching.MATCH(Category.REGENERATES))))), Matching.parse("MOVEMENT&(GRAVITY|!(BOUNCES|REGENERATES))"));
    }
}
