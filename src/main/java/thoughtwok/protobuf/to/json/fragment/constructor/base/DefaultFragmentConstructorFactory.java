package thoughtwok.protobuf.to.json.fragment.constructor.base;

import com.google.protobuf.Descriptors.FieldDescriptor;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructorFactory;

public class DefaultFragmentConstructorFactory implements FragmentConstructorFactory {

    @Override
    public FragmentConstructor getFragmentConstructorForType(FieldDescriptor descriptor) {
        return FragmentConstructorEnum.valueOf(descriptor.getJavaType().name()).getFragmentConstructor();
    }

}
