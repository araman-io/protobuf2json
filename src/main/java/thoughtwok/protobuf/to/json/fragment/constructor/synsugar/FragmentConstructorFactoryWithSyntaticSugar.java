package thoughtwok.protobuf.to.json.fragment.constructor.synsugar;

import com.google.protobuf.Descriptors.FieldDescriptor;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.base.DefaultFragmentConstructorFactory;

/**
 * A 
 */
public class FragmentConstructorFactoryWithSyntaticSugar extends DefaultFragmentConstructorFactory {

    @Override
    public FragmentConstructor getFragmentConstructorForType(FieldDescriptor descriptor) {

        FragmentConstructor resolvedConstructor = null;

        // only if it is a message and has the name name KeyValue
        // then return a different fragment constructor
        if (descriptor.getJavaType().name().equals("MESSAGE")
                && descriptor.getMessageType().getName().equals("KeyValue")) {
            resolvedConstructor = MapFragmentConstructor.INSTANCE;
        } else {
            resolvedConstructor = super.getFragmentConstructorForType(descriptor);
        }

        return resolvedConstructor;
    }

}
