package basic;

import static org.easymock.EasyMock.expect;

import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

import annotations.UnderTest;

@RunWith(Enclosed.class)
public class InOrderVerificationTest {

    public static class WithEasyMock {

        private final IMocksControl control = EasyMock.createStrictControl();
        @SuppressWarnings("unchecked")
        private final List<String> one = control.createMock(List.class);
        @SuppressWarnings("unchecked")
        private final List<String> two = control.createMock(List.class);

        @Test
        public void ensureCalledInOrder() throws Exception {
            expect(one.add("one")).andReturn(true);
            expect(two.add("two")).andReturn(true);
            control.replay();

            addElementsInOrderTo(one, two);

            control.verify();
        }
    }

    public static class WithMockito {

        @SuppressWarnings("unchecked")
        private final List<String> one = Mockito.mock(List.class);
        @SuppressWarnings("unchecked")
        private final List<String> two = Mockito.mock(List.class);

        @Test
        public void ensureCalledInOrder() throws Exception {
            addElementsInOrderTo(one, two);

            InOrder order = Mockito.inOrder(one, two);
            order.verify(one).add("one");
            order.verify(two).add("two");
        }
    }

    @UnderTest
    private static void addElementsInOrderTo(List<String> one, List<String> two) {
        one.add("one");
        two.add("two");
    }

}
