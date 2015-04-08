package basics;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class MethodCallsTest {

    /**
     * as stated in http://easymock.org/getting-started.html
     */
    public static class EasyMockWithSupperClass extends EasyMockSupport {

        @Rule
        public EasyMockRule rule = new EasyMockRule(this);

        @Mock
        private List<String> list;

        @Test
        public void ensureMethodWasCalled() throws Exception {
            expect(list.add("one")).andReturn(Boolean.TRUE);
            replayAll();

            list.add("one");
            verifyAll();
        }
    }

    public static class EasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = org.easymock.EasyMock.createMock(List.class);

        @Test
        public void ensureMethodWasCalled() throws Exception {
            expect(list.add("one")).andReturn(Boolean.TRUE);
            org.easymock.EasyMock.replay(list);

            list.add("one");
            org.easymock.EasyMock.verify(list);
        }
    }

    /**
     * Verification is explicit - verification errors point at line of code showing what interaction failed
     */
    public static class Mockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void ensureMethodWasCalled() throws Exception {
            list.add("one");

            verify(list).add("one");
        }
    }

}
