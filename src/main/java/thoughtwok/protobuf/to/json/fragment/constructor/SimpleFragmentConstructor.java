package thoughtwok.protobuf.to.json.fragment.constructor;

import java.util.Iterator;
import java.util.List;

import org.json.JSONStringer;

import com.google.protobuf.Descriptors.FieldDescriptor;

public class SimpleFragmentConstructor extends AbstractFragmentConstructor implements FragmentConstructor {
    
    public static final FragmentConstructor INSTANCE = new SimpleFragmentConstructor();
    
    @Override
    public JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName());
        setTypeSpecificValue(value, jsonStringer);
        return jsonStringer;
    }

    @Override
    public JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName());
        jsonStringer.array();
        
        Iterator<?> i = ((List<?>) value).iterator();
        
        while(i.hasNext()) {
            setTypeSpecificValue(i.next(), jsonStringer);
        }
        
        jsonStringer.endArray();
        
        return jsonStringer;
        
    }

    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(value.toString());
    }

}
