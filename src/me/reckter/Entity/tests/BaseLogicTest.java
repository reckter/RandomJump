package me.reckter.Entity.tests;

import junit.framework.Assert;
import me.reckter.Entity.Category.Category;
import me.reckter.Entity.Category.Matching.Matching;
import org.junit.Test;

/**
 * Created by Hannes on 2/24/14.
 *
 * @author Hannes Güdelhöfer
 */
public class BaseLogicTest {
    @Test
    public void testEquals() throws Exception {
        Assert.assertTrue("Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)) didn't match itself", Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)).equals(Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY))));
        Assert.assertTrue("Matching.AND(Category.MOVEMENT, Category.GRAVITY) matched",  Matching.OR(Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)), Matching.XOR(Matching.MATCH(Category.GRAVITY), Matching.NOT(Matching.MATCH(Category.BOUNCES)))).equals(Matching.OR(Matching.AND(Matching.MATCH(Category.MOVEMENT), Matching.MATCH(Category.GRAVITY)), Matching.XOR(Matching.MATCH(Category.GRAVITY), Matching.NOT(Matching.MATCH(Category.BOUNCES))))));
    }
}
