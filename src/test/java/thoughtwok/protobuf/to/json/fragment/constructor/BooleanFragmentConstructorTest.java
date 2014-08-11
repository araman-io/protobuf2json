package thoughtwok.protobuf.to.json.fragment.constructor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map.Entry;

import org.json.JSONStringer;
import org.junit.Test;

import thoughtwok.protobuf.to.json.ProtoBufToJson;
import thoughtwok.protobuf.to.json.fragment.constructor.base.BooleanFragmentConstuctor;
import thoughtwok.protobuf.to.json.fragment.constructor.base.FragmentConstructorEnum;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithBoolField;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class BooleanFragmentConstructorTest {
    
    @Test
    public void shouldReturnBooleanConstructorForBooleanFields() {
        assertTrue(FragmentConstructorEnum.BOOLEAN.getFragmentConstructor() instanceof BooleanFragmentConstuctor);
    }
    
    @Test
    public void shouldSetBooleanValueOnceOnJsonStringer() {
        Message m = MessageWithBoolField.newBuilder().setAlive(true).build();
        FragmentConstructor fc = BooleanFragmentConstuctor.INSTANCE;
        JSONStringer js = mock(JSONStringer.class);
        
        for (Entry<FieldDescriptor, Object> i : m.getAllFields().entrySet()) {
            fc.construct(i.getKey(), i.getValue(), js);
        }
        
        verify(js, times(1)).value(true);
        verify(js, never()).object();
        verify(js, never()).endObject();
    }

    @Test
    public void shouldSetRepeatedBooleanFieldsOnJsonStringer() {
        Message m = MessageWithBoolField.newBuilder().setAlive(true).addPresent(true).addPresent(true).addPresent(false).build();
        FragmentConstructor fc = BooleanFragmentConstuctor.INSTANCE;
        JSONStringer js = mock(JSONStringer.class);
        
        for (Entry<FieldDescriptor, Object> i : m.getAllFields().entrySet()) {
            fc.construct(i.getKey(), i.getValue(), js);
        }
        
        verify(js, times(4)).value(anyBoolean());
        verify(js, times(1)).array();
        verify(js, times(1)).endArray();
        verify(js, never()).object();
        verify(js, never()).endObject();
    }
    
    @Test
    public void shouldOutputMessageWithBooleanFields() {
        Message m = MessageWithBoolField.newBuilder().setAlive(true).addPresent(true).addPresent(true).addPresent(false).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        
        assertEquals("{\"alive\":true,\"present\":[true,true,false]}", s);
    }

}
