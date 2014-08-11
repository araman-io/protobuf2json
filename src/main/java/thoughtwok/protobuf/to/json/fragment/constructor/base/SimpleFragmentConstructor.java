package thoughtwok.protobuf.to.json.fragment.constructor.base;

import java.util.Iterator;
import java.util.List;

import org.json.JSONStringer;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;

import com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * suitable for all non message Java Type fields
 * 
 * @author araman
 */
public class SimpleFragmentConstructor extends AbstractFragmentConstructor implements FragmentConstructor {

    public static final FragmentConstructor INSTANCE = new SimpleFragmentConstructor();

    @Override
    public JSONStringer constructFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName());
        setTypeSpecificValue(value, jsonStringer);
        return jsonStringer;
    }

    @Override
    public JSONStringer constructRepeatedFragment(FieldDescriptor fs, Object value, JSONStringer jsonStringer) {
        jsonStringer.key(fs.getName());
        jsonStringer.array();

        Iterator<?> i = ((List<?>) value).iterator();

        while (i.hasNext()) {
            setTypeSpecificValue(i.next(), jsonStringer);
        }

        jsonStringer.endArray();

        return jsonStringer;

    }

    /**
     * <code>JSONStringer</code> provides overriden methods to set the value of certain java types. Extension point
     * invoke these overriden methods
     * 
     * @param value
     * @param jsonStringer
     */
    protected void setTypeSpecificValue(Object value, JSONStringer jsonStringer) {
        jsonStringer.value(value.toString());
    }

}
