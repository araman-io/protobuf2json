package thoughtwok.protobuf.to.json.fragment.constructor.base;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;

public class BooleanFragmentConstuctor extends SimpleFragmentConstructor {
    
    public static final FragmentConstructor INSTANCE = new BooleanFragmentConstuctor();

    @Override
    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(((Boolean)value).booleanValue());
    }

}
