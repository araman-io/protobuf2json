package thoughtwok.protobuf.to.json.fragment.constructor;

import org.json.JSONStringer;

import com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * main interface
 * @author araman
 */
public interface FragmentConstructor {
    
    public JSONStringer construct(FieldDescriptor fs, Object value, JSONStringer jsonStringer);

}
