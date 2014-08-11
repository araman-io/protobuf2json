package thoughtwok.protobuf.to.json.fragment.constructor.base;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;

import com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * abstract fragment constructor. All constructors should extend this
 * @author araman
 */
public abstract class AbstractFragmentConstructor implements FragmentConstructor {
    
    @Override
    public JSONStringer construct(FieldDescriptor fieldDescriptor, Object value, JSONStringer jsonStringer) {
        
        if ( fieldDescriptor.isRepeated() ) {
            this.constructRepeatedFragment(fieldDescriptor, value, jsonStringer);
        } else {
            this.constructFragment(fieldDescriptor, value, jsonStringer);
        }

        return jsonStringer;
    }

    /**
     * constructs a fragment 
     * @param fieldDescriptor
     * @param value
     * @param jsonStringer
     * @return
     */
    public abstract JSONStringer constructFragment(FieldDescriptor fieldDescriptor, Object value, JSONStringer jsonStringer);

    /**
     * constructs a fragment for a repeated field
     * @param fieldDescriptor
     * @param value
     * @param jsonStringer
     * @return
     */
    public abstract JSONStringer constructRepeatedFragment(FieldDescriptor fieldDescriptor, Object value, JSONStringer jsonStringer);


}
