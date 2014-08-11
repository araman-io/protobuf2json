package thoughtwok.protobuf.to.json.fragment.constructor.base;

import java.util.Iterator;
import java.util.List;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;

import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;

public class EnumFragmentConstructor extends AbstractFragmentConstructor implements FragmentConstructor {
    
    public static final EnumFragmentConstructor INSTANCE = new EnumFragmentConstructor();

    @Override
    public JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName()).value( ((EnumValueDescriptor)value).getName() );
        return jsonStringer;
    }

    @Override
    public JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.array();
        
        Iterator<?> i = ((List<?>) value).iterator();
        
        while(i.hasNext()) {
            jsonStringer.value( ((EnumValueDescriptor)i.next()).getName());
        }
        
        jsonStringer.endArray();
        
        return jsonStringer;
    }

}
