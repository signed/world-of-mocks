package basic;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import annotations.UnderTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class ThrowExceptionTest {

    public class WithEasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = createNiceMock(List.class);

        @Test(expected = RuntimeException.class)
        public void methodWithReturnValue() throws Exception {
            expect(list.get(0)).andThrow(new RuntimeException("do not call me"));
            replay(list);

            firstElementIn(list);
        }

        @Test(expected = RuntimeException.class)
        public void methodWithoutReturnValue() throws Exception {
            list.clear();
            expectLastCall().andThrow(new RuntimeException("do not call me"));
            replay(list);

            List<String> list = this.list;
            clear(list);
        }
    }

    public class WithMockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test(expected = RuntimeException.class)
        public void methodWithReturnValue() throws Exception {
            when(list.get(0)).thenThrow(new RuntimeException("do not call me"));

            List<String> list = this.list;
            firstElementIn(list);
        }

        @Test(expected = RuntimeException.class)
        public void methodWithoutReturnValue() throws Exception {
            doThrow(new RuntimeException("do not call me")).when(list).clear();

            clear(list);
        }

    }

    @UnderTest
    private static String firstElementIn(List<String> list) {
        return list.get(0);
    }

    @UnderTest
    private static void clear(List<String> list) {
        list.clear();
    }

}
