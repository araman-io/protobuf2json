package thoughtwok.protobuf.to.json.fragment.constructor.base;

import static org.junit.Assert.*;

import org.junit.Test;

import thoughtwok.protobuf.to.json.fragment.constructor.base.FragmentConstructorEnum;
import thoughtwok.protobuf.to.json.fragment.constructor.base.IntFragmentConstructor;

public class IntFragmentConstructorTest {
    
    @Test
    public void testShouldReturnInstanceOfIntFragmentConstructor() {
        assertTrue(FragmentConstructorEnum.INT.getFragmentConstructor() instanceof IntFragmentConstructor);
    }

}
