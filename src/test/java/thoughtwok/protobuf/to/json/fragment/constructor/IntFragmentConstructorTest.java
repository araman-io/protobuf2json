package thoughtwok.protobuf.to.json.fragment.constructor;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntFragmentConstructorTest {
    
    @Test
    public void testShouldReturnInstanceOfIntFragmentConstructor() {
        assertTrue(FragmentConstructorEnum.INT.getFragmentConstructor() instanceof IntFragmentConstructor);
    }

}
