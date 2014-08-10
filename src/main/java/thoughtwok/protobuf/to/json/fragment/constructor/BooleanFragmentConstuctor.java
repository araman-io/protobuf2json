package thoughtwok.protobuf.to.json.fragment.constructor;

import org.json.JSONStringer;

public class BooleanFragmentConstuctor extends SimpleFragmentConstructor {
    
    public static final FragmentConstructor INSTANCE = new BooleanFragmentConstuctor();

    @Override
    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(((Boolean)value).booleanValue());
    }

}
