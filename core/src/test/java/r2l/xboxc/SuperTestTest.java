package r2l.xboxc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SuperTestTest
{
    @Test
    public void thisAlwaysPasses()
    {
        assertTrue(true);
    }

    @Test
    @Ignore
    public void thisIsIgnored()
    {
    }
}