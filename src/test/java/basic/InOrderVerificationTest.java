package basic;

import static org.mockito.Mockito.mock;

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

    public interface ProtectiveGear{

        void  equip();

        void placeIndianaJonesSticker();
    }

    public interface PandorasBox{
        void open();
    }

    public static class WithEasyMock {

        private final IMocksControl control = EasyMock.createStrictControl();
        private final PandorasBox pandorasBox = control.createMock(PandorasBox.class);
        private final ProtectiveGear protectiveGear = control.createMock(ProtectiveGear.class);

        @Test
        public void ensureCalledInOrder() throws Exception {
            protectiveGear.equip();
            pandorasBox.open();
            control.replay();

            addElementsInOrderTo(protectiveGear, pandorasBox);

            control.verify();
        }
    }

    public static class WithMockito {
        private final PandorasBox pandorasBox = mock(PandorasBox.class);
        private final ProtectiveGear protectiveGear = mock(ProtectiveGear.class);

        @Test
        public void ensureCalledInOrder() throws Exception {
            addElementsInOrderTo(protectiveGear, pandorasBox);

            InOrder order = Mockito.inOrder(protectiveGear, pandorasBox);
            order.verify(protectiveGear).equip();
            order.verify(pandorasBox).open();
        }
    }

    @UnderTest
    private static void addElementsInOrderTo(ProtectiveGear protectiveGear, PandorasBox pandorasBox) {
        protectiveGear.equip();
        pandorasBox.open();
    }

}
