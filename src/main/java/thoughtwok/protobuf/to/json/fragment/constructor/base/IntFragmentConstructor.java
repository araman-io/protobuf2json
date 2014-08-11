package thoughtwok.protobuf.to.json.fragment.constructor.base;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;

public class IntFragmentConstructor extends SimpleFragmentConstructor {

    public static final FragmentConstructor INSTANCE = new IntFragmentConstructor();

    @Override
    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(((Integer)value).intValue());
    }
}
