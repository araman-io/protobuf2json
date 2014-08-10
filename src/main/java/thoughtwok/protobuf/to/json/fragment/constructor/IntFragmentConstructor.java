package thoughtwok.protobuf.to.json.fragment.constructor;

import org.json.JSONStringer;

public class IntFragmentConstructor extends SimpleFragmentConstructor {

    public static final FragmentConstructor INSTANCE = new IntFragmentConstructor();

    @Override
    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(((Integer)value).intValue());
    }
}
