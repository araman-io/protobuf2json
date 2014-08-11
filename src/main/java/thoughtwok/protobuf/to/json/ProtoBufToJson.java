package thoughtwok.protobuf.to.json;

import java.util.Map.Entry;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructorFactory;
import thoughtwok.protobuf.to.json.fragment.constructor.base.DefaultFragmentConstructorFactory;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

/**
 * Converts a <code>Message</code> to a JSON string
 * 
 * @author araman
 */
public class ProtoBufToJson {

    private FragmentConstructorFactory factory = null;

    /**
     * an instance
     */
    public static final ProtoBufToJson DEFAULT_INSTANCE = new ProtoBufToJson(new DefaultFragmentConstructorFactory());


    public ProtoBufToJson(FragmentConstructorFactory factory) {
        super();
        this.factory = factory;
    }


    /**
     * iterates through all fields of a message and converts them to a json fragment using an implementation of
     * <code>FragmentConstructor</code>
     * 
     * @param message
     * @return
     */
    public String print(Message message) {

        FragmentConstructor fragmentConstructor = null;

        // initialize a json object
        JSONStringer jsonStringer = new JSONStringer();

        // iterate through the message
        if (message == null) {
            return null;
        }

        jsonStringer.object();

        // iterate through all the fields and call appropriate fragment constructors
        for (Entry<FieldDescriptor, Object> fieldEntry : message.getAllFields().entrySet()) {
            FieldDescriptor descriptor = fieldEntry.getKey();
            Object value = fieldEntry.getValue();

            fragmentConstructor = this.factory.getFragmentConstructorForType(descriptor.getJavaType().name());
            fragmentConstructor.construct(descriptor, value, jsonStringer);

        }

        jsonStringer.endObject();

        return jsonStringer.toString();
    }


}
