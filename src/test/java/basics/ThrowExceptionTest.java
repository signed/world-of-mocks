package basics;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ThrowExceptionTest {

    public static class EasyMock {

        private final List<String> list = org.easymock.EasyMock.createNiceMock(List.class);

        @Test(expected = RuntimeException.class)
        public void methodWithReturnValue() throws Exception {
            expect(list.get(0)).andThrow(new RuntimeException("do not call me"));
            replay(list);

            list.get(0);
        }

        @Test(expected = RuntimeException.class)
        public void methodWithoutReturnValue() throws Exception {
            list.clear();
            expectLastCall().andThrow(new RuntimeException("do not call me"));
            replay(list);

            list.clear();
        }

    }

    public static class Mockito {

        private final List<String> list = mock(List.class);

        @Test(expected = RuntimeException.class)
        public void methodWithReturnValue() throws Exception {
            when(list.get(0)).thenThrow(new RuntimeException("do not call me"));

            list.get(0);
        }

        @Test(expected = RuntimeException.class)
        public void methodWithoutReturnValue() throws Exception {
            doThrow(new RuntimeException("do not call me")).when(list).clear();

            list.clear();
        }
    }
}
