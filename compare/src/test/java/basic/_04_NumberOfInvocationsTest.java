package basic;

import java.util.List;

import annotations.UnderTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(HierarchicalContextRunner.class)
public class _04_NumberOfInvocationsTest {

    public class WithEasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = createNiceMock(List.class);

        @Test
        public void ensureMethodWasCalledNTimes() throws Exception {
            list.clear();
            expectLastCall().times(2);
            replay(list);

            callClearTwoTimes(list);

            EasyMock.verify(list);
        }

    }

    public class WithMockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void ensureMethodWasCalledNTimes() throws Exception {
            callClearTwoTimes(list);

            Mockito.verify(list, times(2)).clear();
        }

    }

    @UnderTest
    private static void callClearTwoTimes(List<String> list) {
        list.clear();
        list.clear();
    }

}
