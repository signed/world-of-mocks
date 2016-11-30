package sample._2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ApiImprovementsTest {

    @Test
    public void simpler_setup_to_throw_exceptions() throws Exception {
        Runnable runnable = Mockito.mock(Runnable.class);
        Mockito.doThrow(UnsupportedOperationException.class).doThrow(IllegalStateException.class).when(runnable).run();
        //Mockito.doThrow(UnsupportedOperationException.class, IllegalStateException.class).when(runnable).run();
        try {
            runnable.run();
            Assert.fail();
        } catch (UnsupportedOperationException e) {
            //expected
        }

        try {
            runnable.run();
            Assert.fail();
        } catch (IllegalStateException e) {
            //expected
        }
    }
}
