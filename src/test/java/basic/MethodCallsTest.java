package basic;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import annotations.UnderTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class MethodCallsTest {

    /**
     * as stated in http://easymock.org/getting-started.html
     */
    public class EasyMockWithSupperClass extends EasyMockSupport {

        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        @Mock
        private List<String> list;

        @Test
        public void ensureMethodWasCalled() throws Exception {
            expect(list.add("one")).andReturn(Boolean.TRUE);
            replayAll();

            callAddOneOn(list);
            verifyAll();
        }
    }

    public class WithEasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = org.easymock.EasyMock.createMock(List.class);

        @Test
        public void ensureMethodWasCalled() throws Exception {
            expect(list.add("one")).andReturn(Boolean.TRUE);
            org.easymock.EasyMock.replay(list);

            callAddOneOn(list);
            org.easymock.EasyMock.verify(this.list);
        }
    }

    /**
     * # Verification is explicit - verification errors point at line of code showing what interaction failed
     */
    public class WithMockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void ensureMethodWasCalled() throws Exception {
            callAddOneOn(list);

            verify(list).add("one");
        }
    }

    @UnderTest
    private static void callAddOneOn(List<String> list) {
        list.add("one");
    }
}
