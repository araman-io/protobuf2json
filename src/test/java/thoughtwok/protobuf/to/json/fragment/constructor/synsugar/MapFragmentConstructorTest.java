package thoughtwok.protobuf.to.json.fragment.constructor.synsugar;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Map.Entry;

import org.json.JSONStringer;
import org.junit.Test;

import thoughtwok.protobuf.to.json.ProtoBufToJson;
import thoughtwok.protobuf.to.json.test.SamplesProtos.KeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithJustOneKeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithKeyValue;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class MapFragmentConstructorTest {

    @Test
    public void shouldCallJSONStringerKeyValueCallsOncePerKeyValue() {
        Message aRecordWith2Keys =
                MessageWithKeyValue.newBuilder().addRecord(KeyValue.newBuilder().setKey("foo").setValue("bar").build())
                        .addRecord(KeyValue.newBuilder().setKey("foo1").setValue("bar1").build()).build();

        JSONStringer jsonStringer = mock(JSONStringer.class);
        when(jsonStringer.key(anyString())).thenReturn(jsonStringer);
        when(jsonStringer.value(anyString())).thenReturn(jsonStringer);

        // get the first record from the message and pass it to fragment constructor
        Entry<FieldDescriptor, Object> next = aRecordWith2Keys.getAllFields().entrySet().iterator().next();

        MapFragmentConstructor.INSTANCE.construct(next.getKey(), next.getValue(), jsonStringer);

        verify(jsonStringer, times(3)).key(anyString()); // keys gets invoked one additional time to set the record key
        verify(jsonStringer, times(2)).value(anyString()); // keys gets invoked one additional time to set the record
                                                           // key

        verify(jsonStringer, times(1)).object();
        verify(jsonStringer, times(1)).endObject();

        verify(jsonStringer, times(1)).key("record");
        verify(jsonStringer, times(1)).key("foo");
        verify(jsonStringer, times(1)).key("foo1");
        verify(jsonStringer, times(1)).value("bar");
        verify(jsonStringer, times(1)).value("bar1");

        // and no calls to array
        verify(jsonStringer, never()).array();
        verify(jsonStringer, never()).endArray();
    }

    @Test
    public void shouldCallOnlyOnceWhenKeyValueIsSimplyARecord() {
        Message aRecordWith2Keys =
                MessageWithJustOneKeyValue.newBuilder()
                        .setRecord(KeyValue.newBuilder().setKey("foo").setValue("bar").build()).build();

        JSONStringer jsonStringer = mock(JSONStringer.class);
        when(jsonStringer.key(anyString())).thenReturn(jsonStringer);
        when(jsonStringer.value(anyString())).thenReturn(jsonStringer);

        // get the first record from the message and pass it to fragment constructor
        Entry<FieldDescriptor, Object> next = aRecordWith2Keys.getAllFields().entrySet().iterator().next();

        MapFragmentConstructor.INSTANCE.construct(next.getKey(), next.getValue(), jsonStringer);

        verify(jsonStringer, times(2)).key(anyString()); // keys gets invoked one additional time to set the record key
        verify(jsonStringer, times(1)).value(anyString());

        verify(jsonStringer, times(1)).object();
        verify(jsonStringer, times(1)).endObject();

        verify(jsonStringer, times(1)).key("record");
        verify(jsonStringer, times(1)).key("foo");
        verify(jsonStringer, times(1)).value("bar");

        // and no calls to array
        verify(jsonStringer, never()).array();
        verify(jsonStringer, never()).endArray();
    }

}
