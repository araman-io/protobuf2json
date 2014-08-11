package thoughtwok.protobuf.to.json.fragment.constructor.synsugar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructorFactory;
import thoughtwok.protobuf.to.json.test.SamplesProtos.KeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithKeyValue;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class FragmentConstructorFactoryWithSyntaticSugarTest {

    @Test
    public void shouldReturnMapFragmentConstrutorWhenMessageIsKeyValue() {
        FragmentConstructorFactory factory = new FragmentConstructorFactoryWithSyntaticSugar();
        Message m =
                MessageWithKeyValue.newBuilder().addRecord(KeyValue.newBuilder().setKey("foo").setValue("bar").build())
                        .addRecord(KeyValue.newBuilder().setKey("foo1").setValue("bar1").build()).build();

        // get fd for the keyvalue field
        Set<Entry<FieldDescriptor, Object>> entrySet = m.getAllFields().entrySet();
        FieldDescriptor fd = entrySet.iterator().next().getKey();
        FragmentConstructor fc = factory.getFragmentConstructorForType(fd);
        assertTrue(fc instanceof MapFragmentConstructor);

    }
    
    @Test
    public void shouldNeverInvokeMapFragmentConstructorWhenItIsTheOnlyMessage() {
        KeyValue kv = KeyValue.newBuilder().setKey("foo").setValue("bar").build();
        FragmentConstructorFactory fcf = new FragmentConstructorFactoryWithSyntaticSugar();
        FragmentConstructor fragmentConstructorForType = fcf.getFragmentConstructorForType(kv.getAllFields().entrySet().iterator().next().getKey());
        assertFalse(fragmentConstructorForType instanceof MapFragmentConstructor);
    }
    
}
