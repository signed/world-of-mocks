package sample._1;

import org.junit.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDetailsTest {

    @Test
    public void print_details_for_debugging() throws Exception {
        Queue<String> queue = when(mock(Queue.class).poll()).thenReturn("a", "b", "c").getMock();

        queue.poll();
        queue.peek();
        queue.isEmpty();

        MockingDetails details = Mockito.mockingDetails(queue);
        assertThat("should be a mock", details.isMock());
    }
}
