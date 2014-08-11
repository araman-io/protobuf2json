package thoughtwok.protobuf.to.json.fragment.constructor.base;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructorFactory;

public class DefaultFragmentConstructorFactory implements FragmentConstructorFactory {

    @Override
    public FragmentConstructor getFragmentConstructorForType(String type) {
        return FragmentConstructorEnum.valueOf(type).getFragmentConstructor();
    }

}
