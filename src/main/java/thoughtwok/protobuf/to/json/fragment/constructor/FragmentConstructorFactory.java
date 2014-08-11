package thoughtwok.protobuf.to.json.fragment.constructor;

import com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * An interface to get hold of <code>FragmentConstructor</code>
 * @author araman
 */
public interface FragmentConstructorFactory {
    
    /**
     * return an instance of fragment constructor for a type
     * @param type
     * @return
     */
    public FragmentConstructor getFragmentConstructorForType(FieldDescriptor descriptor);

}
