package sample._2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ApiImprovementsTest {

    @Test
    public void simpler_setup_to_return_multiple_results_in_order() throws Exception {
        List<String> queue = new ArrayList<>();
        List<String> spy = Mockito.spy(queue);

        //Mockito.when(spy.get(0)).thenReturn("one", "two", "three");
        Mockito.doReturn("one").doReturn("two").doReturn("three").when(spy).get(0);
        //Mockito.doReturn("one", "two", "three").when(spy).get(0);

        assertThat(spy.get(0), equalTo("one"));
        assertThat(spy.get(0), equalTo("two"));
        assertThat(spy.get(0), equalTo("three"));
    }

    @Test
    public void simpler_setup_to_throw_multiple_exceptions_in_order() throws Exception {
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
