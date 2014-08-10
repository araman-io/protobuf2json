package thoughtwok.protobuf.to.json.fragment.constructor;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONStringer;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class MessageFragmentConstructor extends AbstractFragmentConstructor implements FragmentConstructor {

    public static final FragmentConstructor INSTANCE = new MessageFragmentConstructor();

    @Override
    public JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        Message subMessage = null;

        jsonStringer.key(fs.getName());
        subMessage = (Message) value;

        jsonStringer.object();
        iterateOverMessage(subMessage, jsonStringer);
        jsonStringer.endObject();

        return null;
    }

    @Override
    public JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        Iterator<?> messageIterator = null;
        jsonStringer.key(fs.getName());

        // iterate over the repeated messages
        messageIterator = ((List<?>) value).iterator();
        jsonStringer.array();

        while (messageIterator.hasNext()) {
            Message m = (Message) messageIterator.next();
            jsonStringer.object();
            this.iterateOverMessage(m, jsonStringer);
            jsonStringer.endObject();
        }

        jsonStringer.endArray();
        return jsonStringer;
    }

    protected void iterateOverMessage(Message message, JSONStringer jsonStringer) {
        for (Entry<FieldDescriptor, Object> fieldEntry : message.getAllFields().entrySet()) {
            FieldDescriptor subDescriptor = fieldEntry.getKey();
            Object subValue = fieldEntry.getValue();

            FragmentConstructor fragmentConstructor =
                    FragmentConstructorEnum.valueOf(subDescriptor.getJavaType().name()).getFragmentConstructor();
            fragmentConstructor.construct(subDescriptor, subValue, jsonStringer);
        }
    }

}
