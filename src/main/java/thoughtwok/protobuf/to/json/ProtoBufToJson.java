package thoughtwok.protobuf.to.json;

import java.util.Map.Entry;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructorEnum;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class ProtoBufToJson {

    public static final ProtoBufToJson INSTANCE = new ProtoBufToJson();

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

            fragmentConstructor =
                    FragmentConstructorEnum.valueOf(descriptor.getJavaType().name()).getFragmentConstructor();
            fragmentConstructor.construct(descriptor, value, jsonStringer);

        }

        jsonStringer.endObject();

        return jsonStringer.toString();
    }


}
