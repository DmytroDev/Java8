package com.itcompany.monads.optional;

import com.itcompany.core.constants.Data;
import com.itcompany.core.data.Profession;
import org.junit.Assert;
import org.junit.Test;

public class PersonServiceImplTest {

    @Test
    public void testGetWorkersByCompanyAndProf() {
        Assert.assertTrue(PersonServiceImpl.getWorkersByCompanyAndProf(Data.getPreraredMap(), "Test1", Profession.PROGRAMMER).get(0).getFirstName().equals("Martin"));
        Assert.assertTrue(PersonServiceImpl.getWorkersByCompanyAndProf(Data.getPreraredMap(), "Test2", Profession.PROGRAMMER).get(0).getFirstName().equals("Linus"));
    }
}