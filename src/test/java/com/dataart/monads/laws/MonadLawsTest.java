package com.dataart.monads.laws;

import org.junit.Assert;
import org.junit.Test;

public class MonadLawsTest {
    @Test
    public void testLeftIdentityLaw() {
        Assert.assertTrue(MonadLaws.leftIdentityLaw());
    }

    @Test
    public void testRightIdentityLaw() {
        Assert.assertTrue(MonadLaws.rightIdentityLaw());
    }

    @Test
    public void testAssociativityLaw() {
        Assert.assertTrue(MonadLaws.associativityLaw());
    }
}