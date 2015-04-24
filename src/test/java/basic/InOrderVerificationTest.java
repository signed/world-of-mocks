package basic;

import static org.mockito.Mockito.mock;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

import annotations.UnderTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class InOrderVerificationTest {

    public interface ProtectiveGear{

        void  equip();

        void placeIndianaJonesSticker();
    }

    public interface PandorasBox{
        void open();
    }

    public class WithEasyMock {

        private final IMocksControl control = EasyMock.createStrictControl();
        private final PandorasBox pandorasBox = control.createMock(PandorasBox.class);
        private final ProtectiveGear protectiveGear = control.createMock(ProtectiveGear.class);

        @Test
        public void ensureProtectiveGearIsPutOnBeforeOpeningPandorasBox() throws Exception {
            protectiveGear.equip();
            pandorasBox.open();
            control.replay();

            addElementsInOrderTo(protectiveGear, pandorasBox);

            control.verify();
        }
    }

    public class WithMockito {
        private final PandorasBox pandorasBox = mock(PandorasBox.class);
        private final ProtectiveGear protectiveGear = mock(ProtectiveGear.class);

        @Test
        public void ensureProtectiveGearIsPutOnBeforeOpeningPandorasBox() throws Exception {
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
