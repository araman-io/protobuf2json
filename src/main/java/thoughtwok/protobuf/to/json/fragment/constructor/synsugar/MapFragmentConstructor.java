package thoughtwok.protobuf.to.json.fragment.constructor.synsugar;

import java.util.List;
import java.util.Map.Entry;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.base.SimpleFragmentConstructor;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class MapFragmentConstructor extends SimpleFragmentConstructor implements FragmentConstructor {

    public static final FragmentConstructor INSTANCE = new MapFragmentConstructor();

    @Override
    public JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        Message m = (Message) value;
        jsonStringer.key(fs.getName());
        jsonStringer.object();

        this.extractKeyValue(m, jsonStringer);
        
        jsonStringer.endObject();

        return jsonStringer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName());

        jsonStringer.object();

        // for all repeated values iterate
        for (Message m : (List<Message>) value) {
            this.extractKeyValue(m, jsonStringer);
        }

        jsonStringer.endObject();

        return jsonStringer;
    }

    protected void extractKeyValue(Message m, JSONStringer jsonStringer) {
        String k = null;
        String v = null;

        // for all fields in a message
        for (Entry<FieldDescriptor, Object> entry : m.getAllFields().entrySet()) {
            if (entry.getKey().getName().equals("key")) {
                k = entry.getValue().toString();
            } else {
                v = entry.getValue().toString();
            }
        }

        jsonStringer.key(k).value(v);
    }



}
