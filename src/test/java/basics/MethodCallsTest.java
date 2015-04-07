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

    public static class Mockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void ensureMethodWasCalled() throws Exception {
            list.add("one");

            verify(list).add("one");
        }
    }

    /**
     * as stated in http://easymock.org/getting-started.html
     */
    public static class EasyMock extends EasyMockSupport {

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
}
