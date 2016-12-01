package sample._1;

import org.junit.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.mock.MockCreationSettings;

import java.util.Collection;
import java.util.Queue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDetailsTest {

    @Test
    public void print_details_for_debugging() throws Exception {
        Queue<String> queue = when(mock(Queue.class).poll()).thenReturn("a", "b", "c").getMock();

        deeplyNestedProductionCode(queue);

        MockingDetails mockingDetails = Mockito.mockingDetails(queue);
        assertThat("should be a mock", mockingDetails.isMock());

        //MockCreationSettings<?> mockCreationSettings = mockingDetails.getMockCreationSettings();
        //Collection<Stubbing> stubbings = mockingDetails.getStubbings();
        Collection<Invocation> invocations = mockingDetails.getInvocations();
        //System.out.println(mockingDetails.printInvocations());
    }

    private void deeplyNestedProductionCode(Queue<String> queue) {
        queue.poll();
        queue.peek();
        queue.isEmpty();
    }
}
