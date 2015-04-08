package basics;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class NumberOfInvocationsTest {

    private static void callClearTwoTimes(List<String> list) {
        list.clear();
        list.clear();
    }

    public static class EasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = createNiceMock(List.class);

        @Test
        public void ensureMethodWasCalledNTimes() throws Exception {
            list.clear();
            expectLastCall().times(2);
            replay(list);

            callClearTwoTimes(list);

            verify(list);
        }
    }

    public static class Mockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void ensureMethodWasCalledNTimes() throws Exception {
            callClearTwoTimes(list);

            org.mockito.Mockito.verify(list, org.mockito.Mockito.times(2)).clear();
        }
    }

}
