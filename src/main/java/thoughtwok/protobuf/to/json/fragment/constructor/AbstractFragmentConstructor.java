package thoughtwok.protobuf.to.json.fragment.constructor;

import org.json.JSONStringer;

import com.google.protobuf.Descriptors.FieldDescriptor;

public abstract class AbstractFragmentConstructor implements FragmentConstructor {
    
    @Override
    public JSONStringer construct(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        
        if ( fs.isRepeated() ) {
            this.constructRepeatedFragment(fs, value, jsonStringer);
        } else {
            this.constructFragment(fs, value, jsonStringer);
        }

        return jsonStringer;
    }

    public abstract JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer);

    public abstract JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer);


}
